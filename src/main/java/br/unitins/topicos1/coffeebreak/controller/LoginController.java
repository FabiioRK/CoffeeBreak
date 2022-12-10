package br.unitins.topicos1.coffeebreak.controller;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.RepositoryException;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		
		UsuarioRepository repo = new UsuarioRepository(JPAUtil.getEntityManager());
		
		Usuario usuarioLogado;
		try {
			usuarioLogado = repo.buscar(getUsuario().getLogin(), getUsuario().getSenha());
		} catch (RepositoryException e) {
			// quando entrar nesse exception, significa que o usuario não foi encontrado
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
			return null;
		}
		
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("usuarioLogado", usuarioLogado);
		
		return "home.xhtml?faces-redirect=true";
	}
	
	public void limpar() {
		getUsuario().setLogin(null);;
		getUsuario().setSenha(null);;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
