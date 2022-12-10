package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Produto;
import br.unitins.topicos1.coffeebreak.model.TipoProduto;
import br.unitins.topicos1.coffeebreak.repository.ProdutoRepository;

@Named
@ViewScoped
public class FormProdutoController implements Serializable {
	
	private static final long serialVersionUID = -9146667204434630863L;
	private Produto produto = new Produto();
	
	public FormProdutoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashProduto");
		setProduto((Produto)flash.get("flashProduto"));
	}
	
	public TipoProduto[] getListaTipoProduto() {
		return TipoProduto.values();
	}
	
	public String salvar() {
		ProdutoRepository repo = new ProdutoRepository(JPAUtil.getEntityManager());
		try {
			repo.salvar(getProduto());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		
		PrimeFaces.current().executeScript("location.reload()");
        PrimeFaces.current().ajax().update("form:messages", "form:ltProdutos");
		
        Util.addInfoMessage("Produto salvo com sucesso.");
		return "produtos.xhtml?faces-redirect=true";
	}

	public Produto getProduto() {
		if (produto == null)
			produto = new Produto();
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
