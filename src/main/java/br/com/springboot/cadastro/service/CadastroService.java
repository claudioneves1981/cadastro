package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.repository.CadastroRepository;
import br.com.springboot.cadastro.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CadastroService {

    Iterable<Cadastro> buscarTodos();

    Cadastro buscarPorId(Long id);

    void inserir(Cadastro cadastro);

    void atualizar(Long id, Cadastro cadastro);

    void deletar(Long id);

    Cadastro buscarPorNome(String nome);

   List<Cadastro> buscaPorCadastro(String nome);
}
