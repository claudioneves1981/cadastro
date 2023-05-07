package br.com.springboot.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.springboot.cadastro.model.Administrativo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

//@Query("SELECT a FROM Administrativo a JOIN FETCH a.roles WHERE a.usuario=:usuario")
Administrativo findByUsuario(@Param("usuario") String usuario);

}
