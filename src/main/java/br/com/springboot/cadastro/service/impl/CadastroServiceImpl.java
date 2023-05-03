package br.com.springboot.cadastro.service.impl;

import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.model.Endereco;
import br.com.springboot.cadastro.repository.CadastroRepository;
import br.com.springboot.cadastro.repository.EnderecoRepository;
import br.com.springboot.cadastro.service.CadastroService;
import br.com.springboot.cadastro.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroServiceImpl implements CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cadastro> buscarTodos() {
        return cadastroRepository.findAll();
    }

    @Override
    public Cadastro buscarPorId(Long id) {
        Optional<Cadastro> cliente = cadastroRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cadastro cadastro) {
        salvarClienteComCep(cadastro);
    }

    @Override
    public void atualizar(Long id, Cadastro cadastro) {
        Optional<Cadastro> clienteBd = cadastroRepository.findById(id);
        if(clienteBd.isPresent()) {
            salvarClienteComCep(cadastro);
        }
    }

    @Override
    public void deletar(Long id) {
        cadastroRepository.deleteById(id);
    }

    @Override
    public Cadastro buscarPorNome(String nome) {
        return cadastroRepository.findByNome(nome);
    }

    @Override
    public List<Cadastro> buscaPorCadastro(String nome){
        return cadastroRepository.buscarPorCadastro(nome);

    }

    private void salvarClienteComCep(Cadastro cadastro){
        String cep = cadastro.getEndereco().getCep();
        Endereco endereco =  enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cadastro.setEndereco(endereco);
        cadastroRepository.save(cadastro);
    }
}
