package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Produto;
import br.unitins.topicos1.coffeebreak.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutosController implements Serializable {

	private static final long serialVersionUID = -6097435908413821692L;
	private List<Produto> listaProduto;
	private Produto produto;

	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			ProdutoRepository repo = new ProdutoRepository(JPAUtil.getEntityManager());
			listaProduto = repo.buscarTodos();

			if (listaProduto == null)
				listaProduto = new ArrayList<Produto>();
		}	
		return listaProduto;
	}

	public void adicionar() {
		setProduto(new Produto());
	}
	
	public void ir() {
		Util.redirect("admin/produtos.xhtml");
	}

	public String excluir(Produto usuario) {
		ProdutoRepository repo = new ProdutoRepository(JPAUtil.getEntityManager());
		try {
			repo.deletar(usuario);
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
		PrimeFaces.current().executeScript("location.reload()");
		PrimeFaces.current().ajax().update("form:messages", "form:ltProdutos");
		
		Util.addInfoMessage("Produto excluído com sucesso.");
		return "admin/produtos.xhtml?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
