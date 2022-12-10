package br.unitins.topicos1.coffeebreak.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.unitins.topicos1.coffeebreak.model.Estado;

public class EstadoRepository extends Repository<Estado> {

	public EstadoRepository(EntityManager em) {
		super(em);
	}

	public Estado buscarPeloId(Integer id) {
		return em.find(Estado.class, id);
	}

	public List<Estado> buscarTodos() {
		return em.createQuery("SELECT e FROM Estado e ORDER BY e.nome", Estado.class).getResultList();
	}

}
