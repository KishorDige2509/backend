package com.problem3.integration.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mappingId;

	private String shortUrl;
	private String url;
	private String uniqueId;

	public UrlMapping() {
		super();
	}

	public UrlMapping(Long mappingId, String shortUrl, String url, String uniqueId) {
		super();
		this.mappingId = mappingId;
		this.shortUrl = shortUrl;
		this.url = url;
		this.uniqueId = uniqueId;
	}

	@Override
	public String toString() {
		return "UrlMapping [mappingId=" + mappingId + ", shortUrl=" + shortUrl + ", url=" + url + ", uniqueId="
				+ uniqueId + "]";
	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
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

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	

	

}
