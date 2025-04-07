package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.config.RabbitMQConfig;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;

@Component
public class PedidoConsumer {
	
	 @RabbitListener(queues = RabbitMQConfig.NOME_FILA)
	    public void consumir(Pedido pedido) {
	        System.out.println("✅ Pedido processado via RabbitMQ: " + pedido.getCliente());
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

}
