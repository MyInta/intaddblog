package cn.inta.intaddblog.controller;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.BlogService;
import cn.inta.intaddblog.service.CommentService;
import cn.inta.intaddblog.service.TagService;
import cn.inta.intaddblog.service.UserService;
import cn.inta.intaddblog.vo.BlogHtml;
import cn.inta.intaddblog.vo.BlogHtmlCommentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        List<BlogHtmlCommentPart> comments = commentService.listCommentByBlogId(blog.getId());

        blogHtml.setBlog(blog);
        blogHtml.setUser(user);
        blogHtml.setTags(tags);
        blogHtml.setComments(comments);

        model.addAttribute("blogHtml", blogHtml);
        return "blog";
    }
}
