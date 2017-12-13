package com.sapient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sapient.*"})
public class App 
{
	public static void main(String[] args) throws Exception 
	{
		SpringApplication.run(App.class, args);
	}
}
