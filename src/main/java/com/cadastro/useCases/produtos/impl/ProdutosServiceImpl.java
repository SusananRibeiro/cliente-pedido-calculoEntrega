package com.cadastro.useCases.produtos.impl;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.produtos.ProdutosService;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProdutosServiceImpl implements ProdutosService {
    @Autowired
    private ProdutosBusinessImpl produtosBusiness;

    @Override
    public List<ProdutosResponseDom> carregarProdutos() {
        return produtosBusiness.carregarProdutos();
    }

    @Override
    public ProdutosResponseDom criarProduto(ProdutosRequestDom produtosRequestDom) throws Exception {
        return produtosBusiness.criarProduto(produtosRequestDom);
    }

    @Override
    public ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException {
        return produtosBusiness.atualizarProduto(id, produtosRequestDom);
    }

    @Override
    public void deletarProduto(Long id) {
        produtosBusiness.deletarProdutos(id);
    }

    @Override
    public ProdutosResponseDom carregarProdutoById(Long id) throws SenacException {
        return produtosBusiness.carregarProdutoById(id);
    }
}
