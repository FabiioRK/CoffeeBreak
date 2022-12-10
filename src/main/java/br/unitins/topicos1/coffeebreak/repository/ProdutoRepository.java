package br.unitins.topicos1.coffeebreak.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.unitins.topicos1.coffeebreak.model.Produto;
import br.unitins.topicos1.coffeebreak.model.TipoProduto;

public class ProdutoRepository extends Repository<Produto> {

	@Inject
	public ProdutoRepository(EntityManager em) {
		super(em);
	}

	public List<Produto> buscarTodos() {
		try {
			return em.createQuery("SELECT p FROM Produto p ORDER BY p.nome", Produto.class).getResultList();
		} catch (Exception e) {
			return new ArrayList<Produto>();
		}
	}
	
	public List<Produto> buscarPeloNome(String nome) {
		TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE upper(p.nome) LIKE upper(:nome) ORDER BY p.nome ", Produto.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}
	
	public List<Produto> buscarPorTipoProduto(TipoProduto tipoProduto) {
		try {
			TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.tipoProduto = :tipoProduto ORDER BY p.nome", Produto.class);
			query.setParameter("tipoProduto", tipoProduto);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<Produto>();
		}
	}

}
