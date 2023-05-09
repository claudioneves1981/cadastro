package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.repository.CadastroRepository;
import br.com.springboot.cadastro.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
