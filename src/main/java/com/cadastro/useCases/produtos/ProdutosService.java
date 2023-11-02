package com.cadastro.useCases.produtos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ProdutosService {
    List<ProdutosResponseDom> carregarProdutos();
    ProdutosResponseDom criarProduto(ProdutosRequestDom produtosRequestDom) throws Exception;
    ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException;
    void deletarProduto(Long id);
    ProdutosResponseDom carregarProdutoById(Long id);
}
