package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.CadastroUpdateDTO;
import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Cadastro;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastroUpdateDTOAdapter {

    private CadastroUpdateDTO cadastroDTO;
    private List<CadastroUpdateDTO> cadastroDTOList;

    public CadastroUpdateDTOAdapter(Cadastro cadastro){

      cadastroDTO = toDTO(cadastro);

    }

    public CadastroUpdateDTOAdapter(List<Cadastro> cadastroList){

        cadastroDTOList = toDTOList(cadastroList);

    }

    private CadastroUpdateDTO toDTO(Cadastro cadastro){

        return CadastroUpdateDTO.builder()
                .codigo(cadastro.getCodigo())
                .casapropria(cadastro.getCasapropria())
                .endereco(EnderecoDTO.builder()
                        .bairro(cadastro.getEndereco().getBairro())
                        .cep(cadastro.getEndereco().getCep())
                        .localidade(cadastro.getEndereco().getLocalidade())
                        .logradouro(cadastro.getEndereco().getLogradouro())
                        .build())
                .estuda(cadastro.getEstuda())
                .nome(cadastro.getNome())
                .quantosmoram(cadastro.getQuantosmoram())
                .telefone1(cadastro.getTelefone1())
                .telefone2(cadastro.getTelefone2())
                .numero(cadastro.getNumero())
                .build();
    }

    private List<CadastroUpdateDTO> toDTOList(List<Cadastro> cadastros){

        List<CadastroUpdateDTO> cadastroDTOList = new ArrayList<>();
        for(Cadastro cadastro : cadastros){
            cadastroDTOList.add(toDTO(cadastro));

        }

        return cadastroDTOList;

    }

}
