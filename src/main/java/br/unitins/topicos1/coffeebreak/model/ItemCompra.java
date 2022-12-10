package br.unitins.topicos1.coffeebreak.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCompra extends DefaultEntity {
	
	private int quantidade;
	private Double preco;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	//GETTERS E SETTERS
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	
}
