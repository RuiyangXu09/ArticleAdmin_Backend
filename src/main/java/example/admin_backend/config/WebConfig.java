package example.admin_backend.config;

import example.admin_backend.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 注入拦截器的方法
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加拦截器需要拦截的路径，/** 为拦截所有路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置需要拦截哪些路径addPathPatterns，不需要拦截的路径的方法是excludePathPatterns，排除路径login和register
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login", "/user/registerUser");
    }
}
