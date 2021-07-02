package br.com.springboot.cadastro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@SequenceGenerator(name = "seq_administrativo" , sequenceName = "seq_administrativo", allocationSize = 1, initialValue = 1)
public class Administrativo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_administrativo")
	private Long codigo;
	
	private String nome;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	private Boolean administrativo;
	
	
	public Administrativo() {
		
	}
	
	public Administrativo(@NotBlank String usuario, 
            @NotBlank String senha, Boolean administrativo) {
	this.usuario = usuario;
    this.senha = senha;
    this.administrativo = administrativo;
}
	
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getAdministrativo() {
		return administrativo;
	}
	public void setAdministrativo(Boolean administrativo) {
		this.administrativo = administrativo;
	}
	
@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrativo)) return false;
        Administrativo administrativos = (Administrativo) o;
        return Objects.equals(usuario, administrativos.usuario) &&
                Objects.equals(senha, administrativos.senha) && Objects.equals(administrativo, administrativos.administrativo);
    }

@Override
    public int hashCode() {
        return Objects.hash(codigo, usuario, senha, 
                            administrativo);
    }
@Override
    public String toString() {
        return "Administrativo{" +
                "codigo=" + codigo +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", administrativo=" + administrativo +
                '}';
    }
}
	

	