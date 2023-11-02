package com.cadastro.useCases.produtos.impl;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.annotions.Business;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.frameWork.utils.StringUtil;
import com.cadastro.useCases.produtos.ProdutosBusiness;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;
import com.cadastro.useCases.produtos.impl.mappers.ProdutosMapper;
import com.cadastro.useCases.produtos.impl.repositorys.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Business
public class ProdutosBusinessImpl implements ProdutosBusiness {
    @Autowired
    private ProdutosRepository produtosRepository;
    @Override
    public List<ProdutosResponseDom> carregarProdutos() {
        List<Produtos> produtosList = produtosRepository.findAll();
        List<ProdutosResponseDom> out = produtosList
                .stream()
                .map(ProdutosMapper::produtosToProdutosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ProdutosResponseDom criarProduto(ProdutosRequestDom produtosRequestDom) throws Exception {
        List<String> messages = this.validacaoManutencaoProduto(produtosRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Produtos produtos = ProdutosMapper.produtosRequestDomToProdutos(produtosRequestDom);
        Produtos resultProdutos = produtosRepository.save(produtos);
        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(resultProdutos);

        return out;

    }

    @Override
    public ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoProduto(produtosRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produtos = produtosRepository.findById(id).map(record -> {
            record.setNome(produtosRequestDom.getNome());
            record.setDescricao(produtosRequestDom.getDescricao());
            return produtosRepository.save(record);
        });

        if(!produtos.isPresent()){
            throw new SenacException("Produto informando não existe!");
        }

        ProdutosResponseDom out =
                ProdutosMapper.produtosToProdutosResponseDom(produtos.get());

        return out;
    }

    @Override
    public void deletarProdutos(Long id) {
        produtosRepository.deleteById(id);

    }

    @Override
    public Produtos carregarProdutoEntidade(Long id) {
        Produtos produtos = produtosRepository.findById(id).get();

        return produtos;

    }

    @Override
    public ProdutosResponseDom carregarProdutoById(Long id) {
        Produtos produtos = produtosRepository.findById(id).get();

        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(produtos);

        return out;

    }

    private List<String> validacaoManutencaoProduto(ProdutosRequestDom produtosRequestDom){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(produtosRequestDom.getNome())){
            messages.add("Produto informado não possui nome!");
        }

        if(StringUtil.validarString(produtosRequestDom.getDescricao())){
            messages.add("Produto informado não possui descrição!");
        }

        return messages;
    }
}
