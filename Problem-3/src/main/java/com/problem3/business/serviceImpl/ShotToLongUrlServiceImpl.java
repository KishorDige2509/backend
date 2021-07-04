package com.problem3.business.serviceImpl;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.problem3.business.dto.UrlMappingDTO;
import com.problem3.business.service.ShortToLongUrlService;
import com.problem3.integration.domain.UrlMapping;
import com.problem3.integration.repository.UrlRepository;

@Component
public class ShotToLongUrlServiceImpl implements ShortToLongUrlService {

	@Autowired
	UrlRepository urlRepository;
	
	@Value("${custom.base.url}")
	public String baseUrl;

	final String SUCCESS = "SUCCESS";
	final String ERROR = "ERROR";
	Integer lengthOfId = 6;
	private static char[] characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	@Override
	public Map<String, Object> saveUrl(String url) throws Exception {

		Map<String, Object> map = new HashMap<>();

		UrlMapping obj = null;
		
		if (!url.isEmpty()) {

			obj = urlRepository.findByUrl(url);

			if (obj == null) {
				obj = new UrlMapping();
				Map<String, String> uniqueMap = shortenTheUrl(url);

				if (!uniqueMap.isEmpty()) {
					obj.setShortUrl(uniqueMap.get("shortenUrl"));
					obj.setUrl(url);
					obj.setUniqueId(uniqueMap.get("uniqueId"));
				
					obj = urlRepository.save(obj);
					
					map.put("code", 200);
					map.put("status", SUCCESS);
					map.put("message", "Url shortened and saved successfully");
					map.put("response", obj);
				}

			} else {
				map.put("code", 500);
				map.put("status", ERROR);
				map.put("message", "Url already exists");
				map.put("response", null);
			}

		}

		
		return map;
	}
	
	
	@Override
	public Map<String, Object> getByUrl(String url) {
		Map<String, Object> map = new HashMap<>();
		
		UrlMappingDTO dto = null;
		
		UrlMapping obj = urlRepository.findByUrl(url);
		
		if(obj != null) {
			dto = new UrlMappingDTO();
			BeanUtils.copyProperties(obj, dto);
			map.put("code", 200);
			map.put("status", SUCCESS);
			map.put("message", "Url found");
			map.put("response", dto);
		} else {
			map.put("code", 500);
			map.put("status", ERROR);
			map.put("message", "No such url exists");
			map.put("response", dto);
		}		
		return map;
	}
	
	
	@Override
	public void redirect(String unId, HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<>();
		UrlMapping obj = urlRepository.findByUniqueId(unId);
		
		if(obj != null) {
		
			response.sendRedirect(obj.getUrl());

		} else {
			map.put("code", 500);
			map.put("status", ERROR);
			map.put("message", "No such url exists");
			
		}		
		
	}
	
	

	private Map<String, String> shortenTheUrl(String url) {
		
		Map<String, String> map = new HashMap<>();
		
		String uniqueId = uniqueId();
		
		String shortenUrl = baseUrl.concat(uniqueId);
		
		map.put("uniqueId", uniqueId);
		map.put("shortenUrl", shortenUrl);

		return map;
	}

	public String uniqueId() {

		String code = "rx" + randomString(lengthOfId);
		UrlMapping objUrl = urlRepository.findByUniqueId(code);
	
		while (objUrl != null) {
			code = "rx" + randomString(lengthOfId);
			objUrl = urlRepository.findByUniqueId(code);
		}
		return code;
	}

	public static String randomString(int length) {

		SecureRandom random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}

		return new String(result);
	}



}
