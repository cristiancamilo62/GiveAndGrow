package com.giveandgrow.shared.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class TextHelper {

    public static final String EMPTY = "";
    private static final String PATTERN_ONLY_DIGITS = "\\d+";
    private static final String PATTERN_ONLY_LETTERS = "^[A-Za-záéíóúÁÉÍÓÚ]+$";
    private static final String PATTERN_ONLY_LETTERS_DIGITS = "^[0-9A-Za-záéíóúÁÉÍÓÚ]+$";
    private static final String PATTERN_ONLY_LETTERS_DIGITS_SPACES = "^[0-9A-Za-záéíóúÁÉÍÓÚ ]+$";
    private static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$";
    private static final String PATTERN_DATE = "^([0-2][0-9]|3[01])/([0][1-9]|1[0-2])/\\d{4}$";

    private static final String PATTERN_ONLY_LETTERS_DIGITS_AT_DOMAIN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private TextHelper() {
        super();
    }

    public static boolean isNull(final String string) {
        return ObjectHelper.isNull(string);
    }

    public static boolean isNullOrEmpty(final String string) {
        return isNull(string) || EMPTY.equals(applyTrim(string));
    }

    public static String applyTrim(final String string) {
        return getDefault(string).trim();
    }

    public static String getDefault(final String string, final String defaultValue) {
        return ObjectHelper.getDefault(string, defaultValue);
    }

    public static String getDefault(final String string) {
        return getDefault(string, EMPTY);
    }

    public static String concatenate(final String... strings) {
        return Arrays.stream(ObjectHelper.getDefault(strings, new String[0])).map(string -> string)
                .collect(Collectors.joining());
    }
    
    public static boolean sameWithTrim(final String valorUno, final String valorDos) {
		return applyTrim(valorUno).equals(applyTrim(valorDos));
	}
    
    public static boolean isEmpty(final String value) {
		return sameWithTrim(value, EMPTY);
	}
    
    public static boolean isValidPassword(final String value) {
        return applyTrim(value).matches(PASSWORD);
    }
    
    public static boolean isValidDateTime(final String value) {
        return value.matches(PATTERN_DATE);
    }
    
    public static boolean isValidMinimumLength(final String value, final int minimumLength) {
        return applyTrim(value).length() >= minimumLength;
    }

    public static boolean isValidMaximumLength(final String value, final int maximumLength) {
        return applyTrim(value).length() <= maximumLength;
    }

    public static boolean isExactLength(final String value, final int length) {
        return applyTrim(value).length() == length;
    }

    public static boolean isValidLength(final String value, final int minimumLength, final int maximumLength) {
        return isValidMinimumLength(value, minimumLength) && isValidMaximumLength(value, maximumLength);
    }

    public static boolean containsOnlyLetters(final String value) {
        return applyTrim(value).matches(PATTERN_ONLY_LETTERS);
    }

    public static boolean containsOnlyLettersDigitsSpaces(final String value) {
        return applyTrim(value).matches(PATTERN_ONLY_LETTERS_DIGITS_SPACES);
    }

    public static boolean containsOnlyLettersDigitsAtDomain(final String value) {
        return applyTrim(value).matches(PATTERN_ONLY_LETTERS_DIGITS_AT_DOMAIN);
    }

    public static boolean containsOnlyDigits(final String value) {
        return applyTrim(value).matches(PATTERN_ONLY_DIGITS);
    }

    public static boolean containsOnlyLettersDigits(final String value) {
        return applyTrim(value).matches(PATTERN_ONLY_LETTERS_DIGITS);
    }
}
