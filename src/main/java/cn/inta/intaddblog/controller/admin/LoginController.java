package cn.inta.intaddblog.controller.admin;

import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author inta
 * @date 2020/8/8
 * @describe
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /*直接访问admin时进行跳转到登陆页面*/
    @GetMapping
    public String loginSimple() {
        return "admin/login";
    }

    /*访问admin/login时返回登陆页面*/
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            //为了安全起见，密码隐去
            user.setPassword("");
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码不匹配");
            //重定向到登录页面
            return "redirect:/admin";
        }
    }

    /*登出管理*/
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
