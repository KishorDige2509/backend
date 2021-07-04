package com.problem3.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.problem3.integration.domain.UrlMapping;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping, Long>{
	
	
	UrlMapping findByUrl(String longUrl);
	
	UrlMapping findByUniqueId(String uniqueId);

}
