package test;
import java.util.*;
import org.apache.kafka.clients.producer.*;

public class UserProducer {

	public static void main(String[] args) throws Exception {

		String topicName = "SupplierTopic";
		 String groupName = "SupplierTopicGroup";
		 
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "test.UserSerializer");
		  props.put("group.id", groupName);

		User user1 = new User("ABC1", 12);
		User user2 = new User("ABC2", 12);
		User user3 = new User("ABC3", 12);
		User user4 = new User("ABC4", 12);
		try (Producer<String, User> producer = new KafkaProducer<>(props)) {
			producer.send(new ProducerRecord<String, User>(topicName, user1));
			producer.send(new ProducerRecord<String, User>(topicName, user2));
			producer.send(new ProducerRecord<String, User>(topicName, user3));
			producer.send(new ProducerRecord<String, User>(topicName, user4));
			//System.out.println("Message " + user.toString() + " sent !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}