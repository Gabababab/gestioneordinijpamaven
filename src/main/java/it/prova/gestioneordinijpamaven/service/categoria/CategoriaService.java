package it.prova.gestioneordinijpamaven.service.categoria;

import java.util.List;

import it.prova.gestioneordinijpamaven.dao.categoria.CategoriaDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;


public interface CategoriaService {
	
	public List<Categoria> listAllCategoria() throws Exception;

	public Categoria caricaSingolaCategoria(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;
	
	public void aggiungiArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;
	
	public void rimuoviArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;
	
	public List<Categoria> findAllCategorieArticoliByOrdine(Ordine ordine)throws Exception;

	public void setCategoriaDao(CategoriaDao categoriaDaoInstance);
}
