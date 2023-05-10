package br.com.springboot.cadastro.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CadastroUpdateDTO {

    private Long codigo;

    private String nome;

    private String telefone1;

    private String telefone2;

    private EnderecoDTO endereco;

    private String numero;

    private String quantosmoram;

    private Boolean estuda;

    private Boolean casapropria;

}
