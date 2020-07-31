//package com.example.demosubchildtwo.config;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CustomerWebMvcConfigurer implements WebMvcConfigurer {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("main");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> excludePatterns = Arrays.asList("/error", "/static/**", "/ajax/**", "/css/**",
//                "/file/**", "/fonts/**", "/i18n/**", "/img/**", "/js/**");
////		registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePatterns);
//    }
//
//}