package it.prova.gestioneordinijpamaven.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public class OrdineDaoImpl implements OrdineDao{

	private EntityManager entityManager;
	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	public List<Ordine> findAllByCategoria(Categoria categoria) {
		TypedQuery<Ordine> query=entityManager.createQuery("SELECT o FROM Ordine o join o.articoli a join a.categorie c WHERE c.descrizione= :descCategoria", Ordine.class);
		query.setParameter("descCategoria", categoria.getDescrizione());
		return query.getResultList();
	}

}
