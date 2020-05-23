package com.template.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.template.common.exception.JsonDeserializationException;
import com.template.common.exception.JsonSerializationException;

public class JsonUtil {
	private JsonUtil() {
	}

	private static final ObjectMapper mapper = new ObjectMapper();;
	static {
		mapper.setSerializationInclusion(Include.NON_EMPTY);
	}

	public static String toJson(Object obj) throws JsonSerializationException {
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new JsonSerializationException(e);
		}
		return json;
	}

	public static <T> T toObject(String json, Class<T> clazz) throws JsonDeserializationException {
		T t = null;
		try {
			t = mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new JsonDeserializationException(e);
		}
		return t;
	}

	public static <T> List<T> toList(String json, Class<T> clazz) throws JsonDeserializationException {

		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		List<T> list = null;
		try {
			list = mapper.readValue(json, listType);
		} catch (IOException e) {
			throw new JsonDeserializationException(e);
		}
		return list;
	}

	/**
	 * Based on https://stackoverflow.com/a/49564514/510017
	 *
	 * @param unformattedJsonString JSON string that has not been formatted at all.
	 * @return A best-effort at pretty printed JSON, even for malformed JSON.
	 */
	public static String prettyPrint(String unformattedJsonString) {
		StringBuilder sb = new StringBuilder();
		int indentLevel = 0;
		boolean inQuote = false;
		for (char charFromUnformattedJson : unformattedJsonString.toCharArray()) {
			switch (charFromUnformattedJson) {
			case '"':
				// switch the quoting status
				inQuote = !inQuote;
				sb.append(charFromUnformattedJson);
				break;
			case ' ':
				// For space: ignore the space if it is not being quoted.
				if (inQuote) {
					sb.append(charFromUnformattedJson);
				}
				break;
			case '{':
			case '[':
				// Starting a new block: increase the indent level
				sb.append(charFromUnformattedJson);
				indentLevel++;
				appendIndentedNewLine(indentLevel, sb);
				break;
			case '}':
			case ']':
				// Ending a new block; decrese the indent level
				indentLevel--;
				appendIndentedNewLine(indentLevel, sb);
				sb.append(charFromUnformattedJson);
				break;
			case ',':
				// Ending a json item; create a new line after
				sb.append(charFromUnformattedJson);
				if (!inQuote) {
					appendIndentedNewLine(indentLevel, sb);
				}
				break;
			default:
				sb.append(charFromUnformattedJson);
			}
		}
		return sb.toString();
	}

	/**
	 * Print a new line with indention at the beginning of the new line.
	 *
	 * @param indentLevel
	 * @param stringBuilder
	 */
	private static void appendIndentedNewLine(int indentLevel, StringBuilder stringBuilder) {
		stringBuilder.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			// Assuming indention using 2 spaces
			stringBuilder.append("  ");
		}
	}
}
