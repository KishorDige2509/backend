package com.problem3.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FieldUtil {

	private static final String CLASSNAME = FieldUtil.class.getSimpleName();

	private static final String FIELDS_TO_CHECK = "Fields to check with:{}";

	private static final String UPDATED_EMPTY_MANDATOY_FIELDS_SET = "Updated emptyMandatoyFieldsSet:{}";

	private static final String ADDING_FIELDS_TO_EMPTY_MANDATOY_FIELDS_SET = "Adding Fields to emptyMandatoyFieldsSet ...";

	private static final String FIELD_VALUE_FOUND_NULL_OR_EMPTY = "Field Value found null or empty:{}";

	private static final String FIELD_NAME_FOUND_NULL_OR_EMPTY = "Field Name found null or empty:{}";

	private static final String FIELD_VALUE_TO_CHECK = "Field value to check:{}";

	private static final String FIELD_VALUE = "Field Value:{}";

	private static final String FIELD_NAME = "Field Name:{}";

	private static final String DTO = "DTO: {}";

	private static final String FIELD_VALUE_TO_MASK = "Field Value to mask:{}";

	private static final String FIELD_NAME_TO_MASK = "Field Name to mask:{}";

	private static final String MASK = "XXXX";

	/**
	 * 
	 * @param parentFieldNames
	 * @param requestBodyString
	 * @return
	 * @throws IOException
	 */
	public String maskNonViewableFieldsInDTOV2(List<String> parentFieldNames, String requestBodyString)
			throws IOException {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELDS_TO_CHECK, parentFieldNames);
		log.info(DTO, requestBodyString);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(requestBodyString);
		maskJsonNode(parentFieldNames, rootNode);

		log.info(LogUtil.exitLog(CLASSNAME));
		return objectMapper.writeValueAsString(rootNode);
	}

	/**
	 * 
	 * @param parentFieldNames
	 * @param requestBodyString
	 * @return
	 * @throws IOException
	 */
	public Set<String> getEmtpyMandatoryFieldsInDtoV2(List<String> parentFieldNames, String requestBodyString)
			throws IOException {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELDS_TO_CHECK, parentFieldNames);
		log.info(DTO, requestBodyString);

		Set<String> emptyMandatoryFieldsInDto = new HashSet<>();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(requestBodyString);

		findEmptyAndNullFieldsInJsonNode(parentFieldNames, rootNode, emptyMandatoryFieldsInDto);

		log.info(LogUtil.exitLog(CLASSNAME));
		return emptyMandatoryFieldsInDto;
	}

	/**
	 * 
	 * @param parentFieldNames
	 * @param requestBodyString
	 * @return
	 * @throws IOException
	 */
	public Set<String> getNonEditableFieldsInDtoV2(List<String> parentFieldNames, String requestBodyString)
			throws IOException {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELDS_TO_CHECK, parentFieldNames);
		log.info(DTO, requestBodyString);

		Set<String> nonEditableFieldsInDto = new HashSet<>();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(requestBodyString);

		findNotEditableFieldsInJsonNode(parentFieldNames, rootNode, nonEditableFieldsInDto);

		log.info(LogUtil.exitLog(CLASSNAME));
		return nonEditableFieldsInDto;

	}

	private void findEmptyAndNullFieldsInJsonNode(List<String> parentFieldNames, JsonNode node,
			Set<String> emptyMandatoryFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info("Node to check for empty mandatory fields:{}", node);
		if (null == emptyMandatoryFieldsInDto) {
			log.info("Initializing emptyMandatoryFieldsInDto set...");
			emptyMandatoryFieldsInDto = new HashSet<>();
		}

		if (node.isObject()) {
			findEmptyAndNullFieldsInJsonObject(parentFieldNames, (ObjectNode) node, emptyMandatoryFieldsInDto);
		} else if (node.isArray()) {
			findEmptyAndNullFieldsInJsonArray(parentFieldNames, (ArrayNode) node, emptyMandatoryFieldsInDto);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void findEmptyAndNullFieldsInJsonObject(List<String> parentFieldNames, ObjectNode objectNode,
			Set<String> emptyMandatoryFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info("Node to check for empty mandatory fields:{}", objectNode);
		log.info("Empty Mandatory fields in DTO:{}", emptyMandatoryFieldsInDto);
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			log.info(FIELD_NAME, fieldName);
			log.info(FIELD_VALUE, fieldValue);

			if (parentFieldNames.contains(fieldName)) {
				addFieldToSetIfEmpty(emptyMandatoryFieldsInDto, fieldName, fieldValue);
			} else {
				findEmptyAndNullFieldsInJsonNode(parentFieldNames, fieldValue, emptyMandatoryFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void addFieldToSetIfEmpty(Set<String> emptyMandatoryFieldsInDto, String fieldName, JsonNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		if (fieldIsInstanceOfObject(fieldValue)) {
			if (isAnyFieldEmptyInObject((ObjectNode) fieldValue)) {
				addFieldsInSet(emptyMandatoryFieldsInDto, fieldName, fieldValue);
			}
		} else if (fieldIsInstanceOfArray(fieldValue)) {
			if (isAnyElementEmptyInArray((ArrayNode) fieldValue)) {
				addFieldsInSet(emptyMandatoryFieldsInDto, fieldName, fieldValue);
			}
		} else if (isFieldNullOrEmpty(fieldValue)) {
			addFieldsInSet(emptyMandatoryFieldsInDto, fieldName, fieldValue);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private boolean isFieldNullOrEmpty(JsonNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELD_VALUE_TO_CHECK, fieldValue);
		boolean test = fieldValue == null || fieldValue.isNull() || fieldValue.asText().isEmpty();
		log.info("Is Field Null or Empty:{}", test);
		log.info(LogUtil.exitLog(CLASSNAME));
		return test;
	}

	private void addFieldsInSet(Set<String> emptyMandatoryFieldsInDto, String fieldName, JsonNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELD_NAME_FOUND_NULL_OR_EMPTY, fieldName);
		log.info(FIELD_VALUE_FOUND_NULL_OR_EMPTY, fieldValue);
		log.info(ADDING_FIELDS_TO_EMPTY_MANDATOY_FIELDS_SET);
		emptyMandatoryFieldsInDto.add(fieldName);
		log.info(UPDATED_EMPTY_MANDATOY_FIELDS_SET, emptyMandatoryFieldsInDto);
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private boolean isAnyElementEmptyInArray(ArrayNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELD_VALUE_TO_CHECK, fieldValue);
		boolean test = false;
		// Check each element in the array
		for (JsonNode element : fieldValue) {
			if (element == null || element.isNull() || element.asText().isEmpty()) {
				test = true;
				break;
			}
		}
		log.info("Is Field Null or Empty:{}", test);
		log.info(LogUtil.exitLog(CLASSNAME));
		return test;
	}

	private boolean isAnyFieldEmptyInObject(ObjectNode objectNode) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info("Node to check for empty mandatory fields: {}", objectNode);

		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			log.info(FIELD_NAME, fieldName);
			log.info(FIELD_VALUE, fieldValue);

			if (fieldIsInstanceOfObject(fieldValue)) {
				if (isAnyFieldEmptyInObject((ObjectNode) fieldValue)) {
					log.info(LogUtil.exitLog(CLASSNAME));
					return true;
				}
			} else if (isFieldNullOrEmpty(fieldValue)) {
				log.info(LogUtil.exitLog(CLASSNAME));
				return true;
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
		return false;
	}

	private void findEmptyAndNullFieldsInJsonArray(List<String> parentFieldNames, ArrayNode arrayNode,
			Set<String> emptyMandatoryFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info("Array Node to check for empty mandatory fields:{}", arrayNode);
		for (JsonNode element : arrayNode) {
			if (fieldIsInstanceOfObject(element)) {
				findEmptyAndNullFieldsInJsonNode(parentFieldNames, element, emptyMandatoryFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskJsonNode(List<String> parentFieldNames, JsonNode node) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info("Node to mask:{}", node);
		if (node.isObject()) {
			maskObjectFields(parentFieldNames, (ObjectNode) node);
		} else if (node.isArray()) {
			maskObjectElementsInArray(parentFieldNames, (ArrayNode) node);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskObjectFields(List<String> parentFieldNames, ObjectNode objectNode) {
		log.info(LogUtil.startLog(CLASSNAME));
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			log.info(FIELD_NAME, fieldName);
			log.info(FIELD_VALUE, fieldValue);

			if (parentFieldNames.contains(fieldName)) {
				if (fieldIsInstanceOfObject(fieldValue)) {
					maskAllFieldsInObject((ObjectNode) fieldValue);
				} else if (fieldIsInstanceOfArray(fieldValue)) {
					log.info(FIELD_NAME_TO_MASK, fieldName);
					log.info(FIELD_VALUE_TO_MASK, fieldValue);

					maskArrayElements((ArrayNode) fieldValue);
				} else {
					log.info(FIELD_NAME_TO_MASK, fieldName);
					log.info(FIELD_VALUE_TO_MASK, fieldValue);
					objectNode.put(fieldName, MASK);
				}
			} else {
				maskJsonNode(parentFieldNames, fieldValue);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskAllFieldsInObject(ObjectNode objectNode) {
		log.info(LogUtil.startLog(CLASSNAME));
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			log.info(FIELD_NAME_TO_MASK, fieldName);
			log.info(FIELD_VALUE_TO_MASK, fieldValue);
			objectNode.put(fieldName, MASK);

		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskArrayElements(ArrayNode arrayNode) {
		log.info(LogUtil.startLog(CLASSNAME));
		for (int i = 0; i < arrayNode.size(); i++) {
			arrayNode.set(i, new TextNode(MASK));
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskObjectElementsInArray(List<String> parentFieldNames, ArrayNode arrayNode) {
		log.info(LogUtil.startLog(CLASSNAME));
		for (int i = 0; i < arrayNode.size(); i++) {
			JsonNode element = arrayNode.get(i);
			if (fieldIsInstanceOfObject(element)) {
				maskJsonNode(parentFieldNames, element);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void findNotEditableFieldsInJsonNode(List<String> fieldNames, JsonNode node,
			Set<String> nonEditableFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		if (null == nonEditableFieldsInDto) {
			log.info("Initializing nonEditableFieldsInDto set...");
			nonEditableFieldsInDto = new HashSet<>();
		}

		if (node.isObject()) {
			findNotEditableFieldsInJsonObject(fieldNames, (ObjectNode) node, nonEditableFieldsInDto);
		} else if (node.isArray()) {
			findNotEditableFieldsInJsonArray(fieldNames, (ArrayNode) node, nonEditableFieldsInDto);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void findNotEditableFieldsInJsonObject(List<String> fieldNames, ObjectNode objectNode,
			Set<String> nonEditableFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			log.info(FIELD_NAME, fieldName);
			log.info(FIELD_VALUE, fieldValue);

			if (fieldNames.contains(fieldName)) {
				nonEditableFieldsInDto.add(fieldName);
			} else {
				findNotEditableFieldsInJsonNode(fieldNames, fieldValue, nonEditableFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void findNotEditableFieldsInJsonArray(List<String> fieldNames, ArrayNode arrayNode,
			Set<String> nonEditableFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		for (JsonNode element : arrayNode) {
			if (fieldIsInstanceOfObject(element)) {
				findNotEditableFieldsInJsonNode(fieldNames, element, nonEditableFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private boolean fieldIsInstanceOfObject(JsonNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELD_VALUE_TO_CHECK, fieldValue);
		boolean test = fieldValue != null && fieldValue.isObject();
		log.info("Is Field Value instance of Object:{}", test);
		return test;
	}

	private boolean fieldIsInstanceOfArray(JsonNode fieldValue) {
		log.info(LogUtil.startLog(CLASSNAME));
		log.info(FIELD_VALUE_TO_CHECK, fieldValue);
		boolean test = fieldValue != null && fieldValue.isArray();
		log.info("Is Field Value instance of Array:{}", test);
		return test;
	}

}
