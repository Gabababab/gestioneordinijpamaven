package it.prova.gestioneordinijpamaven.service.categoria;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioneordinijpamaven.dao.categoria.CategoriaDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService{

	private CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> listAllCategoria() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return categoriaDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Categoria caricaSingolaCategoria(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return categoriaDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			categoriaDao.update(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			categoriaDao.insert(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			categoriaDao.delete(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setCategoriaDao(CategoriaDao categoriaDaoInstance){
		this.categoriaDao=categoriaDaoInstance;
	}

	@Override
	public void aggiungiArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			categoriaDao.setEntityManager(entityManager);

			articoloInstance = entityManager.merge(articoloInstance);
			categoriaInstance = entityManager.merge(categoriaInstance);
			
			categoriaInstance.getArticoli().add(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuoviArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			categoriaDao.setEntityManager(entityManager);

//			articoloInstance = entityManager.merge(articoloInstance);
			categoriaInstance = entityManager.merge(categoriaInstance);
			for(Articolo item:categoriaInstance.getArticoli())
				categoriaInstance.getArticoli().remove(item);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Categoria> findAllCategorieArticoliByOrdine(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			categoriaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return categoriaDao.findAllCategorieArticoliByOrdine(ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
