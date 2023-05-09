package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.model.Cadastro;

import java.util.List;

public interface AdministrativoService {

    Iterable<AdministrativoDTO> buscarTodos();

    AdministrativoDTO buscarPorId(Long id);

    void inserir(AdministrativoDTO administrativo);

    void atualizar(Long id, AdministrativoDTO administrativo);

    void deletar(Long id);

    AdministrativoDTO buscarPorUsuario(String nome);
}
