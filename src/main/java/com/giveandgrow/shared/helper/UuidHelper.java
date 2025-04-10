package com.giveandgrow.shared.helper;

import java.util.UUID;

public class UuidHelper {
	
	public static final String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-000000000000";
    public static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

    private UuidHelper() {
        super();
    }

    public static UUID generateNewUuid() {
        return UUID.randomUUID();
    }

    public static UUID getDefaultUuid(final UUID uuidValue) {
        return (isUuidNull(uuidValue)) ? DEFAULT_UUID : uuidValue;
    }

    public static boolean isDefaultOrNull(final UUID uuidValue) {
        return (isUuidNull(uuidValue) || uuidValue.equals(DEFAULT_UUID));
    }
    
    public static boolean isDefault(final UUID uuidValue) {
        return (!isUuidNull(uuidValue) && uuidValue.equals(DEFAULT_UUID));
    }

    public static boolean isUuidNull(final UUID uuidValue) {

        return ObjectHelper.isNull(uuidValue);
    }
    
    public static boolean isUuidEmpty(final UUID uuidValue) {
    	return ObjectHelper.isEmpty(uuidValue);
    }
}

