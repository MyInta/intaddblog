package cn.inta.intaddblog.controller.admin;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Type;
import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.BlogService;
import cn.inta.intaddblog.service.TagService;
import cn.inta.intaddblog.service.TypeService;
import cn.inta.intaddblog.vo.admin.AdminBlogsHtml;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author inta
 * @date 2020/8/6
 * @describe 后台博客管理执行器
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    //定义一些常量方便使用
    /*博客内容输入管理页面地址*/
    private static final String INPUT = "admin/blogs-input";
    /*博客列表界面*/
    private static final String LIST = "admin/blogs";
    //重定向到博客列表界面
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        //这边写死了，后续可以调整
        PageHelper.startPage(pageNum, 8);
        List<AdminBlogsHtml> blogList = blogService.adminBlog();
        PageInfo pageInfo = new PageInfo(blogList);
        List<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("page", pageInfo);
        return LIST;
    }

    /**
     *
     * @param pageNum
     * @param model
     * @return 返回到后台博客管理页面的博客展示部分
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 8);
        List<AdminBlogsHtml> blogList = blogService.adminBlog();
        PageInfo pageInfo = new PageInfo(blogList);
        model.addAttribute("page", pageInfo);
        return "admin/blogs :: blogList";
    }


    /**
     * 添加类型和标签
     * @param model
     */
    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("tags", tagService.findAll());
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

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        User curUser = (User) session.getAttribute("user");
        //设置用户id
        blog.setUserId(curUser.getId());
        //设置blog的type属性（type的id）
        blog.setType(typeService.getTypeById(blog.getType()).getId());
        Integer b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if(b.equals(0)){
            attributes.addFlashAttribute("message", "操作失败");
        }else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
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
