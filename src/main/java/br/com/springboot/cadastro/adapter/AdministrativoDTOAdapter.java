package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.model.Cadastro;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdministrativoDTOAdapter {

    private AdministrativoDTO administrativoDTO;
    private List<AdministrativoDTO> administrativoDTOList;

    public AdministrativoDTOAdapter(Administrativo administrativo){

        administrativoDTO = toDTO(administrativo);

    }

    public AdministrativoDTOAdapter(List<Administrativo> administrativoList){

        administrativoDTOList = toDTOList(administrativoList);

    }

    private AdministrativoDTO toDTO(Administrativo administrativo){

        return AdministrativoDTO.builder()
                .codigo(administrativo.getCodigo())
                .roles(administrativo.getRoles())
                .usuario(administrativo.getUsuario())
                .senha(administrativo.getSenha())
                .nome(administrativo.getNome())
                .build();
    }

    private List<AdministrativoDTO> toDTOList(List<Administrativo> administrativos){

        List<AdministrativoDTO> AdministrativoDTOList = new ArrayList<>();
        for(Administrativo administrativo : administrativos){
            administrativoDTOList.add(toDTO(administrativo));

        }

        return administrativoDTOList;

    }

}
