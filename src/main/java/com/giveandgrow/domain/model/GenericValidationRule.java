package com.giveandgrow.domain.model;

public interface GenericValidationRule<T> {
	
	void validate(T data);

}
