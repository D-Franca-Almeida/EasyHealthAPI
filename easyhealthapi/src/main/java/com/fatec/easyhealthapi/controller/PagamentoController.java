package com.fatec.easyhealthapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.easyhealthapi.model.Pagamento;
import com.fatec.easyhealthapi.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/processar")
    public ResponseEntity<Pagamento> processarPagamento(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.processarPagamento(pagamento));
    }
}

