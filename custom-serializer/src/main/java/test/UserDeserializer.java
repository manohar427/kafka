package test;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer {
	public void close() {
	}

	public void configure(Map configs, boolean isKey) {

	}

	public User deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(arg1, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}