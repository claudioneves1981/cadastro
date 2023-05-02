package br.com.springboot.cadastro.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@SequenceGenerator(name = "seq_cadastro" , sequenceName = "seq_cadastro", allocationSize = 1, initialValue = 1)
@Data
public class Cadastro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cadastro")
	private Long codigo;

	@Column(unique=true)
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datanasc;
	
	private String idade;
	private String telefone1;
	private String telefone2;

	@ManyToOne
	private Endereco endereco;

	private String numero;
	private String quantosmoram;

	private Boolean estuda;

	private Boolean casapropria;

	@Column(unique=true)
	private String numeronis;
	
	}
