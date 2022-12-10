package br.unitins.topicos1.coffeebreak.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.unitins.topicos1.coffeebreak.model.Compra;
import br.unitins.topicos1.coffeebreak.model.Usuario;

public class CompraRepository extends Repository<Compra> {

	@Inject
	public CompraRepository(EntityManager em) {
		super(em);
	}

	public List<Compra> buscarTodos() {
		return em.createQuery("SELECT c FROM Compra c ORDER BY c.id DESC", Compra.class).getResultList();
	}
	
	public List<Compra> buscarTodos(Usuario usuario) {
		TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c WHERE c.usuario.id = :idUsuario ORDER BY c.id DESC ", Compra.class);
		query.setParameter("idUsuario", usuario.getId());

		return query.getResultList();
	}
	
}
