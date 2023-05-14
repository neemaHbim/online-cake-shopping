package com.onlineCakeShopping.App;

import com.onlineCakeShopping.App.Services.FilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class OnlineCakeShoppingAppApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(OnlineCakeShoppingAppApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
