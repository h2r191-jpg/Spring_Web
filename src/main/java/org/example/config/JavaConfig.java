package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Конфигурационный класс Spring, в котором объявляются
 * и настраиваются бины через Java‑код.
 */
@Configuration
@ComponentScan("org.example")
public class JavaConfig {
    @Bean
    //Добавляет к RequestMappingHandlerAdapter конвертер на Gson
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final var bean = new RequestMappingHandlerAdapter();
        bean.getMessageConverters().add(new GsonHttpMessageConverter());
        return bean;
    }

}