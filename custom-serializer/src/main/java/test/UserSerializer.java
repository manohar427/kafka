package test;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSerializer implements Serializer<User> {
	public void configure(Map configs, boolean isKey) {

	}

	public byte[] serialize(String topic, User data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public void close() {
	}
}