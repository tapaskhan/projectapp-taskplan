package com.taskplan.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.taskplan.service.impl.UserService;
import com.taskplan.service.util.UserMapper;

@Configuration
@ComponentScan("com.taskplan")
public class TestConfig {

	 @Bean
     public UserService userService(ApplicationContext context) {
		 UserService userService = new UserService();
		 
         // set properties, etc.
         return userService;
     }
	 @Bean
     public UserMapper userMapper(ApplicationContext context) {
		 UserMapper userMapper = new UserMapper();
		 
         // set properties, etc.
         return userMapper;
     }
}
