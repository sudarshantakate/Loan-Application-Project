package com.ltifinance.loanapp.documentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltifinance.loanapp.documentservice.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
