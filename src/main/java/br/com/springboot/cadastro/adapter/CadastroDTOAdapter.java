package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Cadastro;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastroDTOAdapter {

    private CadastroDTO cadastroDTO;
    private List<CadastroDTO> cadastroDTOList;

    public CadastroDTOAdapter(Cadastro cadastro){

      cadastroDTO = toDTO(cadastro);

    }

    public CadastroDTOAdapter(List<Cadastro> cadastroList){

        cadastroDTOList = toDTOList(cadastroList);

    }

    private CadastroDTO toDTO(Cadastro cadastro){

        return CadastroDTO.builder()
                .codigo(cadastro.getCodigo())
                .idade(cadastro.getIdade())
                .datanasc(cadastro.getDatanasc())
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
                .numeronis(cadastro.getNumeronis())
                .build();
    }

    private List<CadastroDTO> toDTOList(List<Cadastro> cadastros){

        List<CadastroDTO> cadastroDTOList = new ArrayList<>();
        for(Cadastro cadastro : cadastros){
            cadastroDTOList.add(toDTO(cadastro));

        }

        return cadastroDTOList;

    }

}
