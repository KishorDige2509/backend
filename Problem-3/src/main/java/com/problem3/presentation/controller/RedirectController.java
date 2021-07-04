package com.problem3.presentation.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.problem3.business.service.ShortToLongUrlService;

@RestController
public class RedirectController {
	
	@Autowired
	ShortToLongUrlService shortToLongUrlService;
	
	@GetMapping("{uniqueId}")
	public void redirect(@PathVariable("uniqueId") String unId, HttpServletResponse response) throws IOException{
		shortToLongUrlService.redirect(unId, response);
	}
	
	@PostMapping("save/url")
	public Map<String, Object> saveUrl(@RequestParam("longUrl") String url) throws Exception{
		return shortToLongUrlService.saveUrl(url);
	}
	
	@PostMapping("get/ByUrl")
	public Map<String, Object> getByUrl(@RequestParam("longUrl") String url) throws Exception{
		return shortToLongUrlService.getByUrl(url);
	}
	

}
