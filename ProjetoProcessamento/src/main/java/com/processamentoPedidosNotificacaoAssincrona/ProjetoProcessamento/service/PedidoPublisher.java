package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.config.RabbitMQConfig;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;

@Service
public class PedidoPublisher {
	private final RabbitTemplate rabbitTemplate;

    public PedidoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicar(Pedido pedido) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOME_FILA, pedido);
        System.out.println("📤 Pedido enviado via RabbitMQ: " + pedido.getCliente());
    }

}
