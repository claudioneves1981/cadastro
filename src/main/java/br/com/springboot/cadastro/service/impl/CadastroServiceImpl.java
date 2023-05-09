package br.com.springboot.cadastro.service.impl;

import br.com.springboot.cadastro.adapter.CadastroDTOAdapter;
import br.com.springboot.cadastro.adapter.CadastroModelAdapter;
import br.com.springboot.cadastro.adapter.EnderecoDTOAdapter;
import br.com.springboot.cadastro.adapter.EnderecoModelAdapter;
import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.EnderecoDTO;
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
    public Iterable<CadastroDTO> buscarTodos() {
        return new CadastroDTOAdapter(cadastroRepository.findAll()).getCadastroDTOList();
    }

    @Override
    public CadastroDTO buscarPorId(Long id) {
        Cadastro cadastro = cadastroRepository.findById(id).get();
        return new CadastroDTOAdapter(cadastro).getCadastroDTO();
    }

    @Override
    public void inserir(CadastroDTO cadastro) {
        salvarClienteComCep(cadastro);
    }

    @Override
    public void atualizar(Long id, CadastroDTO cadastro) {
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
    public CadastroDTO buscarPorNome(String nome) {
        return new CadastroDTOAdapter(cadastroRepository.findByNome(nome)).getCadastroDTO();
    }

    @Override
    public List<CadastroDTO> buscaPorCadastro(String nome){
        return new CadastroDTOAdapter(cadastroRepository.buscarPorCadastro(nome)).getCadastroDTOList();
    }

    private void salvarClienteComCep(CadastroDTO cadastroDTO){
        String cep = cadastroDTO.getEndereco().getCep();
        Endereco endereco =  enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        Cadastro cadastro =  new CadastroModelAdapter(cadastroDTO).getCadastro();
        cadastro.setEndereco(endereco);
        cadastro.setCasapropria(cadastroDTO.getCasapropria());
        cadastro.setEstuda(cadastroDTO.getEstuda());
        cadastro.setIdade(cadastroDTO.getIdade());
        cadastro.setNumero(cadastroDTO.getNumero());
        cadastro.setNome(cadastroDTO.getNome());
        cadastro.setQuantosmoram(cadastroDTO.getQuantosmoram());
        cadastro.setTelefone1(cadastro.getTelefone1());
        cadastro.setTelefone2(cadastro.getTelefone2());
        cadastroRepository.save(cadastro);
    }
}
