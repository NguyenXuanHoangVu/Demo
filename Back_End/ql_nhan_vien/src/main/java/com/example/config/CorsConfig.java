package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // class config cua springboot
public class CorsConfig implements WebMvcConfigurer{ // trien khai interface WebMvcConfigurer

    //@Override
    public void corsMapping(CorsRegistry registry){ //dinh nghia phuong thuc corsMapping
        registry.addMapping("/**") // ap dung voi tat ca cac endpoint
                .allowedOrigins("http://localhost:4200") // cho phep ket noi voi Angular
                .allowedMethods("GET", "POST", "PUT") // chi cho phep doc/them/sua du lieu
                .allowedHeaders("Content-Type", "Authorization") // chi cho phep 2 loai header nay
                .allowCredentials(false); // khong cho phep cookies
    }
}
