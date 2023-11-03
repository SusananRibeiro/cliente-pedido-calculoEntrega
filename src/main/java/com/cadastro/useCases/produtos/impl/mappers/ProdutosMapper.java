package com.cadastro.useCases.produtos.impl.mappers;
import com.cadastro.entities.Produtos;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;

public class ProdutosMapper {
    public static Produtos produtosRequestDomToProdutos(ProdutosRequestDom produtosRequestDom){
        Produtos out = new Produtos();
        out.setNome(produtosRequestDom.getNome());
        out.setDescricao(produtosRequestDom.getDescricao());

        return out;
    }
    public static ProdutosResponseDom produtosToProdutosResponseDom(Produtos produtos){
        ProdutosResponseDom out = new ProdutosResponseDom();
        out.setId(produtos.getId());
        out.setNome(produtos.getNome());
        out.setDescricao(produtos.getDescricao());
        return out;
    }



}
