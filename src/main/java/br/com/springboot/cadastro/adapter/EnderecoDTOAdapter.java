package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.model.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoDTOAdapter {

    private EnderecoDTO enderecoDTO;

    public EnderecoDTOAdapter(Endereco endereco){
        enderecoDTO = toDTO(endereco);

    }

    private EnderecoDTO toDTO(Endereco endereco){
        return EnderecoDTO.builder()
                        .bairro(endereco.getBairro())
                        .cep(endereco.getCep())
                        .localidade(endereco.getLocalidade())
                        .logradouro(endereco.getLogradouro())
                        .build();
    }
}
