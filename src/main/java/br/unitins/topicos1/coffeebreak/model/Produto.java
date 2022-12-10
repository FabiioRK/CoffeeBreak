package br.unitins.topicos1.coffeebreak.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto extends DefaultEntity implements Cloneable {

	@NotBlank(message = "O nome deve ser informado.")
	private String nome;
	@NotNull(message = "O preço deve ser informado.")
	private Double preco;
	private String urlImagem;
	private String descricao;

	private TipoProduto tipoProduto;

	public Produto getClone() {
		try {
			return (Produto) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	// GETTERS E SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

}

