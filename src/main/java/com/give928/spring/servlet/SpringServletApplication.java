package com.give928.spring.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.File;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class SpringServletApplication {
	public static void main(String[] args) {
		File dir = new File(".");
		System.out.println("dir.getAbsolutePath() = " + dir.getAbsolutePath());
		System.out.println("dir.exists() = " + dir.exists());
		System.out.println("dir.isDirectory() = " + dir.isDirectory());
		SpringApplication.run(SpringServletApplication.class, args);
	}
}
