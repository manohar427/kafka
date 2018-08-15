import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ManualConsumer {

	public static void main(String[] args) throws Exception {

		String topicName = "SupplierTopic";
		String groupName = "SupplierTopicGroup";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("group.id", groupName);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "SupplierDeserializer");
		props.put("enable.auto.commit", "false");

		KafkaConsumer<String, Supplier> consumer = null;

		try {
			consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Arrays.asList(topicName));

			while (true) {
				ConsumerRecords<String, Supplier> records = consumer.poll(100);
				for (ConsumerRecord<String, Supplier> record : records) {
					System.out.println("Supplier id= " + String.valueOf(record.value().getID()) + " Supplier  Name = "
							+ record.value().getName() + " Supplier Start Date = "
							+ record.value().getStartDate().toString());
				}
				consumer.commitAsync();// not wait for commit response
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			consumer.commitSync();// wait for commit response
			consumer.close();
		}
	}
}