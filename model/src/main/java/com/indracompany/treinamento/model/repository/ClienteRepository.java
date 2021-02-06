package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long> {

	Cliente findByCpf(String cpf);

	Cliente findByNome(String nome);
<<<<<<< HEAD
=======
	
>>>>>>> 91dd49b350fd46a7b7335091245f87b075c933e9

}
