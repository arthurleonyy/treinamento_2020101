package com.indracompany.treinamento.model.repository;



import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.springframework.data.jpa.repository.Query;
import com.indracompany.treinamento.model.entity.ExtratoBancario;



public interface ExtratoBancarioRepository extends  GenericCrudRepository<ExtratoBancario, Long> {
	
public List<ExtratoBancario> findByAgenciaAndConta(String agencia, String conta);

/*
 * public List<ExtratoBancario> findAllByPublicationTimeBetween(Date
 * dataInicio,Date dataFim);
 * 
 * 
 * @Query(nativeQuery = true, value =
 * "SELECT e FROM extrato e WHERE agencia = ?1 AND conta = ?2 AND DATE (data) BETWEEN ?3 AND ?4"
 * ) public List<ExtratoBancario> buscarEntreDatasSQL( String agencia, String
 * conta, Date dataInicio, Date datafim);
 * 
 * 
 * @Query(nativeQuery = true, value =
 * "SELECT e FROM extrato e WHERE e.agencia like %?1 AND e.conta like %2 AND DATE BETWEEN ?3 AND ?4"
 * ) public List<ExtratoBancario> buscarEntreDatasSQLteste( String agencia,
 * String conta, Date dataInicio, Date datafim);
 */

}
//SELECT * FROM extrato WHERE  agencia=''and conta='' and DATE( data )
//between '2017-01-13' and '2017-01-20';