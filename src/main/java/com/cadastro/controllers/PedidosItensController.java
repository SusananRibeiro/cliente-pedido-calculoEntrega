package com.cadastro.controllers;
import com.cadastro.entities.Pedidos;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.annotions.LogRest;
import com.cadastro.frameWork.utils.ResponseUtil;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.clientes.domanis.ClientesRequestDom;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import com.cadastro.useCases.pedidosItens.impl.PedidosItensServiceImpl;
import com.cadastro.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
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
    @Autowired
    private PedidosItensRepository pedidosItensRepository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<PedidosItensResponseDom>> carregarPedidosItens(){
        return ResponseEntity.ok(pedidosItensService.carregarPedidosItens());
    }
    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<PedidosItensResponseDom> carregarPedidosItensById(@PathVariable Long id){
        return ResponseEntity.ok(pedidosItensService.carregarPedidosItensById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidosItens
            (@RequestBody PedidosItensRequestDom pedidosItensRequestDom, Produtos produtos, Pedidos pedidos){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosItensService.criarPedidosItens(pedidosItensRequestDom,
                    produtos, pedidos));
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
    public ResponseEntity<?> atualizarCliente
            (@PathVariable Long id,
             @RequestBody PedidosItensRequestDom pedidosItensRequestDom){
        try {
            return ResponseEntity.ok(
                    pedidosItensService.atualizarPedidosItens(id, pedidosItensRequestDom));
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
    public ResponseEntity<Void> deletarPedidos(@PathVariable Long id){
        pedidosItensService.deletarPedidosItens(id);

        return ResponseEntity.ok(null);
    }
}
