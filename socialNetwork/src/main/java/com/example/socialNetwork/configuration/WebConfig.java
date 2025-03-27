package com.example.socialNetwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    String frontendUrl = System.getenv("FRONTEND_URL");

    //Le doy permiso al frontendt que acceda al endpoint /uploadLogo
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (frontendUrl == null || frontendUrl.isEmpty()) {
                    frontendUrl = "http://localhost:5173"; // Default Value
                }
                registry.addMapping("/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}