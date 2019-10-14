package br.com.rabbitmq.sender;

import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {


	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
				Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			for (String message : getMessages()) {
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
				System.out.println(" [x] Sent '" + message + "'");
			}
		}
	}
	
	private static List<String> getMessages() {
		List<String> messages = new ArrayList<String>();
		messages.add("Primeira mensagem");
		messages.add("Segunda mensagem");
		messages.add("Terceira mensagem");
		messages.add("Quarta mensagem");
		messages.add("Quinta mensagem");
		return messages;
	}
}
