package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.consumer;

import org.springframework.stereotype.Component;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.queue.FilaMensagens;

import jakarta.annotation.PostConstruct;

@Component
public class PedidoConsumer {
	
	@PostConstruct
	public void iniciar() {
		System.out.println("⏳ Aguardando pedidos...");
		new Thread(() -> {
			while (true) {
			    Pedido p = FilaMensagens.consumir();
			    if (p != null) {
			        System.out.println("✅ Pedido processado para cliente: " + p.getCliente());
			        try {
			            Thread.sleep(5000);
			        } catch (InterruptedException e) {}
			    } else {
			        try {
			            Thread.sleep(100);
			        } catch (InterruptedException e) {}
			    }
			}
		}).start();
	}
	
}
