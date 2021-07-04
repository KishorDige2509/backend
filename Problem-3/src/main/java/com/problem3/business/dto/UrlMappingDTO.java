package com.problem3.business.dto;

public class UrlMappingDTO {
	private String shortUrl;
	private String url;
	
	public UrlMappingDTO() {
		super();
	}

	public UrlMappingDTO(String shortUrl, String url) {
		super();
		this.shortUrl = shortUrl;
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlMappingDTO [shortUrl=" + shortUrl + ", url=" + url + "]";
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	

}
