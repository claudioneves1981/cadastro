package br.com.springboot.cadastro.service.impl;

import br.com.springboot.cadastro.adapter.AdministrativoDTOAdapter;
import br.com.springboot.cadastro.adapter.AdministrativoModelAdapter;
import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.repository.AdministrativoRepository;
import br.com.springboot.cadastro.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Override
    public Iterable<AdministrativoDTO> buscarTodos() {
        return new AdministrativoDTOAdapter(administrativoRepository.findAll()).getAdministrativoDTOList();
    }

    @Override
    public AdministrativoDTO buscarPorId(Long id) {
        Administrativo administrativo = administrativoRepository.findById(id).get();
        return new AdministrativoDTOAdapter(administrativo).getAdministrativoDTO();
    }

    @Override
    public void inserir(AdministrativoDTO administrativoDTO) {
        Administrativo administrativo = new AdministrativoModelAdapter(administrativoDTO).getAdministrativo();
        administrativoRepository.save(administrativo);
    }

    @Override
    public void atualizar(Long id, AdministrativoDTO administrativoDTO) {
        administrativoRepository
                .findById(id)
                .ifPresent(administrativo -> {
                    administrativo = new AdministrativoModelAdapter(administrativoDTO).getAdministrativo();
                    administrativoRepository.save(administrativo);
                });
    }

    @Override
    public void deletar(Long id) {
        administrativoRepository.deleteById(id);
    }

    @Override
    public AdministrativoDTO buscarPorUsuario(String nome) {
        return new AdministrativoDTOAdapter(administrativoRepository.findByUsuario(nome)).getAdministrativoDTO();
    }
}
