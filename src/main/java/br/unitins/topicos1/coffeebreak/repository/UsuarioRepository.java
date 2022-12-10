package br.unitins.topicos1.coffeebreak.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.coffeebreak.application.RepositoryException;
import br.unitins.topicos1.coffeebreak.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	public UsuarioRepository(EntityManager em) {
		super(em);
	}

	public List<Usuario> buscarTodos() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
	
	public Usuario buscar(String login, String senha) throws RepositoryException {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT "); 
		jpql.append("  u ");
		jpql.append("FROM ");
		jpql.append("  Usuario u ");
		jpql.append("WHERE ");
		jpql.append("  u.login = :pLogin AND ");
		jpql.append("  u.senha = :pSenha ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		
		try {
			// o metodo getSingleResult gera um eception quando nao existe um resultado
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RepositoryException("Login ou senha Inválido");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
