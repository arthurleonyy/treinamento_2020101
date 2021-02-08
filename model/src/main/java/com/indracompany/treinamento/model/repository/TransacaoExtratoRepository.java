package com.indracompany.treinamento.model.repository;

import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.TransacaoExtrato;

@Repository
public interface TransacaoExtratoRepository extends GenericCrudRepository<TransacaoExtrato, Long> {

}
