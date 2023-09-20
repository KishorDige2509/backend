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
		if (emptyMandatoryFieldsInDto.isEmpty()) {
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
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			if (parentFieldNames.contains(fieldName) && fieldIsNullOrEmpty(fieldValue)) {
				emptyMandatoryFieldsInDto.add(fieldName);
			} else {
				findEmptyAndNullFieldsInJsonNode(parentFieldNames, fieldValue, emptyMandatoryFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void findEmptyAndNullFieldsInJsonArray(List<String> parentFieldNames, ArrayNode arrayNode,
			Set<String> emptyMandatoryFieldsInDto) {
		log.info(LogUtil.startLog(CLASSNAME));
		for (JsonNode element : arrayNode) {
			if (fieldIsInstanceOfObject(element)) {
				findEmptyAndNullFieldsInJsonNode(parentFieldNames, element, emptyMandatoryFieldsInDto);
			}
		}
		log.info(LogUtil.exitLog(CLASSNAME));
	}

	private void maskJsonNode(List<String> parentFieldNames, JsonNode node) {
		log.info(LogUtil.startLog(CLASSNAME));
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

			log.info("Field Name:{}", fieldName);
			log.info("Field Value:{}", fieldValue);

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
		if (nonEditableFieldsInDto.isEmpty()) {
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

			if (fieldNames.contains(fieldName) && !fieldIsInstanceOfObject(fieldValue)) {
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

	private boolean fieldIsNullOrEmpty(JsonNode fieldValue) {
		return fieldValue == null || fieldValue.isNull() || fieldValue.asText().isEmpty();
	}

	private boolean fieldIsInstanceOfObject(JsonNode fieldValue) {
		return fieldValue != null && fieldValue.isObject();
	}

	private boolean fieldIsInstanceOfArray(JsonNode fieldValue) {
		return fieldValue != null && fieldValue.isArray();
	}

}
