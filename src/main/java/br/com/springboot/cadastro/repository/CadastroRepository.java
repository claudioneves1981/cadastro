package br.com.springboot.cadastro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.springboot.cadastro.model.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

	@Query(value = "select c from Cadastro c where upper(trim(c.nome)) like %?1%")
	List<Cadastro> buscarPorNome(String name);
}
