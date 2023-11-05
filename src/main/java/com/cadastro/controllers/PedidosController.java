package com.cadastro.controllers;
import com.cadastro.frameWork.annotions.LogRest;
import com.cadastro.frameWork.utils.ResponseUtil;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import com.cadastro.useCases.pedidos.impl.PedidosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidosServiceImpl pedidosService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<PedidosResponseDom>> carregarPedidos(){
        List<PedidosResponseDom> out = pedidosService.carregarPedidos();

        return ResponseEntity.ok(out);
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<PedidosResponseDom> carregarPedidoById(@PathVariable Long id) throws SenacException {
            return ResponseEntity.ok(pedidosService.carregarPedidoById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedido
            (@RequestBody PedidosRequestDom pedidosRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.criarPedido(pedidosRequestDom));
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
    public ResponseEntity<?> atualizarPedido
            (@PathVariable Long id,
             @RequestBody PedidosRequestDom pedidosRequestDom){
        try {
            return ResponseEntity.ok(
                    pedidosService.atualizarPedido(id, pedidosRequestDom));
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
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id){
        pedidosService.deletarPedido(id);

        return ResponseEntity.ok(null);
    }

}
