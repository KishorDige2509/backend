package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

}
