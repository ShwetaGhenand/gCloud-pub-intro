## Publishing Messages Using GCP PUB/SUB

A publisher application creates and sends messages to a topic. Cloud Pub/Sub offers at-least-once message delivery.

The general flow for a publisher application is:
- Create a message containing your data.
- Send a request to the Cloud Pub/Sub Server to publish the message to the desired topic.

Install [java](https://oracle.com) and [Maven](https://maven.apache.org)

###### Run ErrorCount locally
```
1. mvn clean
2. mvn install
3. mvn compile exec:java -Dexec.mainClass=com.example.pub_demo.App
```