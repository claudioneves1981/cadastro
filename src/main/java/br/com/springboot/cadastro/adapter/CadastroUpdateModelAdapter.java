package br.com.springboot.cadastro.adapter;


import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.CadastroUpdateDTO;
import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.model.Endereco;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastroUpdateModelAdapter {

    private Cadastro cadastro;
    private List<Cadastro> cadastroList;

    public CadastroUpdateModelAdapter(CadastroUpdateDTO cadastroDTO){

        cadastro = toModel(cadastroDTO);

    }

    public CadastroUpdateModelAdapter(List<CadastroUpdateDTO> cadastroDTOList){

        cadastroList = toModelList(cadastroDTOList);

    }

    private Cadastro toModel(CadastroUpdateDTO cadastro){

        return Cadastro.builder()
                .codigo(cadastro.getCodigo())
                .casapropria(cadastro.getCasapropria())
                .endereco(Endereco.builder()
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

    private List<Cadastro> toModelList(List<CadastroUpdateDTO> cadastros){

        List<Cadastro> cadastroList = new ArrayList<>();
        for(CadastroUpdateDTO cadastro : cadastros){
            cadastroList.add(toModel(cadastro));

        }

        return cadastroList;

    }

}
