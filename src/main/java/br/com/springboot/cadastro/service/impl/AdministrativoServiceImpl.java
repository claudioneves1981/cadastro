package br.com.springboot.cadastro.service.impl;

import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.repository.AdministrativoRepository;
import br.com.springboot.cadastro.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Override
    public Iterable<Administrativo> buscarTodos() {
        return administrativoRepository.findAll();
    }

    @Override
    public Administrativo buscarPorId(Long id) {
        Optional<Administrativo> administrativo = administrativoRepository.findById(id);
        return administrativo.get();
    }

    @Override
    public void inserir(Administrativo administrativo) {
            administrativoRepository.save(administrativo);
    }

    @Override
    public void atualizar(Long id, Administrativo administrativo) {
        Optional<Administrativo> administrativoBd = administrativoRepository.findById(id);
        if(administrativoBd.isPresent()) {
            administrativoRepository.save(administrativo);
        }
    }

    @Override
    public void deletar(Long id) {
        administrativoRepository.deleteById(id);
    }

    @Override
    public Administrativo buscarPorUsuario(String nome) {
        return administrativoRepository.findByUsuario(nome);
    }
}
