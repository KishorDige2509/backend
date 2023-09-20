package com.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class FieldUtil {

	private static final String MASK = "XXXX";

	private void findEmptyAndNullFieldsInJsonNode(List<String> parentFieldNames, JsonNode node,
			Set<String> emptyMandatoryFieldsInDto) {
		if (emptyMandatoryFieldsInDto.isEmpty()) {
			emptyMandatoryFieldsInDto = new HashSet<>();
		}

		if (node.isObject()) {
			findEmptyAndNullFieldsInJsonObject(parentFieldNames, (ObjectNode) node, emptyMandatoryFieldsInDto);
		} else if (node.isArray()) {
			findEmptyAndNullFieldsInJsonArray(parentFieldNames, (ArrayNode) node, emptyMandatoryFieldsInDto);
		}
	}

	private void findEmptyAndNullFieldsInJsonObject(List<String> parentFieldNames, ObjectNode objectNode,
			Set<String> emptyMandatoryFieldsInDto) {
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

	}

	private void findEmptyAndNullFieldsInJsonArray(List<String> parentFieldNames, ArrayNode arrayNode,
			Set<String> emptyMandatoryFieldsInDto) {
		for (JsonNode element : arrayNode) {
			if (fieldIsInstanceOfObject(element)) {
				findEmptyAndNullFieldsInJsonNode(parentFieldNames, element, emptyMandatoryFieldsInDto);
			}
		}
	}

	private void maskJsonNode(List<String> parentFieldNames, JsonNode node) {
		if (node.isObject()) {
			maskObjectFields(parentFieldNames, (ObjectNode) node);
		} else if (node.isArray()) {
			maskArrayElements(parentFieldNames, (ArrayNode) node);
		}
	}

	private void maskObjectFields(List<String> parentFieldNames, ObjectNode objectNode) {
		Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			JsonNode fieldValue = field.getValue();

			if (parentFieldNames.contains(fieldName)) {
				if (!fieldIsInstanceOfObject(fieldValue)) {
					objectNode.put(fieldName, MASK);
				} else if (fieldIsInstanceOfArray(fieldValue)) {
					maskArrayElements(parentFieldNames, (ArrayNode) fieldValue);
				} else {
					maskJsonNode(parentFieldNames, fieldValue);
				}
			}
		}
	}

	private void maskArrayElements(List<String> parentFieldNames, ArrayNode arrayNode) {
		for (int i = 0; i < arrayNode.size(); i++) {
			JsonNode element = arrayNode.get(i);
			if (!fieldIsInstanceOfObject(element)) {
				arrayNode.set(i, new TextNode(MASK));
			} else {
				maskJsonNode(parentFieldNames, element);
			}
		}
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
