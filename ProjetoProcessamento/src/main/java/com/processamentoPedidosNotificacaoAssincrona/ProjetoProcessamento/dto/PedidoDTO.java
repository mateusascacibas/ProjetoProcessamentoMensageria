package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto;
import java.util.List;

public class PedidoDTO {
    private String cliente;
    private List<ItemDTO> itens;

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public List<ItemDTO> getItens() { return itens; }
    public void setItens(List<ItemDTO> itens) { this.itens = itens; }
}
