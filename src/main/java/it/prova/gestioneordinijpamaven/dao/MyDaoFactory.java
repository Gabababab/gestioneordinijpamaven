package it.prova.gestioneordinijpamaven.dao;

import it.prova.gestioneordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioneordinijpamaven.dao.articolo.ArticoloDaoImpl;
import it.prova.gestioneordinijpamaven.dao.categoria.CategoriaDao;
import it.prova.gestioneordinijpamaven.dao.categoria.CategoriaDaoImpl;
import it.prova.gestioneordinijpamaven.dao.ordine.OrdineDao;
import it.prova.gestioneordinijpamaven.dao.ordine.OrdineDaoImpl;

public class MyDaoFactory {

	private static OrdineDao ordineDaoInstance = null;
	private static ArticoloDao articoloDaoInstance = null;
	private static CategoriaDao categoriaDaoInstance = null;

	public static OrdineDao getOrdineDAOInstance() {
		if (ordineDaoInstance == null)
			ordineDaoInstance = new OrdineDaoImpl();

		return ordineDaoInstance;
	}

	public static ArticoloDao getArticoloDAOInstance() {
		if (articoloDaoInstance == null)
			articoloDaoInstance = new ArticoloDaoImpl();

		return articoloDaoInstance;
	}
	
	public static CategoriaDao getCategoriaDAOInstance() {
		if (categoriaDaoInstance == null)
			categoriaDaoInstance = new CategoriaDaoImpl();

		return categoriaDaoInstance;
	}

}
