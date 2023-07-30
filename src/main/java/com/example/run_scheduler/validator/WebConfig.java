package com.example.run_scheduler.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ParamsValidatorInterceptor pvi;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pvi);
    }
}
