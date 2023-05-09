package br.com.springboot.cadastro.adapter;

import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Endereco;
import lombok.Data;

@Data
public class EnderecoModelAdapter {


    private Endereco endereco;

    public EnderecoModelAdapter(EnderecoDTO enderecoDTO){
        endereco = toDTO(enderecoDTO);

    }

    private Endereco toDTO(EnderecoDTO endereco){
        return Endereco.builder()
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .localidade(endereco.getLocalidade())
                .logradouro(endereco.getLogradouro())
                .build();
    }
}
