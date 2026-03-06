package com.ltifinance.loanapp.documentservice.service;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ltifinance.loanapp.documentservice.entity.Document;
import com.ltifinance.loanapp.documentservice.repository.DocumentRepository;

@Service
public class DocumentServiecImpl implements DocumentService {

	@Value("${aws.bucket-name}")
	private String bucketName;

	private final AmazonS3 amazonS3;
	private final DocumentRepository documentRepository;

	public DocumentServiecImpl(AmazonS3 amazonS3, DocumentRepository documentRepository) {
		this.amazonS3 = amazonS3;
		this.documentRepository = documentRepository;
	}

	@Override
	public Document uploadDocument(Long customerId, Long loanId, String documentType, MultipartFile file)
			throws IOException {
		// Generate unique S3 key
		String s3Key = "loan-documents/" + loanId + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

		// Metadata
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());

		// Upload to S3
		amazonS3.putObject(new PutObjectRequest(bucketName, s3Key, file.getInputStream(), metadata));
		String fileUrl = amazonS3.getUrl(bucketName, s3Key).toString();

		// Save in DB
		Document document = new Document();
		document.setCustomerId(customerId);
		document.setLoanId(loanId);
		document.setDocumentType(documentType);
		document.setDocumentName(file.getOriginalFilename());
		document.setS3Key(s3Key);
		document.setS3Url(fileUrl);
		document.setStatus("UPLOADED");
		document.setUploadDate(LocalDate.now());
		document.setDeleted(false);
		return documentRepository.save(document);
	}

	@Override
	public String deleteDocument(Long documentId) {
		Document document = documentRepository.findById(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found"));
		amazonS3.deleteObject(bucketName, document.getS3Key());
		document.setDeleted(true);
		documentRepository.save(document);
		return "Document deleted successfully";
	}

}
