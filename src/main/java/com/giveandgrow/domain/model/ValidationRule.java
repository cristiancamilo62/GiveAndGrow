package com.giveandgrow.domain.model;

public interface ValidationRule<T> {
	
	void validate(T data);

}
