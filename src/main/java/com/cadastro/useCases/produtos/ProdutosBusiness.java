package com.cadastro.useCases.produtos;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;
import java.util.List;

public interface ProdutosBusiness {
    public List<ProdutosResponseDom> carregarProdutos();
    ProdutosResponseDom criarProduto(ProdutosRequestDom produtosRequestDom) throws Exception;
    ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException;
    void deletarProdutos(Long id);
    Produtos carregarProdutoEntidade(Long id);
    ProdutosResponseDom carregarProdutoById(Long id);
}
