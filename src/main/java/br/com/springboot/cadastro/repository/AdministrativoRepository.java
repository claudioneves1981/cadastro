package br.com.springboot.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.springboot.cadastro.model.Administrativo;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
	
@Query(value = "select a from Administrativo a where upper(trim(a.nome)) like %?1% or "+
			  "upper(trim(a.usuario)) like %?1%")
List<Administrativo> buscarPorAdministrativo(String name);

@Query(value = "select a from Administrativo a where upper(trim(a.nome)) like %?1%")
List<Administrativo> buscarPorNome(String name);

@Query(value = "SELECT COUNT(a) FROM Administrativo a WHERE upper(trim(a.nome)) like %?1%")
List<Administrativo> validaDuplicados(String name);

@Query(value = "select a from Administrativo a where upper(trim(a.usuario)) like %?1% and "
+ "a.senha like %?2% and a.administrativo like %?3%")
List<Administrativo> buscaPorParametros(String usuario, String senha, Boolean administrativo);

@Query(value = "select a from Administrativo a where upper(trim(a.usuario)) like %?1% and "
+ "a.senha like %?2% and a.administrativo like %?3%")
List<Administrativo> buscaPorParametros2(String usuario, String senha, Boolean administrativo);

}
