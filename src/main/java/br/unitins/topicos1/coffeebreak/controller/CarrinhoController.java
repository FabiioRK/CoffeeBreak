package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.Session;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.ItemCompra;
import br.unitins.topicos1.coffeebreak.model.Produto;

@ViewScoped
@Named
public class CarrinhoController implements Serializable{

	private static final long serialVersionUID = 3513288066644277878L;
	private Compra carrinho;
	
	public List<ItemCompra> getItensCarrinho() {
		Session session = Session.getInstance();
		carrinho = (Compra) session.get("carrinho");

		if (carrinho == null)
			return new ArrayList<ItemCompra>();
		
		return carrinho.getListaItemCompra();
	}
	
	public void finalizarCompra() {
		// verificando se o usuario esta logado
		if (Session.getInstance().get("usuarioLogado") == null) 
			Util.redirect("login.xhtml");
		
		Compra carrinho = (Compra) Session.getInstance().get("carrinho");
		
		if (carrinho == null || 
				carrinho.getListaItemCompra() == null ||
					carrinho.getListaItemCompra().isEmpty()) {
			Util.addWarnMessage("Adicione um item no carrinho antes de concluir a compra.");
			return;
		}
		
		Util.redirect("finalizarcompra.xhtml");
	}
	
	public void excluirItem(Produto produto) {
		Compra carrinho;
		Session session = Session.getInstance();
		if (session.get("carrinho") != null) {
			carrinho = (Compra) session.get("carrinho");
		} else {
			carrinho = new Compra();
		}

		if (carrinho.getListaItemCompra() == null)
			carrinho.setListaItemCompra(new ArrayList<ItemCompra>());

		Optional<ItemCompra> opItem = carrinho.getListaItemCompra().stream()
				.filter(item -> item.getProduto().equals(produto)).findAny();
		
		ItemCompra item = opItem.orElse(new ItemCompra());

		int novaQuantidade = item.getQuantidade() - 1;

		item.setQuantidade(novaQuantidade);

		int indice = -1;
		for (int index = 0; index < carrinho.getListaItemCompra().size(); index++) {
			if (carrinho.getListaItemCompra().get(index).getProduto().equals(produto)) {
				indice = index;
				break;
			}
		}

		if (novaQuantidade >=1)
			carrinho.getListaItemCompra().set(indice, item);
		else 
			carrinho.getListaItemCompra().remove(indice);

		session.put("carrinho", carrinho);

		Util.addInfoMessage(item.getProduto().getNome() + " removido do carrinho.");
	}
	
	public void adicionar(Produto produto) {
		Compra carrinho;
		Session session = Session.getInstance();
		if (session.get("carrinho") != null) {
			carrinho = (Compra) session.get("carrinho");
		} else {
			carrinho = new Compra();
		}

		if (carrinho.getListaItemCompra() == null)
			carrinho.setListaItemCompra(new ArrayList<ItemCompra>());

		Optional<ItemCompra> opItem = carrinho.getListaItemCompra().stream()
				.filter(item -> item.getProduto().equals(produto)).findAny();

		ItemCompra item = opItem.orElse(new ItemCompra());
		
		item.setPreco(produto.getPreco());
		item.setProduto(produto);
		item.setQuantidade(item.getQuantidade() + 1);

		int indice = -1;
		for (int index = 0; index < carrinho.getListaItemCompra().size(); index++) {
			if (carrinho.getListaItemCompra().get(index).getProduto().equals(produto)) {
				indice = index;
				break;
			}
		}

		if (indice >= 0)
			carrinho.getListaItemCompra().set(indice, item);
		else
			carrinho.getListaItemCompra().add(item);

		session.put("carrinho", carrinho);

		Util.addInfoMessage(item.getProduto().getNome() + " adicionado ao carrinho.");
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
