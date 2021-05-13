package sk.stopangin.producer;


import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerTest {

  public static void main(final String[] args) throws Exception {

    final String topic = "schmeTest2Part";

    final Properties props = new Properties();
    props.put("bootstrap.servers", "172.17.126.101:9092");

    // Add additional properties.
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        KafkaJsonSchemaDeserializer.class);
    props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, SchemaTest.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "someGajakaGroup");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put("schema.registry.url", "http://172.17.126.101:8081");

    final Consumer<String, SchemaTest> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList(topic));

    Long total_count = 0L;

    try {
      while (true) {
        ConsumerRecords<String, SchemaTest> records = consumer.poll(100);
        for (ConsumerRecord<String, SchemaTest> record : records) {
          String key = record.key();
          SchemaTest value = record.value();
          total_count += value.getMyField1();
          System.out
              .printf("Consumed record with key %s and value %s, and updated total count to %d%n",
                  key, value, total_count);
        }
      }
    } finally {
      consumer.close();
    }
  }


  public static Properties loadConfig(String configFile) throws IOException {
    if (!Files.exists(Paths.get(configFile))) {
      throw new IOException(configFile + " not found.");
    }
    final Properties cfg = new Properties();
    try (InputStream inputStream = new FileInputStream(configFile)) {
      cfg.load(inputStream);
    }
    return cfg;
  }

}