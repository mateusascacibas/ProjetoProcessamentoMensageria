package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ItemDTO {
	@NotBlank(message = "Nome do item é obrigatório")
	private String nome;
	
	@Min(value = 1, message = "Quantidade deve ser no mínimo 1")
	private int quantidade;
	
	public ItemDTO(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
