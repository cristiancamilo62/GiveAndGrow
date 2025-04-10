package com.giveandgrow.shared.helper;

public class LongHelper {
	
	private LongHelper() {
		super();
	}
	public static final Long DEFAULT_LONG = 0L;
	
	public static boolean isNull( final Long valor) {
		return ObjectHelper.isNull(valor);
	}
	
	public static  boolean isValueByDefault(final Long valor) {
	    return !isNull(valor) && valor.equals(DEFAULT_LONG);
	}

}
