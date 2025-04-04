package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;

public class FilaMensagens {
	private static final Queue<Pedido> fila = new LinkedList<>();
	
	public static void publicar(Pedido pedido) {
		fila.offer(pedido);
		System.out.println("Pedido enviado para a fila: " + pedido.getCliente());
	}
	
	public static Pedido consumir() {
		return fila.poll();
	}

}
