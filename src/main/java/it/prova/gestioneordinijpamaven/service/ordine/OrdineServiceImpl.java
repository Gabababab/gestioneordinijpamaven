package it.prova.gestioneordinijpamaven.service.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioneordinijpamaven.dao.ordine.OrdineDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public class OrdineServiceImpl implements OrdineService{

	private OrdineDao ordineDao;
	
	@Override
	public List<Ordine> listAllOrdini() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloOrdine(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.update(ordineInstance);

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
	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.insert(ordineInstance);

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
	public void rimuovi(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.delete(ordineInstance);

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
	public void setOrdineDao(OrdineDao ordineDaoInstance){
		this.ordineDao=ordineDaoInstance;
	}
	
	public void aggiungiArticolo(Ordine ordineEsistente, Articolo articoloInstance) throws Exception{
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordineEsistente = entityManager.merge(ordineEsistente);
			articoloInstance = entityManager.merge(articoloInstance);
			
			ordineEsistente.getArticoli().add(articoloInstance);

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
	public List<Ordine> findAllByCategoria(Categoria categoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDao.findAllByCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuoviArticolo(Ordine ordineEsistente, Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordineEsistente = entityManager.merge(ordineEsistente);
			articoloInstance = entityManager.merge(articoloInstance);
			
//			for(Articolo item:ordineEsistente.getArticoli())
			ordineEsistente.getArticoli().remove(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
