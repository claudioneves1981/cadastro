package br.com.springboot.cadastro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.springboot.cadastro.model.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

	@Query(value = "select c from Cadastro c where upper(trim(c.nome)) like %?1% or "+
												  "upper(trim(c.idade)) like %?1% or "+
												  "upper(trim(c.endereco)) like %?1% or "+
												  "upper(trim(c.numero)) like %?1% or "+
												  "upper(trim(c.bairro)) like %?1% or "+
												  "upper(trim(c.cep)) like %?1% or "+
												  "upper(trim(c.cidade)) like %?1% or "+
												  "upper(trim(c.estado)) like %?1% or "+
												  "upper(trim(c.telefone1)) like %?1% or "+
												  "upper(trim(c.telefone2)) like %?1% or "+
												  "upper(trim(c.quantosmoram)) like %?1% or "+
												  "upper(trim(c.numeronis)) like %?1%")
	List<Cadastro> buscarPorNome(String name);
}