package it.prova.gestioneordinijpamaven.service.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioneordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService{

	private ArticoloDao articoloDao;
	@Override
	public List<Articolo> listAllArticolo() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloArticolo(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.update(articoloInstance);

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
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.insert(articoloInstance);

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
	public void rimuovi(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.delete(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void aggiungiAdOrdine(Ordine ordineEsistente, Articolo articoloInstance) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			ordineEsistente = entityManager.merge(ordineEsistente);
			articoloInstance = entityManager.merge(articoloInstance);
			
			articoloInstance.setOrdine(ordineEsistente);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void rimuoviDaOrdine(Ordine ordineEsistente, Articolo articoloInstance) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			ordineEsistente = entityManager.merge(ordineEsistente);
			articoloInstance = entityManager.merge(articoloInstance);
			
			articoloInstance.setOrdine(null);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			articoloInstance = entityManager.merge(articoloInstance);
			categoriaInstance = entityManager.merge(categoriaInstance);
			
			articoloInstance.getCategorie().add(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void rimuoviCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			articoloDao.setEntityManager(entityManager);

			articoloInstance = entityManager.merge(articoloInstance);
//			categoriaInstance = entityManager.merge(categoriaInstance);
			
			for(Categoria item:articoloInstance.getCategorie())
				articoloInstance.getCategorie().remove(item);

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
	public void setArticoloDao(ArticoloDao articoloDaoInstance) {
		this.articoloDao=articoloDaoInstance;
	}

	@Override
	public Long sommaArticoliByCategoria(Categoria categoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.sommaArticoliByCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
