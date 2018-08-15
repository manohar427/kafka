package test;

import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class UserConsumer{

        public static void main(String[] args) throws Exception{

                String topicName = "SupplierTopic";
                String groupName = "SupplierTopicGroup";

                Properties props = new Properties();
                props.put("bootstrap.servers", "localhost:9092");
                props.put("group.id", groupName);
                props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.put("value.deserializer", "test.UserDeserializer");


                try (KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props)) {
                    consumer.subscribe(Collections.singletonList(topicName));
                    while (true) {
                        ConsumerRecords<String, User> messages = consumer.poll(100);
                        for (ConsumerRecord<String, User> message : messages) {
                          System.out.println("Message received " + message.value().toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
}