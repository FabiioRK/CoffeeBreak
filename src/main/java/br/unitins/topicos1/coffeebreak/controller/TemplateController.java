package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.Session;
import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -6813511878037231636L;

	public Usuario getUsuarioLogado() {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		return (Usuario) session.get("usuarioLogado");
	}
	
	public String encerrarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	public Integer getQuantidadeCarrinho() {
		Session session = Session.getInstance();
		Compra compra = (Compra) session.get("carrinho");
		
		if (compra == null || compra.getListaItemCompra() == null)
			return null;
		
		return compra.getListaItemCompra().size();
		
	}

}
