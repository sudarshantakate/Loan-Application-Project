package com.ltifinance.loanapp.documentservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "documents")
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private Long customerId;
    private Long loanId;

    private String documentType;
    private String documentName;

    private String s3Key;
    private String s3Url;

    private String status;  // UPLOADED, VERIFIED, REJECTED

    private LocalDate uploadDate;

    private boolean deleted = false;
}
