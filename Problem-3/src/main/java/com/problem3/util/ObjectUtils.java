package com.problem3.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.problem3.business.dto.ChangedFieldDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectUtils {
	

	public static List<String> getFieldNameList(Object obj) {
        List<String> fieldNames = new ArrayList<>();
        List<String> ignoredFields = new ArrayList<>(Arrays.asList("createdBy", "createdOn", "modifiedBy", "modifiedOn", "lastModifiedBy", "lastModifiedOn",
                "assetTypeId", "techSecurityMeasureId", "departmentId", "riskLevelId", "respOrganizationId",
                "assetOwnerUserId", "uuid", "status", "ACTIVE", "riskOccuranceId", "riskImpactId",
                "assetLocationId", "assetCountryId", "COMPANY_ID", "assetId", "assetDisplayId",
                "staffTimingDTO","outletAddress","profileImage","knownLanguages","accessRoles","roles",
                "emailIds","phoneNos"));
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (!ignoredFields.contains(field.getName())) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    public static List<ChangedFieldDto> getChangedFields(Object oldObject, Object newObject) {
        List<String> fieldList = getFieldNameList(oldObject);
        List<ChangedFieldDto> changedFields = new ArrayList<>();
        Class aClass = oldObject.getClass();
        for (String fieldName : fieldList) {
            try {
                Field objField = aClass.getDeclaredField(fieldName);
                try {
                    objField.setAccessible(true);
                    Object oldValue = objField.get(oldObject);
                    Object newValue = objField.get(newObject);
//                    if (oldValue != null) {
//						if (!oldValue.equals(newValue)) {
//							changedFields.add(ChangedFieldDto.builder().fieldName(FieldLabels.getFieldLabel(fieldName))
////                                    .oldValue(oldValue + "").newValue(newValue + "").build());
//									.oldValue(String.valueOf(oldValue))
//									.newValue(newValue == null ? String.valueOf(SpecialCharactersConstant.HYPHEN)
//											: String.valueOf(newValue))
//									.build());
//						}
//                    } else {
//						if (newValue != null) {
//							changedFields.add(ChangedFieldDto.builder().fieldName(FieldLabels.getFieldLabel(fieldName))
////                                    .oldValue(oldValue + "").newValue(newValue + "").build());
//									.oldValue(oldValue == null ? String.valueOf("-")
//											: String.valueOf(oldValue))
//									.newValue(String.valueOf(newValue))
//									.build());
//						}
//                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return changedFields;
    }

    public static boolean isNotNonZero(Number number) {
		log.info("inside isNotNonZero method");
        if (number != null) {
            if (number instanceof Byte) {
                log.info("byteValue : " + number.byteValue());
                return number.byteValue() > 0;
            } else if (number instanceof Short) {
                log.info("shortValue : " + number.shortValue());
                return number.shortValue() > 0;
            } else if (number instanceof Integer) {
                log.info("intValue : " + number.intValue());
                return number.intValue() > 0;
            } else if (number instanceof Long) {
                log.info("longValue : " + number.longValue());
                return number.longValue() > 0;
            } else if (number instanceof Double) {
                log.info("doubleValue : " + number.doubleValue());
                return number.doubleValue() > 0;
            } else if (number instanceof Float) {
                log.info("floatValue : " + number.floatValue());
                return number.floatValue() > 0;
            }
        }
        log.info("inside isNotNonZero, no match found");
        return Boolean.FALSE;
	}
	
	public static boolean isNullOrEmptyMap(Map < ? , ? > map) {
	    return (map == null || map.isEmpty());
	}
	
	public static boolean isNotNullOrEmptyMap(Map < ? , ? > map) {
	    return !isNullOrEmptyMap(map);
	}

}
