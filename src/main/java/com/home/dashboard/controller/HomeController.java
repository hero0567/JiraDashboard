package com.home.dashboard.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HomeController {
	
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.setHeader("Access-Control-Allow-Methods","GET,POST");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type,authorization");
		return "home";
	}
	
	
	@RequestMapping("/license")
	public String license(){
		
		return "license";
	}	
	
	@RequestMapping("/dashboard")
	public String dashboard(){
		
		return "dashboard";
	}
	
	@RequestMapping("/license/generate")
	public @ResponseBody String generate(@RequestParam("components") String components) throws IOException{
		String cmds = "echo " +components + " | base64";		
		Process proc = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", cmds});
		InputStream inputStream = proc.getInputStream();
		BufferedReader bufferedRreader = new BufferedReader(new InputStreamReader(inputStream));	
		String line;
		String result = "";
		while ((line = bufferedRreader.readLine()) != null){
			result = result + line;
		}
		return result;
	}
}
