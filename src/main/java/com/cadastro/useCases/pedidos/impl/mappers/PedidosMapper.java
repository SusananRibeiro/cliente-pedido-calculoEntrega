package com.cadastro.useCases.pedidos.impl.mappers;
import com.cadastro.entities.*;
import com.cadastro.useCases.pedidos.domanis.PedidosPedidosItensResponse;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosMapper {

    public static Pedidos pedidosResquestDomToPedidos(PedidosRequestDom pedidos, Clientes cliente, Enderecos enderecos){
        Pedidos out = new Pedidos();
        out.setDataCriacao(LocalDateTime.now());
        out.setDataEntrega(calcularDataDeEntrega(LocalDateTime.now()));
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(cliente);
        out.setEnderecoId(enderecos);
        return out;
    }

    public static PedidosResponseDom pedidosToPedidosResponseDom(Pedidos pedidos){
        PedidosResponseDom out = new PedidosResponseDom();
        out.setId(pedidos.getId());
        out.setDataCriacao(pedidos.getDataCriacao());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(pedidos.getClienteId().getId());
        out.setEnderecoId(pedidos.getEnderecoId().getId());
        out.getValorTotal();
        return out;
    }

    public static PedidosResponseDom pedidosToPedidosProdutosResponseDom(Pedidos pedido,
                                                                         List<PedidosItens> pedidosItens){
        PedidosResponseDom out = PedidosMapper.pedidosToPedidosResponseDom(pedido);
        List<PedidosPedidosItensResponse> pedidosPedidosItensResponseList = pedidosItens.stream()
                .map(PedidosMapper::pedidosItensToPedidosProdutosResponseDom)
                .collect(Collectors.toList());
        out.setPedidosItens(pedidosPedidosItensResponseList);
        return out;
    }

    public static PedidosPedidosItensResponse pedidosItensToPedidosProdutosResponseDom(PedidosItens pedidosItens){
        PedidosPedidosItensResponse out = new PedidosPedidosItensResponse();
        out.setId(pedidosItens.getId());
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setProdutoId(pedidosItens.getProdutoId().getId());
        out.setPedidoId(pedidosItens.getPedidoId().getId());
        out.setNomeDoProduto(pedidosItens.getProdutoId().getNome());
        return out;
    }
    public static LocalDate calcularDataDeEntrega(LocalDateTime dataCriacao) {
        // Adiciona 15 dias à data de criação
        LocalDateTime dataEntrega = dataCriacao.plus(15, ChronoUnit.DAYS);
        // Extrai a parte da data (ignorando a parte do tempo)
        return dataEntrega.toLocalDate();
    }

}