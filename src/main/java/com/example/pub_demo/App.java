package com.example.pub_demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.Constants;
import com.example.config.EnvironmentConfig;
import com.google.api.core.ApiFuture;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

/**
 * Publish message on gCloud PUBSUB topic
 *
 */
public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, EnvironmentConfig.TOPIC_NAME);
		Publisher publisher = null;

		try {
			publisher = Publisher.newBuilder(topicName).build();
			String message = new String(Files.readAllBytes(Paths.get(Constants.INPUT_FILE_PATH)));

			// convert message to bytes
			ByteString data = ByteString.copyFromUtf8(message);
			PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

			ApiFuture<String> future = publisher.publish(pubsubMessage);
			LOG.info(future.get());

		} catch (IOException e) {
			LOG.error("Error in publishing message:" + e.getLocalizedMessage(), e);
		} finally {
			if (publisher != null) {
				publisher.shutdown();
			}
		}
	}
}
