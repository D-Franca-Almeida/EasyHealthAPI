package com.fatec.easyhealthapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.easyhealthapi.model.Pagamento;
@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {
    
	

}
