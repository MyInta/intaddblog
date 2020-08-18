package cn.inta.intaddblog.controller.admin;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author inta
 * @date 2020/8/6
 * @describe 后台博客管理执行器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    //定义一些常量方便使用
    /*博客内容输入管理页面地址*/
    private static final String INPUT = "admin/blogs-input";
    /*博客列表界面*/
    private static final String LIST = "admin/blogs";
    //重定向到博客列表界面
    private static final String REDIRECT_LIST = "admin/blogs-input";

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public String blogs(Model model) {
//        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.archiveBlog());
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(Blog blog, Model model) {
        model.addAttribute("page", blogService.findBlogByCondition(blog));
        return "admin/blogs :: blogList";
    }


    private void setTypeAndTag(Model model) {
//        model.addAttribute("types", typeService.listType());
//        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.findBlogById(id);
        model.addAttribute("blog",blog);
        return INPUT;
    }


    /**
     * 依据id删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}
