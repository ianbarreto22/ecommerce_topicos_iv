package com.vcib.ecommerce.services.fidelity;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.client.FidelityClient;
import com.vcib.ecommerce.configuration.RabbitMQConfig;

import feign.FeignException;

@Service
public class FidelityConsumer {
	
	@Autowired
	private FidelityClient fidelityClient;
	
	@Autowired 
	private FidelityProducer fidelityProducer;
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void receiveMessage(FidelityProducer.FidelityMessage message) {
		try {
			System.out.println("Consumindo mensagem... " + message);
			fidelityClient.addBonus(message.getUser(), message.getBonus());
			System.out.println("Mensagem consumida com sucesso... " + message);
		} catch(FeignException e) {
			System.out.println("Tentativa falha, reenviando mensagem... " + message);
			fidelityProducer.sendMessage(message.getUser(), message.getBonus());
		}
	}
	
}
