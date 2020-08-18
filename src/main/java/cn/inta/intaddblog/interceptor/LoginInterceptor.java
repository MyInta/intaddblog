package cn.inta.intaddblog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author inta
 * @date 2020/8/7
 * @describe 如果session中不存在user就被拦截，不让访问，返回到后台admin登录界面，
 * 需要一个配置类（自定义实现WebMvcConfigurer的WebConfig）规定 这个拦截什么时候加载
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
