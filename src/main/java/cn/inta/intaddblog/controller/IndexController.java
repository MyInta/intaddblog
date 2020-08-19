package cn.inta.intaddblog.controller;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.*;
import cn.inta.intaddblog.vo.BlogHtml;
import cn.inta.intaddblog.vo.BlogHtmlCommentPart;
import cn.inta.intaddblog.vo.SearchHtml;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/8/14
 * @describe
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        BlogHtml blogHtml = new BlogHtml();
        Blog blog = blogService.getAndConvert(id);
        User user = userService.getUserById(blog.getUserId());
        List<Tag> tags = tagService.getTagsByIds(blog.getTags());
        List<BlogHtmlCommentPart> blogHtmlCommentParts = commentService.listCommentByBlogId(blog.getId());

        blogHtml.setBlog(blog);
        blogHtml.setUser(user);
        blogHtml.setTags(tags);
        blogHtml.setBlogHtmlCommentParts(blogHtmlCommentParts);

        model.addAttribute("blogHtml", blogHtml);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model) {
        //找到最新提交的三篇博客信息
        model.addAttribute("newblogs", blogService.findUpTimeTop(3));
        return "_fragments :: newblogList";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam String query,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<SearchHtml> searchHtmlList = blogService.findByQuery(query);
        PageInfo<SearchHtml> pageInfo = new PageInfo<>(searchHtmlList);


        System.out.println("-----------------" + pageInfo.getList().size() + "listSize");
        System.out.println("-----------------" + pageInfo.getPages() + "pages");


        model.addAttribute("page", pageInfo);
        //用于页面重现中，搜索栏显示之前搜索的内容
        model.addAttribute("query", query);
        return "search";
    }

}
