package com.problem3.business.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface ShortToLongUrlService {

	Map<String, Object> saveUrl(String url) throws Exception;

	Map<String, Object> getByUrl(String url);

	void redirect(String unId, HttpServletResponse response) throws IOException;

}
