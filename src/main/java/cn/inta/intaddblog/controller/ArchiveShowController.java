package cn.inta.intaddblog.controller;

import cn.inta.intaddblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author inta
 * @date 2020/8/13
 * @describe 归档
 *          难点：时间排序乱序问题，源于thymeleaf的foreach遍历map使用了iter导致
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("blogCount", blogService.blogNum());
        model.addAttribute("archiveMap", blogService.archiveBlog());
        return "archives";
    }
}
