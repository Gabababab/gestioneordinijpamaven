package it.prova.gestioneordinijpamaven.service.ordine;

import java.util.List;

import it.prova.gestioneordinijpamaven.dao.ordine.OrdineDao;
import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAllOrdini() throws Exception;

	public Ordine caricaSingoloOrdine(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;
	
	public List<Ordine> findAllByCategoria(Categoria categoria) throws Exception;
	
	public void aggiungiArticolo(Ordine ordineEsistente, Articolo articoloInstance) throws Exception;
	
	public void rimuoviArticolo(Ordine ordineEsistente, Articolo articoloInstance) throws Exception;

	public void setOrdineDao(OrdineDao ordineDaoInstance);
}
