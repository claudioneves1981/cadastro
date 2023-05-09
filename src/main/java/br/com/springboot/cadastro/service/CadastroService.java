package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.dto.CadastroDTO;

import java.util.List;

public interface CadastroService {

    Iterable<CadastroDTO> buscarTodos();

    CadastroDTO buscarPorId(Long id);

    void inserir(CadastroDTO cadastro);

    void atualizar(Long id, CadastroDTO cadastro);

    void deletar(Long id);

    CadastroDTO buscarPorNome(String nome);

   List<CadastroDTO> buscaPorCadastro(String nome);
}
