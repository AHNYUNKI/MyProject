package com.api.TopicTraverse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://3.39.250.43:8080", "http://3.39.250.43:3000") // 이 부분을 수정
                .allowedMethods("GET", "POST", "PATCH", "DELETE"); // 필요한 HTTP 메서드 지정
    }

}
