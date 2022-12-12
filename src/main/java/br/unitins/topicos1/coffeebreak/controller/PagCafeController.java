package br.unitins.topicos1.coffeebreak.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.Session;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.ItemCompra;
import br.unitins.topicos1.coffeebreak.model.Produto;
import br.unitins.topicos1.coffeebreak.model.TipoProduto;
import br.unitins.topicos1.coffeebreak.repository.ProdutoRepository;

@RequestScoped
@Named
public class PagCafeController {
	
	@Inject
	private ProdutoRepository repository;
	private List<Produto> listaProduto;
	
	public PagCafeController() {
	}
	
	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Object resultado = flash.get("pesquisaProduto");
		// verificando se teve consulta de remedio pela pesquisa no template
		if (resultado != null)
			setListaProduto((ArrayList<Produto>)resultado);
		else
			setListaProduto(repository.buscarPorTipoProduto(TipoProduto.CAFE));
	}

	public void adicionarCarrinho(Produto produto) {
		Compra carrinho;
		
		Session session = Session.getInstance();
		if (session.get("carrinho") != null){
			carrinho = (Compra) session.get("carrinho"); 
		} else {
			carrinho = new Compra();
		}
		
		// verificando se existe itens de compra
		if (carrinho.getListaItemCompra() == null)
			carrinho.setListaItemCompra(new ArrayList<ItemCompra>());
		
			
		// buscando um item na lista do carrinho
		Optional<ItemCompra> opItem = carrinho.getListaItemCompra().stream()
				.filter(item -> item.getProduto().equals(produto)).findAny();
		
		ItemCompra item = opItem.orElse(new ItemCompra());
		
		item.setPreco(produto.getPreco());
		item.setProduto(produto);
		item.setQuantidade(item.getQuantidade()+1);
			
		
		// buscando se existe um item no carrinho para alterar
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
		
		// adicionando na sessao
		session.put("carrinho", carrinho);
		
		Util.addInfoMessage(item.getProduto().getNome()+ " adicionado ao carrinho.");
		
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
