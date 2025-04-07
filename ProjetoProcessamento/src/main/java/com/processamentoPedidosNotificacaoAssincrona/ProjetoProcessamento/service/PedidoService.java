package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.ItemPedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.ValidadorPedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Validadores;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.ItemDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.PedidoDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.PedidoResponseDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.entity.ItemPedidoEntity;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.entity.PedidoEntity;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoPublisher pedidoPublisher;

    public void criarPedido(PedidoDTO dto) throws Exception {
        List<ItemPedido> itens = dto.getItens().stream()
                .map(i -> new ItemPedido(i.getNome(), i.getQuantidade()))
                .toList();

        Pedido pedido = new Pedido(dto.getCliente(), itens);
        for (ValidadorPedido validador : Validadores.obterValidadores()) {
            validador.validar(pedido);
        }

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCliente(dto.getCliente());

        List<ItemPedidoEntity> itensEntity = dto.getItens().stream()
                .map(i -> {
                    ItemPedidoEntity itemEntity = new ItemPedidoEntity();
                    itemEntity.setNome(i.getNome());
                    itemEntity.setQuantidade(i.getQuantidade());
                    itemEntity.setPedido(pedidoEntity);
                    return itemEntity;
                })
                .collect(Collectors.toList());

        pedidoEntity.setItens(itensEntity);

        pedidoRepository.save(pedidoEntity);

        pedidoPublisher.publicar(pedido);
    }
    
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream().map(entity -> {
            List<ItemDTO> itens = entity.getItens().stream()
                .map(i -> new ItemDTO(i.getNome(), i.getQuantidade()))
                .toList();

            return new PedidoResponseDTO(entity.getCliente(), itens);
        }).toList();
    }

}
