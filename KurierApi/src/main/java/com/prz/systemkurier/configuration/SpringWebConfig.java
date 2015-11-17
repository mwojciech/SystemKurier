package com.prz.systemkurier.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@PropertySource({"classpath:application.properties", "classpath:log4j.properties"})
@ComponentScan(basePackages = "com.prz.systemkurier")
public class SpringWebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        System.out.println("startup");
        registry.addResourceHandler("/resources*//**").addResourceLocations("/resources/");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer appProperties(){
        return new PropertySourcesPlaceholderConfigurer();
    }

  /*  @Bean
    public void initLog4j() throws FileNotFoundException{
        Log4jConfigurer.initLogging("classpath:log4j.properties");
    }*/

}
