/**
 * 
 */
package za.co.sindi.commons.javaee.utils;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 * @author buhake.sindi
 * @since 2016/07/25
 *
 */
public final class JsonUtils {
	
	private JsonUtils() {
		throw new AssertionError("Private Constructor.");
	}
	
	public static void write(JsonArray jsonArray, Writer writer) {
		write(jsonArray, writer, false);
	}

	public static void write(JsonObject jsonObject, Writer writer) {
		write(jsonObject, writer, false);
	}
	
	public static void write(JsonArray jsonArray, Writer writer, boolean prettyPrint) {
		JsonWriter jsonWriter = javax.json.Json.createWriter(writer);
		if (prettyPrint) {
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory writerFactory = Json.createWriterFactory(propertyMap);
			jsonWriter = writerFactory.createWriter(writer);
		}
		jsonWriter.writeArray(jsonArray);
	}

	public static void write(JsonObject jsonObject, Writer writer, boolean prettyPrint) {
		JsonWriter jsonWriter = javax.json.Json.createWriter(writer);
		if (prettyPrint) {
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory writerFactory = Json.createWriterFactory(propertyMap);
			jsonWriter = writerFactory.createWriter(writer);
		}
		jsonWriter.writeObject(jsonObject);
	}
	
	public static String toString(JsonArray jsonArray) {
		return toString(jsonArray, false);
	}
	
	public static String toString(JsonObject jsonObject) {
		return toString(jsonObject, false);
	}
	
	public static String toString(JsonArray jsonArray, boolean prettyPrint) {
		StringWriter writer = new StringWriter();
		write(jsonArray, writer, prettyPrint);
		return writer.toString();
	}
	
	public static String toString(JsonObject jsonObject, boolean prettyPrint) {
		StringWriter writer = new StringWriter();
		write(jsonObject, writer, prettyPrint);
		return writer.toString();
	}
}
