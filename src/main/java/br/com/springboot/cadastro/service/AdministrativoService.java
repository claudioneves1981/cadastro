package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.model.Cadastro;

public interface AdministrativoService {

    Iterable<Administrativo> buscarTodos();

    Administrativo buscarPorId(Long id);

    void inserir(Administrativo administrativo);

    void atualizar(Long id, Administrativo administrativo);

    void deletar(Long id);

    Administrativo buscarPorUsuario(String nome);
}
