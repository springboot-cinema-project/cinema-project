package com.movie.config;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Value("${file.upload.poster-dir}")
    private String posterUploadDir;

    @Value("${file.upload.event-dir}")
    private String eventUploadDir;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/poster/**")
                .addResourceLocations("file:C:/upload/poster/");
        registry.addResourceHandler("/event/**")
                .addResourceLocations("file:C:/upload/event/");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:C:/upload/");
    }

}
