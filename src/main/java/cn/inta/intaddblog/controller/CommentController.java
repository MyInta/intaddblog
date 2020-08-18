package cn.inta.intaddblog.controller;

import cn.inta.intaddblog.service.BlogService;
import cn.inta.intaddblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author inta
 * @date 2020/8/17
 * @describe
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }


}
