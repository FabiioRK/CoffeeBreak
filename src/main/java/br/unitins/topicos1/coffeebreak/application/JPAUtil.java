package br.unitins.topicos1.coffeebreak.application;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("CoffeeBreak");

	@Produces
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
