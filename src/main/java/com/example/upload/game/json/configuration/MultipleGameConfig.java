package com.example.upload.game.json.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MultipleGameConfig implements WebMvcConfigurer {
    private final String CLIENT_PATH="/images/**";
    private final String SERVER_PATH="src/main/resources/images/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("chento love you");
        registry.addResourceHandler(CLIENT_PATH)
                .addResourceLocations("file:"+SERVER_PATH);
//        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
