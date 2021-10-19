package it.prova.gestioneordinijpamaven.dao.articolo;

import it.prova.gestioneordinijpamaven.dao.IBaseDAO;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public interface ArticoloDao extends IBaseDAO<Articolo>{

	public Long sommaArticoliByCategoria(Categoria categoria);
}
