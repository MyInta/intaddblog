package cn.inta.intaddblog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author inta
 * @date 2020/8/7
 * @describe
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*重写添加拦截器方法，在访问后台admin内资源时时候触发，除了登录页面，这个都被拦截，那连登录都失效了*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
