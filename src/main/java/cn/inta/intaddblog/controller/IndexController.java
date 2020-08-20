package cn.inta.intaddblog.controller;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Type;
import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.*;
import cn.inta.intaddblog.vo.IndexHtml;
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
 * @describe 主页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam String query,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<SearchHtml> searchHtmlList = blogService.findByQuery(query);
        PageInfo<SearchHtml> pageInfo = new PageInfo<>(searchHtmlList);
//        System.out.println("-----------------" + pageInfo.getList().size() + "listSize");
//        System.out.println("-----------------" + pageInfo.getPages() + "pages");
        model.addAttribute("page", pageInfo);
        //用于页面重现中，搜索栏显示之前搜索的内容
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = blogService. findAll();
        PageInfo pageInfo = new PageInfo(blogList);
        List<IndexHtml> indexHtmls = new ArrayList<>();
        for (Blog b : blogList) {
            IndexHtml indexHtml = new IndexHtml();
            User user = userService.getUserById(b.getUserId());
            Type type = typeService.getTypeById(b.getType());
            indexHtml.setBlog(b);
            indexHtml.setUser(user);
            indexHtml.setType(type);
            indexHtmls.add(indexHtml);
        }
        pageInfo.setList(indexHtmls);
        model.addAttribute("page", pageInfo);
        model.addAttribute("types", typeService.findTop(6));
        model.addAttribute("tags", tagService.findTop(10));
        model.addAttribute("recommendBlogs", blogService.findRecommendTop(8));
        return "index";
    }

}
