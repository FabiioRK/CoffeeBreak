package br.unitins.topicos1.coffeebreak.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Compra extends DefaultEntity {

	private Double total;
	private LocalDateTime dataHora;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "id_endereco", unique = true)
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
	private List<ItemCompra> listaItemCompra;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pagamento", unique = true)
	private Pagamento pagamento;
	
	//GETTERS E SETTERS
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<ItemCompra> getListaItemCompra() {
		return listaItemCompra;
	}

	public void setListaItemCompra(List<ItemCompra> listaItemCompra) {
		this.listaItemCompra = listaItemCompra;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
