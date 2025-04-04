package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core;

@FunctionalInterface
public interface ValidadorPedido {
	
	void validar(Pedido pedido) throws Exception;

}
