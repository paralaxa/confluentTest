package sk.stopangin.producer;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import java.io.IOException;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerTest {


  public static void main(final String[] args) throws IOException {

    final Properties props = new Properties();
    props.put("bootstrap.servers", "172.17.126.101:9092");

    final String topic = "schmeTest2Part";

    props.put(ProducerConfig.ACKS_CONFIG, "all");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        KafkaJsonSchemaSerializer.class);
    props.put("schema.registry.url", "http://172.17.126.101:8081");
    Producer<String, SchemaTest> producer = new KafkaProducer<>(props);

    final Long numMessages = 10L;
    for (Long i = 0L; i < numMessages; i++) {
      String key = "" + i;
      SchemaTest record = new SchemaTest(1, 2, "3");
      record.setMyField4("djsh");
      producer.send(new ProducerRecord<>(topic, key, record), (m, e) -> {
        if (e != null) {
          e.printStackTrace();
        } else {
          System.out.printf("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(),
              m.partition(), m.offset());
        }
      });
    }

    producer.flush();
    System.out.printf("10 messages were produced to topic %s%n", topic);
    producer.close();
  }

}
