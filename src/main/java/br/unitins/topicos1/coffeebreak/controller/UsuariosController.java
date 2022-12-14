package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuariosController implements Serializable {

	private static final long serialVersionUID = -6108295715387315132L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioRepository repo = new UsuarioRepository(JPAUtil.getEntityManager());
			listaUsuario = repo.buscarTodos();

			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void adicionar() {
		setUsuario(new Usuario());
	}
	
	public void ir() {
		Util.redirect("admin/usuarios.xhtml");
	}

	public String excluir(Usuario usuario) {
		UsuarioRepository repo = new UsuarioRepository(JPAUtil.getEntityManager());
		try {
			repo.deletar(usuario);
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
		
		PrimeFaces.current().executeScript("location.reload()");
		PrimeFaces.current().ajax().update("form:messages", "form:ltUsuarios");
		
		Util.addInfoMessage("Usuario excluído com sucesso.");
		return "admin/usuarios.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
