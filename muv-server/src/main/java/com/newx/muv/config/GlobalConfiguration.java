package com.newx.muv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Xuzhijian on 2018/5/28 0028.
 * 默认配置
 */
@Configuration
public class GlobalConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");
    }
}
