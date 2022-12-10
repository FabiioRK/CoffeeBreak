package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Estado;
import br.unitins.topicos1.coffeebreak.model.Perfil;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.EstadoRepository;
import br.unitins.topicos1.coffeebreak.repository.UsuarioRepository;

@Named
@ViewScoped
public class FormUsuarioController implements Serializable {
	
	private static final long serialVersionUID = 657010740587674305L;
	private Usuario usuario = null;
	private List<Estado> listaEstado;
	
	public FormUsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashUsuario");
		setUsuario((Usuario)flash.get("flashUsuario"));
	}
	
	public List<Estado> getListaEstado() {
		if (listaEstado == null) { 
			EstadoRepository repo = new EstadoRepository(JPAUtil.getEntityManager());
			listaEstado = repo.buscarTodos();
		}
		return listaEstado;
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public String salvar() {
		UsuarioRepository repo = new UsuarioRepository(JPAUtil.getEntityManager());
		try {
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		
		PrimeFaces.current().executeScript("location.reload()");
        PrimeFaces.current().ajax().update("form:messages", "form:ltUsuarios");
		
        Util.addInfoMessage("Usuario salvo com sucesso.");
		return "usuarios.xhtml?faces-redirect=true";
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
