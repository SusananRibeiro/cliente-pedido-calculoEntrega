package com.cadastro.useCases.pedidos.impl.mappers;
import com.cadastro.entities.*;
import com.cadastro.useCases.pedidos.domanis.PedidosProdutosResponseDom;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosMapper {

    public static Pedidos pedidosResquestDomToPedidos(PedidosRequestDom pedidos, Clientes cliente, Enderecos enderecos){
        Pedidos out = new Pedidos();
        out.setDataCriacao(pedidos.getDataCriacao());
        out.setDataEntrega(pedidos.getDataEntrega());
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

        return out;
    }

    public static PedidosResponseDom pedidosToPedidosProdutosResponseDom(Pedidos pedido,
                                                                                        List<Produtos> produtos){
        PedidosResponseDom out = PedidosMapper.pedidosToPedidosResponseDom(pedido);
        List<PedidosProdutosResponseDom> produtosResponseDomList = produtos.stream()
                .map(PedidosMapper::produtosToPedidosProdutosResponseDom)
                .collect(Collectors.toList());

        out.setProdutos(produtosResponseDomList);

        return out;
    }

    public static PedidosProdutosResponseDom produtosToPedidosProdutosResponseDom(Produtos produto){
        PedidosProdutosResponseDom out = new PedidosProdutosResponseDom();
        out.setId(produto.getId());
        out.setNome(produto.getNome());
        return out;
    }


}
