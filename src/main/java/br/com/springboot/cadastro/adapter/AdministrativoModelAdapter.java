package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.model.Administrativo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdministrativoModelAdapter {

    private Administrativo administrativo;
    private List<Administrativo> administrativoList;

    public AdministrativoModelAdapter(AdministrativoDTO administrativoDTO){

        administrativo = toModel(administrativoDTO);

    }

    public AdministrativoModelAdapter(List<AdministrativoDTO> administrativoDTOList){

        administrativoList = toModelList(administrativoDTOList);

    }

    private Administrativo toModel(AdministrativoDTO administrativoDTO){

        return Administrativo.builder()
                .codigo(administrativoDTO.getCodigo())
                .roles(administrativoDTO.getRoles())
                .usuario(administrativoDTO.getUsuario())
                .senha(administrativoDTO.getSenha())
                .nome(administrativoDTO.getNome())
                .build();
    }

    private List<Administrativo> toModelList(List<AdministrativoDTO> administrativos){

        List<Administrativo> administrativoList = new ArrayList<>();
        for(AdministrativoDTO administrativo : administrativos){
            administrativoList.add(toModel(administrativo));

        }

        return administrativoList;

    }

}
