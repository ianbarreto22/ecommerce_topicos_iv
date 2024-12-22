package com.vcib.ecommerce.services.fidelity;

import java.io.Serializable;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcib.ecommerce.configuration.RabbitMQConfig;

@Service
public class FidelityProducer {
	
	
	private final RabbitTemplate rabbitTemplate;
	
	public FidelityProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Long user, int bonus) {
		FidelityMessage message = new FidelityMessage(user, bonus);
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
	}
	
	public static class FidelityMessage implements Serializable  {
		private static final long serialVersionUID = 1L;
		private Long user;
		private int bonus;
		
		public FidelityMessage(Long user, int bonus) {
			super();
			this.user = user;
			this.bonus = bonus;
		}

		public Long getUser() {
			return user;
		}

		public int getBonus() {
			return bonus;
		}

		
	}

}

