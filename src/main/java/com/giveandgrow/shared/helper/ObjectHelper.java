package com.giveandgrow.shared.helper;

import org.springframework.stereotype.Component;

@Component
public final class ObjectHelper {
	
    public static final String EMPTY = "";

	private ObjectHelper() {
		super();
	}

	public static <T> boolean isNull(final T object) {
		return object == null;
	}

	public static <T> T getDefault(final T object, final T defaultObject) {
		return isNull(object) ? defaultObject : object;
	}
	
	public static <T> boolean isEmpty(final T object) {
		return object.equals(EMPTY);
	}
	
	
}
