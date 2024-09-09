package com.demo.robot_cleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.demo.robot_cleaner"})
public class RobotCleanerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RobotCleanerApplication.class, args);
	}
}