package com.hakon.demo;

import com.hakon.demo.service.CleverDictionary;
import com.hakon.demo.service.RegexDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	CleverDictionary cleverDictionary;
	@Autowired
	RegexDictionary regexDictionary;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void setup() throws IOException {
		cleverDictionary.createCleverDictionary();
		regexDictionary.load();
	}
}
