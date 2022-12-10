package br.unitins.topicos1.coffeebreak.model;

import javax.persistence.Entity;

@Entity
public class Pagamento extends DefaultEntity {

	private Integer quantidadeParcela;
	private TipoPagamento tipoPagamento;
	private BandeiraCartao bandeiraCartao;

	//GETTERS E SETTERS
	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	
}
