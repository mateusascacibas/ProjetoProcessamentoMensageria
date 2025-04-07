package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class PedidoDTO {
	@NotBlank(message = "Cliente é obrigatório")
	private String cliente;
	
	@NotEmpty(message = "É necessário ao menos um item no pedido")
	private List<ItemDTO> itens;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<ItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}
}
