package com.cadastro.useCases.pedidosItens.impl.mappers;
import com.cadastro.entities.*;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;

public class PedidosItensMapper {

    public static PedidosItens pedidosItensResquestDomToPedidosItens(PedidosItensRequestDom pedidosItens,
                                                                     Produtos produto, Pedidos pedido){
        PedidosItens out = new PedidosItens();
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setProdutoId(produto);
        out.setPedidoId(pedido);

        return out;
    }

    public static PedidosItensResponseDom pedidosItensToPedidosItensResponseDom(PedidosItens pedidosItens){
        PedidosItensResponseDom out = new PedidosItensResponseDom();
        out.setId(pedidosItens.getId());
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setProdutoId(pedidosItens.getProdutoId().getId());
        out.setPedidoId(pedidosItens.getPedidoId().getId());

        return out;
    }

}
