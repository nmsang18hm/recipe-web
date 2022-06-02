package com.uet.recipeweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.recipeweb.util.AwsUtils;

@RestController
public class TestAPI {
	@Autowired
	AwsUtils awsUtils;
	
	@GetMapping("/test")
	public String test() {
		return awsUtils.generateLink("1650894070133");
	}
}
