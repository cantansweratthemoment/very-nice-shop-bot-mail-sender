package olyamba;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    public static List<String> text() {
        AWSCredentials awsCredentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "";
            }

            @Override
            public String getAWSSecretKey() {
                return "";
            }
        };
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("ru-msk")
                .build();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("")
                .withWaitTimeSeconds(10)
                .withMaxNumberOfMessages(10);

        List<Message> sqsMessages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        List<String> messages = new ArrayList<>();
        for (Message message:
             sqsMessages) {
            messages.add(message.getBody());
            System.out.println(message.getBody());
        }
        for (Message sqsMessage : sqsMessages) {
            sqs.deleteMessage(new DeleteMessageRequest()
                    .withQueueUrl("")
                    .withReceiptHandle(sqsMessage.getReceiptHandle()));
        }
        return messages;
    }
}
