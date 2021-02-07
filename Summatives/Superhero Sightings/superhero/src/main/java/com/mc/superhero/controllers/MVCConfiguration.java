/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.controllers;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Melanie Carroll
 */
@Configuration
public class MVCConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDirectory = Paths.get("./superhero-photos");
        String uploadPath = uploadDirectory.toFile().getAbsolutePath();
        registry.addResourceHandler("/superhero-photos/**").addResourceLocations("file:/"+ uploadPath + "/");
      
    }
    
}
