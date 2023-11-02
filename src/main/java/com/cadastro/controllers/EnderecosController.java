package com.cadastro.controllers;
import com.cadastro.frameWork.annotions.LogRest;
import com.cadastro.frameWork.utils.ResponseUtil;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.enderecos.domanis.EnderecosRequestDom;
import com.cadastro.useCases.enderecos.domanis.EnderecosResponseDom;
import com.cadastro.useCases.enderecos.impl.EnderecosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosServiceImpl enderecosService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<EnderecosResponseDom>> carregarEnderecos(){
        List<EnderecosResponseDom> out = enderecosService.carregarEnderecos();

        return ResponseEntity.ok(out);
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecosRequestDom endereco){
        try {
            EnderecosResponseDom out = enderecosService.criarEndereco(endereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(out);
        } catch (SenacException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecosRequestDom endereco){
        try {
            EnderecosResponseDom out = enderecosService.atualizarEndereco(id, endereco);

            return ResponseEntity.ok(out);
        } catch (SenacException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id){
        enderecosService.deletarEndereco(id);

        return ResponseEntity.ok(null);
    }
}
