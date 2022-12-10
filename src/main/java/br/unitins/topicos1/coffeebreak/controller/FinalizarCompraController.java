package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.Session;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.BandeiraCartao;
import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.ItemCompra;
import br.unitins.topicos1.coffeebreak.model.Pagamento;
import br.unitins.topicos1.coffeebreak.model.TipoPagamento;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.CompraRepository;

@ViewScoped
@Named
public class FinalizarCompraController implements Serializable {

	private static final long serialVersionUID = -2732006511497906453L;

	private Compra carrinho;

	private Pagamento pagamento;

	@Inject
	transient private CompraRepository compraRepository;
	
	public FinalizarCompraController() {
		System.out.println("Contrutor");
	}

	public List<ItemCompra> getItensCarrinho() {
		Session session = Session.getInstance();
		carrinho = (Compra) session.get("carrinho");

		if (carrinho == null)
			return new ArrayList<ItemCompra>();

		return carrinho.getListaItemCompra();
	}

	public TipoPagamento[] getListaTipoPagamento() {
		return TipoPagamento.values();
	}

	public BandeiraCartao[] getListaBandeiraCartao() {
		return BandeiraCartao.values();
	}

	public void finalizarCompra() {
		Usuario usuario = (Usuario) Session.getInstance().get("usuarioLogado");
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		if (usuario == null) {
			Util.addErrorMessage("Faça o login antes de finalizar a compra");
			return;
		}

		if (getPagamento().getTipoPagamento().equals(TipoPagamento.CREDITO)
				|| getPagamento().getTipoPagamento().equals(TipoPagamento.DEBITO)) {
			if (getPagamento().getBandeiraCartao() == null) {
				Util.addErrorMessage("Seleciona a bandeira do cartão.");
				return;
			}
		}

		carrinho.setUsuario(usuario);
		carrinho.setPagamento(getPagamento());
		carrinho.setTotal(getTotalCarrinho());

		for (ItemCompra item : carrinho.getListaItemCompra()) {
			item.setCompra(carrinho);
		}

		carrinho.setDataHora(LocalDateTime.now());

		try {
			compraRepository.salvar(carrinho);
			Util.addInfoMessage("Compra realizada com sucesso.");
			flash.put("carrinho", null);
			Util.redirect("home.xhtml");
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
		}

	}

	public Pagamento getPagamento() {
		if (pagamento == null)
			pagamento = new Pagamento();
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Double getTotalCarrinho() {
		Session session = Session.getInstance();
		Compra compra = (Compra) session.get("carrinho");

		if (compra == null || compra.getListaItemCompra() == null)
			return null;
		double valorTotal = 0;

		for (ItemCompra itemCompra : compra.getListaItemCompra()) {
			valorTotal += (itemCompra.getQuantidade() * itemCompra.getPreco());
		}

		return valorTotal;
	}

}
