package com.cadastro.controllers;
import com.cadastro.frameWork.utils.ResponseUtil;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.produtos.domanis.ProdutosRequestDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;
import com.cadastro.useCases.produtos.impl.ProdutosServiceImpl;
import com.cadastro.useCases.produtos.impl.repositorys.ProdutosRepository;
import com.cadastro.frameWork.annotions.LogRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosServiceImpl produtosServiceImpl;
    private ProdutosRepository produtosRepository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<ProdutosResponseDom>> carregarProdutos(){
        return ResponseEntity.ok(produtosServiceImpl.carregarProdutos());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<ProdutosResponseDom> carregarProdutoById(@PathVariable Long id){
        return ResponseEntity.ok(produtosServiceImpl.carregarProdutoById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarProduto
            (@RequestBody ProdutosRequestDom produtosRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosServiceImpl.criarProduto(produtosRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarProduto
            (@PathVariable Long id,
             @RequestBody ProdutosRequestDom produtosRequestDom){
        try {
            return ResponseEntity.ok(
                    produtosServiceImpl.atualizarProduto(id, produtosRequestDom));
        } catch (SenacException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtosServiceImpl.deletarProduto(id);

        return ResponseEntity.ok(null);
    }

}
