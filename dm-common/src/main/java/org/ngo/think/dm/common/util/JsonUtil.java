package org.ngo.think.dm.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtil
{

	private JsonUtil()
	{
	}

	private static ObjectMapper jsonObjectMapper = new ObjectMapper();

	private static MappingJsonFactory jsonFactory = new MappingJsonFactory();

	public static <T> Object convertJsonToObject(String jsonAsString, Class<T> pojoClass) throws Exception
	{
		final DateFormat df = new SimpleDateFormat("yyyyMMdd");
		jsonObjectMapper.setDateFormat(df);
		try
		{
			return jsonObjectMapper.readValue(jsonAsString, pojoClass);
		}
		catch (JsonParseException jsonParseException)
		{
			throw jsonParseException;
		}
		catch (JsonMappingException jsonMappingException)
		{
			throw jsonMappingException;

		}
		catch (IOException ioException)
		{
			throw ioException;
		}
	}

	public static String convertObjectToJson(Object object) throws Exception
	{
		StringWriter sw = new StringWriter();

		try
		{
			JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
			jsonObjectMapper.writeValue(jsonGenerator, object);
			sw.close();
		}
		catch (Exception exception)
		{
			throw exception;
		}
		return sw.getBuffer().toString();
	}

}
