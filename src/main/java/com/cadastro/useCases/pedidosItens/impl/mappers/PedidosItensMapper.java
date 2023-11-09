package com.cadastro.useCases.pedidosItens.impl.mappers;
import com.cadastro.entities.*;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensProdutosResponseDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import java.util.List;
import java.util.stream.Collectors;

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
        out.setNomeDoProduto(pedidosItens.getProdutoId().getNome());

        return out;
    }

//    public static PedidosItensResponseDom pedidosItensToPedidosItensProdutosResponseDom(PedidosItens pedidosItens,
//                                                                                        List<Produtos> produtos){
//        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItens);
//        List<PedidosItensProdutosResponseDom> produtosResponseDomList = produtos.stream()
//                .map(PedidosItensMapper::produtosToPedidosItensProdutosResponseDom)
//                .collect(Collectors.toList());
//
//        out.setProdutosLista(produtosResponseDomList);
//
//        return out;
//    }
//
//    public static PedidosItensProdutosResponseDom produtosToPedidosItensProdutosResponseDom(Produtos produto){
//        PedidosItensProdutosResponseDom out = new PedidosItensProdutosResponseDom();
//        out.setNome(produto.getNome());
//
//        return out;
//    }
}
