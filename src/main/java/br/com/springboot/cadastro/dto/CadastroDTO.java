package br.com.springboot.cadastro.dto;

import br.com.springboot.cadastro.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroDTO {

    private Long codigo;

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date datanasc;

    private String idade;

    private String telefone1;

    private String telefone2;

    private EnderecoDTO endereco;

    private String numero;

    private String quantosmoram;

    private Boolean estuda;

    private Boolean casapropria;

    private String numeronis;


}
