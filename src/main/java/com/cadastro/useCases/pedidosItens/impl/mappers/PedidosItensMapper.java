package com.cadastro.useCases.pedidosItens.impl.mappers;
import com.cadastro.entities.Pedidos;
import com.cadastro.entities.PedidosItens;
import com.cadastro.entities.Produtos;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensProdutosResponseDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosItensMapper {
    public static PedidosItensResponseDom pedidosItensToPedidosItensResponseDom(PedidosItens pedidosItens){
        PedidosItensResponseDom out = new PedidosItensResponseDom();
        out.setId(pedidosItens.getId());
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setProdutoId(pedidosItens.getProdutoId().getId());
        out.setPedidoId(pedidosItens.getPedidoId().getId());

        return out;
    }

    public static PedidosItensResponseDom pedidosItensToPedidosItensResponseDom(PedidosItens pedidosItens,
                                                                                List<Produtos> produtos){
        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItens);
        List<PedidosItensProdutosResponseDom> produtosResponseDomList = produtos.stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensProdutosResponseDom)
                .collect(Collectors.toList());

        out.setProdutos(produtosResponseDomList);

        return out;
    }

    public static PedidosItens pedidosItensRequestDomToPedidosItens(PedidosItensRequestDom pedidosItensRequestDom,
                                                                    Produtos produtos, Pedidos pedidos){
        PedidosItens out = new PedidosItens();
        out.setQuantidade(pedidosItensRequestDom.getQuantidade());
        out.setValorUnitario(pedidosItensRequestDom.getValorUnitario());
        out.setProdutoId(produtos);
        out.setPedidoId(pedidos);

        return out;
    }

    public static PedidosItensProdutosResponseDom pedidosItensToPedidosItensProdutosResponseDom(Produtos produto){
        PedidosItensProdutosResponseDom out = new PedidosItensProdutosResponseDom();
        out.setNome(produto.getNome());

        return out;
    }
}
