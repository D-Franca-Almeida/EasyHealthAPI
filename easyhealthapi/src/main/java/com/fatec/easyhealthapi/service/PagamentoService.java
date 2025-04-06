package com.fatec.easyhealthapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.enums.EasyHealthStatus;
import com.fatec.easyhealthapi.model.Pagamento;
import com.fatec.easyhealthapi.repository.PagamentoRepository;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento processarPagamento(Pagamento pagamento) {
        pagamento.setStatus(EasyHealthStatus.DONE);
        return pagamentoRepository.save(pagamento);
    }
}

