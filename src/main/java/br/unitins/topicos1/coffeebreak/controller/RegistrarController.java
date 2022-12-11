package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.JPAUtil;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Estado;
import br.unitins.topicos1.coffeebreak.model.Perfil;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.EstadoRepository;
import br.unitins.topicos1.coffeebreak.repository.UsuarioRepository;

@Named
@RequestScoped
public class RegistrarController implements Serializable {
	
	private static final long serialVersionUID = 5956874419946098650L;
	private Usuario usuario;
	private List<Estado> listaEstado;

	public String registrar() {
		getUsuario().setPerfil(Perfil.CLIENTE);
		getUsuario().setSenha(Util.hash(getUsuario().getSenha()));
		UsuarioRepository repo = new UsuarioRepository(JPAUtil.getEntityManager());

		try {
			repo.salvar(getUsuario());
			Util.addInfoMessage("Usuário criado com sucesso.");

			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.put("usuarioLogado", getUsuario());

			return "login.xhtml?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Usuário ou senha inválidos.");
			return null;
		}
	}
	
	public List<Estado> getListaEstado() {
		if (listaEstado == null) { 
			EstadoRepository repo = new EstadoRepository(JPAUtil.getEntityManager());
			listaEstado = repo.buscarTodos();
		}
		return listaEstado;
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
