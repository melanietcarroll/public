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
        exposeDirectory("photos", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
        System.out.println("file:/"+uploadPath +"/");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        
////        registry.addResourceHandler(
////                
////                "/photos/**")
////                
////                .addResourceLocations(
////                        
////                        "classpath:/static/photos/");
////          file:D:\\imagepath\              
//        Path uploadDirectory = Paths.get("./photos");
//        String uploadPath = uploadDirectory.toFile().getAbsolutePath();
//        registry.addResourceHandler("/photos/**").addResourceLocations("file:D:/"+ uploadPath + "/");
//      
//    }
    
}
