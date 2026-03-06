package com.ltifinance.loanapp.documentservice.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.ltifinance.loanapp.documentservice.entity.Document;

public interface DocumentService {
	
	Document uploadDocument(Long customerId, Long loanId, String documentType, MultipartFile file) throws IOException;

	String deleteDocument(Long documentId);
}
