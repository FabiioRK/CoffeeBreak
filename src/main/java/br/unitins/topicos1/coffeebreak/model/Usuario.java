package br.unitins.topicos1.coffeebreak.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Usuario extends DefaultEntity implements Cloneable {

	private String nome;
	@NotBlank(message = "O login deve ser informado.")
	private String login;
	@NotBlank(message = "A senha deve ser informada.")
	private String senha;
	private LocalDate dataNascimento;

	private Perfil perfil;
	
	@OneToOne
	@JoinColumn(name = "id_endereco", unique = true)
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	public Usuario getClone() {
		try {
			return (Usuario) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
