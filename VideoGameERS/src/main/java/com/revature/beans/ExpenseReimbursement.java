package com.revature.beans;

import java.sql.Blob;
import java.sql.Timestamp;

public class ExpenseReimbursement {
	
	private int id;
	private double amount;
	private String description;
	private byte[] receipt;
	private Timestamp submitted;
	private Timestamp resolved;
	private String author;
	private int resolver;
	private String resolverUsername;
	private String erType;
	private String erStatus;
	private String base64Image;
	private byte[] receiptArray; 
	
	public ExpenseReimbursement(){}

	public ExpenseReimbursement(int id, double amount, String description, byte[] receipt, Timestamp submitted,
			Timestamp resolved, String author, int resolver, String erType, String erStatus) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.erType = erType;
		this.erStatus = erStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[]  getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[]  receipt) {
		this.receipt = receipt;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getAuthorId() {
		return author;
	}

	public String getResolverUsername() {
		return resolverUsername;
	}

	public void setResolverUsername(String resolverUsername) {
		this.resolverUsername = resolverUsername;
	}

	public void setAuthorId(String authorId) {
		this.author = authorId;
	}

	public int getResolverId() {
		return resolver;
	}

	public void setResolverId(int resolverid) {
		this.resolver = resolverid;
	}

	public String getErType() {
		return erType;
	}

	public void setErType(String erType) {
		this.erType = erType;
	}

	public String getErStatus() {
		return erStatus;
	}

	public void setErStatus(String erStatus) {
		this.erStatus = erStatus;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public byte[] getReceiptArray() {
		return receiptArray;
	}

	public void setReceiptArray(byte[] receiptArray) {
		this.receiptArray = receiptArray;
	}

	@Override
	public String toString() {
		return "ExpenseReinbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", receipt="
				+ receipt + ", submitted=" + submitted + ", resolved=" + resolved + ", authorId=" + author
				+ ", resolverid=" + resolver + ", erType=" + erType + ", erStatus=" + erStatus + "]";
	}
	
}
