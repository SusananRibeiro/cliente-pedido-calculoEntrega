package com.cadastro.controllers;

import com.cadastro.frameWork.annotions.LogRest;
import com.cadastro.frameWork.utils.ResponseUtil;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import com.cadastro.useCases.pedidosItens.impl.PedidosItensServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidosItens")
public class PedidosItensController {

    @Autowired
    private PedidosItensServiceImpl pedidosItensService;
    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<PedidosItensResponseDom>> carregarPedidosItens(){
        List<PedidosItensResponseDom> out = pedidosItensService.carregarPedidosItens();

        return ResponseEntity.ok(out);
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<PedidosItensResponseDom> carregarPedidoItensById(@PathVariable Long id) throws SenacException {

        return ResponseEntity.ok(pedidosItensService.carregarPedidoItensById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidoItens
            (@RequestBody PedidosItensRequestDom pedidosItensRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosItensService.criarPedidoItens(pedidosItensRequestDom));
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
    public ResponseEntity<?> atualizarPedidoItens
            (@PathVariable Long id,
             @RequestBody PedidosItensRequestDom pedidosItensRequestDom){
        try {
            return ResponseEntity.ok(
                    pedidosItensService.atualizarPedidoItens(id, pedidosItensRequestDom));
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
    public ResponseEntity<Void> deletarPedidoItens(@PathVariable Long id){
        pedidosItensService.deletarPedidoItens(id);

        return ResponseEntity.ok(null);
    }

}
