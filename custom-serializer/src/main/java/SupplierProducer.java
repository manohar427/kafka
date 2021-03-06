import java.util.*;
import org.apache.kafka.clients.producer.*;

public class SupplierProducer {

	public static void main(String[] args) throws Exception {

		String topicName = "SupplierTopic";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "SupplierSerializer");

		Producer<String, Supplier> producer = new KafkaProducer<>(props);

		for (int i = 0; i < 50; i++) {
			Supplier sp1 = new Supplier(101, "Xyz Pvt Ltd.-" + i, new Date());
			producer.send(new ProducerRecord<String, Supplier>(topicName, "SUP", sp1)).get();
			Thread.sleep(1500);
		}

		producer.close();
	}
}