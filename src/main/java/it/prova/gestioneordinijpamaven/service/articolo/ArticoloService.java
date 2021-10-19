package it.prova.gestioneordinijpamaven.service.articolo;

import java.util.List;

import it.prova.gestioneordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;



public interface ArticoloService {

	public List<Articolo> listAllArticolo() throws Exception;

	public Articolo caricaSingoloArticolo(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	public void aggiungiAdOrdine(Ordine ordineEsistente, Articolo articoloInstance) throws Exception;
	
	public void rimuoviDaOrdine(Ordine ordineEsistente, Articolo articoloInstance) throws Exception;
	
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;

	public void rimuoviCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;
	
	public Long sommaArticoliByCategoria(Categoria categoria)throws Exception;
	
	public void setArticoloDao(ArticoloDao articoloDaoInstance);
}
