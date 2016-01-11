package com.prz.systemkurier.controller;

import com.prz.systemkurier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public void getMain(HttpServletResponse response) {
       try {
            response.sendRedirect("/app/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/messages/en", method = RequestMethod.GET)
    public ResponseEntity<Properties> getEnMessages() throws IOException {
        Properties properties = new Properties();
        properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_en.properties")));
        return new ResponseEntity<Properties>(properties, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/pl", method = RequestMethod.GET)
    public ResponseEntity<Properties> getPlMessages() {
        return new ResponseEntity<Properties>(HttpStatus.OK);
    }

}
