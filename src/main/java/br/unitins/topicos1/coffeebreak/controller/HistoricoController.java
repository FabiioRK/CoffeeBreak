package br.unitins.topicos1.coffeebreak.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.unitins.topicos1.coffeebreak.application.Session;
import br.unitins.topicos1.coffeebreak.application.Util;
import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.Usuario;
import br.unitins.topicos1.coffeebreak.repository.CompraRepository;

@RequestScoped
@Named
public class HistoricoController implements Serializable {

	private static final long serialVersionUID = 5761241030260205888L;

	private List<Compra> listaCompra;

	@Inject
	transient private CompraRepository compraRepository;
	
	@PostConstruct
	public void init() {
		Usuario usuario = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuario == null) {
			listaCompra = new ArrayList<Compra>();
		} else {
			setListaCompra(compraRepository.buscarTodos(usuario));
			for (Compra compra : listaCompra) {
				System.out.println("Total Compra: " + compra.getTotal());
			}
		}
	}
	
	public void ir() {
		Util.redirect("historico.xhtml");
	}

	public List<Compra> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(List<Compra> listaCompra) {
		this.listaCompra = listaCompra;
	}

}
