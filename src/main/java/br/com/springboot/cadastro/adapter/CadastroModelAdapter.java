package br.com.springboot.cadastro.adapter;


import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.model.Endereco;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastroModelAdapter {

    private Cadastro cadastro;
    private List<Cadastro> cadastroList;

    public CadastroModelAdapter(CadastroDTO cadastroDTO){

        cadastro = toModel(cadastroDTO);

    }

    public CadastroModelAdapter(List<CadastroDTO> cadastroDTOList){

        cadastroList = toModelList(cadastroDTOList);

    }

    private Cadastro toModel(CadastroDTO cadastro){

        return Cadastro.builder()
                .codigo(cadastro.getCodigo())
                .datanasc(cadastro.getDatanasc())
                .idade(cadastro.getIdade())
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
                .numeronis(cadastro.getNumeronis())
                .build();
    }

    private List<Cadastro> toModelList(List<CadastroDTO> cadastros){

        List<Cadastro> cadastroList = new ArrayList<>();
        for(CadastroDTO cadastro : cadastros){
            cadastroList.add(toModel(cadastro));

        }

        return cadastroList;

    }

}
