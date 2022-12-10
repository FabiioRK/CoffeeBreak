package br.unitins.topicos1.coffeebreak.repository;

import javax.persistence.EntityManager;

public class Repository<T> {

	protected EntityManager em;

	public Repository(EntityManager em) {
		this.em = em;
	}

	public void salvar(T obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}

	public void deletar(T obj) {
		if (obj != null) {
			em.getTransaction().begin();
			em.remove(em.merge(obj));
			em.getTransaction().commit();
		}

	}

}
