package br.com.springboot.cadastro.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@SequenceGenerator(name = "seq_cadastro" , sequenceName = "seq_cadastro", allocationSize = 1, initialValue = 1)
public class Cadastro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cadastro")
	private Long codigo;
	
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datanasc;
	
	private String idade;
	private String telefone1;
	private String telefone2;
	private String endereco;
	private String numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String quantosmoram;
	private Boolean estuda;
	private Boolean casapropria;
	private String numeronis;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanasc() {
		return datanasc;
	}
	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Boolean getEstuda() {
		return estuda;
	}
	public void setEstuda(Boolean estuda) {
		this.estuda = estuda;
	}
	public Boolean getCasapropria() {
		return casapropria;
	}
	public void setCasapropria(Boolean casapropria) {
		this.casapropria = casapropria;
	}
	public String getQuantosmoram() {
		return quantosmoram;
	}
	public void setQuantosmoram(String quantosmoram) {
		this.quantosmoram = quantosmoram;
	}
	public String getNumeronis() {
		return numeronis;
	}
	public void setNumeronis(String numeronis) {
		this.numeronis = numeronis;
	}

}
