package com.ltifinance.loanapp.documentservice.documentcontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ltifinance.loanapp.documentservice.entity.Document;
import com.ltifinance.loanapp.documentservice.service.DocumentService;

@RestController
@RequestMapping("document")
public class DocumnetController {

	@Autowired
	private DocumentService documentService;

	public DocumnetController(DocumentService documentService) {
		this.documentService = documentService;
	}

	@PostMapping("/api/uploaddocument")
	public ResponseEntity<Document> uploadDocument(@RequestParam Long customerId, @RequestParam Long loanId,
			@RequestParam String documentType, @RequestParam("file") MultipartFile file) throws IOException {

		return ResponseEntity.ok(documentService.uploadDocument(customerId, loanId, documentType, file));
	}

	@DeleteMapping("/api/deletedocument/{documentId}")
	public ResponseEntity<String> deleteDocument(@PathVariable Long documentId){
		
		return ResponseEntity.ok(
	            documentService.deleteDocument(documentId));
	}

}
