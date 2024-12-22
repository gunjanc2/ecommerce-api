package com.gc2project.ecommerce.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String field;
	String fieldName;
	Long fieldId;
	
	//generate constructor with all arguments except fieldId
	public ResourceNotFoundException(String resourceName, String field, String fieldName) {
		super(String.format("%s not found with %s: %s", resourceName, field, fieldName));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldName = fieldName;
	}
	
	//generate constructor with all arguments except fieldName
	public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
		super(String.format("%s not found with %s: %d", resourceName, field, fieldId));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldId = fieldId;
	}
	
	//generate no argument constructor
	public ResourceNotFoundException() {
		super();
	}

	
}
