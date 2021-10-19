package it.prova.gestioneordinijpamaven.test;

import java.util.List;

import it.prova.gestioneordinijpamaven.model.Articolo;
import it.prova.gestioneordinijpamaven.model.Categoria;
import it.prova.gestioneordinijpamaven.model.Ordine;
import it.prova.gestioneordinijpamaven.service.MyServiceFactory;
import it.prova.gestioneordinijpamaven.service.articolo.ArticoloService;
import it.prova.gestioneordinijpamaven.service.categoria.CategoriaService;
import it.prova.gestioneordinijpamaven.service.ordine.OrdineService;

public class TestGestioneordini {
	public static void main(String[] args) {

		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {

			System.out.println(
					"In tabella Ordine ci sono " + ordineServiceInstance.listAllOrdini().size() + " elementi.");
			System.out.println(
					"In tabella Articolo ci sono " + articoloServiceInstance.listAllArticolo().size() + " elementi.");
			System.out.println("In tabella Categoria ci sono " + categoriaServiceInstance.listAllCategoria().size()
					+ " elementi.");
			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			// Test CRUD:Inizio

			// Test insert

			testInserimentoOrdine(ordineServiceInstance);

			testInserimentoArticolo(articoloServiceInstance, ordineServiceInstance);

			testInserimentoCategoria(categoriaServiceInstance);

			// Test Update

			testModificaOrdine(ordineServiceInstance);

			testModificaArticolo(articoloServiceInstance);

			testModificaCategoria(categoriaServiceInstance);

			// Test Delete

			testRimozioneOrdine(ordineServiceInstance);

			testRimozioneArticolo(articoloServiceInstance, ordineServiceInstance);

			testRimozioneCategoria(categoriaServiceInstance);

			// Test Metodi business

			testRicercaOrdiniByCategoria(ordineServiceInstance, articoloServiceInstance, categoriaServiceInstance);

			testRicercaCategoriaByOrdine(ordineServiceInstance, articoloServiceInstance, categoriaServiceInstance);

			testSommaPrezzoArticoliByCategoria(ordineServiceInstance, articoloServiceInstance,
					categoriaServiceInstance);

			System.out.println(
					"****************************** fine batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			System.out.println(
					"In tabella Ordine ci sono " + ordineServiceInstance.listAllOrdini().size() + " elementi.");
			System.out.println(
					"In tabella Articolo ci sono " + articoloServiceInstance.listAllArticolo().size() + " elementi.");
			System.out.println("In tabella Categoria ci sono " + categoriaServiceInstance.listAllCategoria().size()
					+ " elementi.");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Test INSERT: INIZIO

	private static void testInserimentoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserimentoOrdine inizio.............");

		Ordine nuovoOrdine = new Ordine("Mario", "Via fregene");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null)
			throw new RuntimeException("testInserimentoOrdine fallito ");

		ordineServiceInstance.rimuovi(nuovoOrdine);
		System.out.println(".......testInserimentoOrdine fine: PASSED.............");
	}

	private static void testInserimentoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserimentoArticolo inizio.............");

		Ordine nuovoOrdine = new Ordine("Mario", "Via fregene");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null)
			throw new RuntimeException("testInserimentoArticolo fallito ");

		Articolo nuovoArticolo = new Articolo("Cacciavite", 10);
		articoloServiceInstance.aggiungiAdOrdine(nuovoOrdine, nuovoArticolo);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		if (nuovoArticolo.getId() == null)
			throw new RuntimeException("testInserimentoArticolo fallito ");

		ordineServiceInstance.rimuoviArticolo(nuovoOrdine, nuovoArticolo);
		articoloServiceInstance.rimuovi(nuovoArticolo);
//		ordineServiceInstance.rimuovi(nuovoOrdine);
		System.out.println(".......testInserimentoArticolo fine: PASSED.............");
	}

	private static void testInserimentoCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testInserimentoCategoria inizio.............");

		Categoria nuovaCategoria = new Categoria("Ferramenta");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null)
			throw new RuntimeException("testInserimentoCategoria fallito ");

		categoriaServiceInstance.rimuovi(nuovaCategoria);
		System.out.println(".......testInserimentoCategoria fine: PASSED.............");
	}

	// Test INSERT: FINE

	// Test UPDATE: INIZIO

	private static void testModificaOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testModificaOrdine inizio.............");

		Ordine ordineDaModificare = new Ordine("Giancarlo", "Via verdi");
		ordineServiceInstance.inserisciNuovo(ordineDaModificare);

		Ordine ordinePerModifica = new Ordine();
		ordinePerModifica.setIndirizzoSpedizione("Via Rossi");

		String indirizzoOld = ordineDaModificare.getIndirizzoSpedizione();
		ordineDaModificare.setIndirizzoSpedizione(ordinePerModifica.getIndirizzoSpedizione());

		ordineServiceInstance.aggiorna(ordineDaModificare);
		if (ordineDaModificare.getIndirizzoSpedizione().equals(indirizzoOld))
			throw new RuntimeException("testModificaOrdine fallito: modifica non avvenuta ");

		ordineServiceInstance.rimuovi(ordineDaModificare);
		System.out.println(".......testModificaOrdine fine: PASSED.............");
	}

	private static void testModificaArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(".......testModificaArticolo inizio.............");

		Articolo articoloDaModificare = new Articolo("Sapone", 2);
		articoloServiceInstance.inserisciNuovo(articoloDaModificare);

		Articolo articoloPerModifica = new Articolo();
		articoloPerModifica.setPrezzoSingolo(4);

		int prezzoOld = articoloDaModificare.getPrezzoSingolo();
		articoloDaModificare.setPrezzoSingolo(articoloPerModifica.getPrezzoSingolo());

		articoloServiceInstance.aggiorna(articoloDaModificare);
		if (articoloDaModificare.getPrezzoSingolo() == prezzoOld)
			throw new RuntimeException("testModificaArticolo fallito: modifica non avvenuta ");

		articoloServiceInstance.rimuovi(articoloDaModificare);
		System.out.println(".......testModificaArticolo fine: PASSED.............");
	}

	private static void testModificaCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testModificaCategoria inizio.............");

		Categoria categoriaDaModificare = new Categoria("Alimentari");
		categoriaServiceInstance.inserisciNuovo(categoriaDaModificare);

		Categoria categoriaPerModifica = new Categoria();
		categoriaPerModifica.setDescrizione("Pitture");

		String descrizioneOld = categoriaDaModificare.getDescrizione();
		categoriaDaModificare.setDescrizione(categoriaPerModifica.getDescrizione());

		categoriaServiceInstance.aggiorna(categoriaDaModificare);
		if (categoriaDaModificare.getDescrizione().equals(descrizioneOld))
			throw new RuntimeException("testModificaCategoria fallito: modifica non avvenuta ");

		categoriaServiceInstance.rimuovi(categoriaDaModificare);
		System.out.println(".......testModificaCategoria fine: PASSED.............");
	}

	// Test UPDATE: FINE

	// Test DELETE: INIZIO

	private static void testRimozioneOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testRimozioneOrdine inizio.............");
		
		Ordine nuovoOrdine=new Ordine("Carlo","via x");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if(nuovoOrdine.getId()==null)
			throw new RuntimeException("testRimozioneOrdine fallito:  record gia esistente ");
		
		ordineServiceInstance.rimuovi(nuovoOrdine);
		if(ordineServiceInstance.listAllOrdini().size()!=0)
			throw new RuntimeException("testRimozioneOrdine fallito:  record non rimosso");
		System.out.println(".......testRimozioneOrdine fine: PASSED.............");
	}

	private static void testRimozioneArticolo(ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testRimozioneArticolo inizio.............");
		
		Ordine nuovoOrdine=new Ordine("Carlo","via x");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if(nuovoOrdine.getId()==null)
			throw new RuntimeException("testRimozioneArticolo fallito:  record gia esistente ");
		
		Articolo nuovoArticolo=new Articolo("Liquore", 20);
		if(nuovoArticolo.getId()==null)
			throw new RuntimeException("testRimozioneArticolo fallito:  record gia esistente ");
		
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		ordineServiceInstance.aggiungiArticolo(nuovoOrdine, nuovoArticolo);
		articoloServiceInstance.aggiungiAdOrdine(nuovoOrdine, nuovoArticolo);
		
		articoloServiceInstance.rimuoviDaOrdine(nuovoOrdine, nuovoArticolo);
		ordineServiceInstance.rimuoviArticolo(nuovoOrdine, nuovoArticolo);
		articoloServiceInstance.rimuovi(nuovoArticolo);
		if(articoloServiceInstance.listAllArticolo().size()!=0)
			throw new RuntimeException("testRimozioneArticolo fallito:  record non rimosso");
		
		ordineServiceInstance.rimuovi(nuovoOrdine);
		System.out.println(".......testRimozioneArticolo fine: PASSED.............");
	}

	private static void testRimozioneCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testRimozioneCategoria inizio.............");
		
		Categoria nuovaCategoria=new Categoria("Fritto");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if(nuovaCategoria.getId()==null)
			throw new RuntimeException("testRimozioneCategoria fallito:  record gia esistente ");
		
		categoriaServiceInstance.rimuovi(nuovaCategoria);
		if(categoriaServiceInstance.listAllCategoria().size()!=0)
			throw new RuntimeException("testRimozioneCategoria fallito:  record non rimosso");
		
		System.out.println(".......testRimozioneCategoria fine: PASSED.............");
	}

	// Test DELETE: FINE

	// Test Metodi Business: INIZIO

	private static void testRicercaOrdiniByCategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testRicercaOrdiniByCategoria inizio.............");

		Ordine nuovoOrdine = new Ordine("Lillo", "Via prato");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		Articolo nuovoArticolo = new Articolo("Pollo", 5);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		ordineServiceInstance.aggiungiArticolo(nuovoOrdine, nuovoArticolo);
//		articoloServiceInstance.aggiungiAdOrdine(nuovoOrdine, nuovoArticolo);
		nuovoArticolo.setOrdine(nuovoOrdine);

		Categoria nuovaCategoria = new Categoria("alimentari");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria);
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);

		List<Ordine> risultatiRicercaOrdiniByCategoria = ordineServiceInstance.findAllByCategoria(nuovaCategoria);
		if (risultatiRicercaOrdiniByCategoria.size() != 1) {
			System.out.println("Ordini:" + risultatiRicercaOrdiniByCategoria.size());
			throw new RuntimeException("testRicercaOrdiniByCategoria fallito");
		}
		System.out.println("Ordini di questa categoria:" + risultatiRicercaOrdiniByCategoria.size());

		// FLUSH
//		articoloServiceInstance.rimuoviCategoria(nuovoArticolo, nuovaCategoria);
//		System.out.println("2");
//		categoriaServiceInstance.rimuoviArticolo(nuovaCategoria, nuovoArticolo);
//		System.out.println("1");
//		ordineServiceInstance.rimuoviArticolo(nuovoOrdine, nuovoArticolo);
//		System.out.println("4");
//		articoloServiceInstance.rimuoviDaOrdine(nuovoOrdine, nuovoArticolo);
//		System.out.println("3");
//
//		
//		categoriaServiceInstance.rimuovi(nuovaCategoria);
//		articoloServiceInstance.rimuovi(nuovoArticolo);
//		ordineServiceInstance.rimuovi(nuovoOrdine);
		System.out.println(".......testRicercaOrdiniByCategoria fine: PASSED.............");
	}

	private static void testRicercaCategoriaByOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testRicercaCategoriaByOrdine inizio.............");

		Ordine nuovoOrdine = new Ordine("Lillo", "Via prato");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		Articolo nuovoArticolo = new Articolo("Pollo", 5);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		ordineServiceInstance.aggiungiArticolo(nuovoOrdine, nuovoArticolo);
//		articoloServiceInstance.aggiungiAdOrdine(nuovoOrdine, nuovoArticolo);
		nuovoArticolo.setOrdine(nuovoOrdine);

		Categoria nuovaCategoria = new Categoria("alimentari");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria);
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);

		List<Categoria> risultatiRicercaCategoriaByOrdine = categoriaServiceInstance
				.findAllCategorieArticoliByOrdine(nuovoOrdine);
		if (risultatiRicercaCategoriaByOrdine.size() != 1) {
			System.out.println("categorie:" + risultatiRicercaCategoriaByOrdine.size());
			throw new RuntimeException("testRicercaCategoriaByOrdine fallito");
		}
		System.out.println("Ordini di questa categoria:" + risultatiRicercaCategoriaByOrdine.size());

		// FLUSH
//		articoloServiceInstance.rimuoviCategoria(nuovoArticolo, nuovaCategoria);
//		System.out.println("2");
//		categoriaServiceInstance.rimuoviArticolo(nuovaCategoria, nuovoArticolo);
//		System.out.println("1");
//		ordineServiceInstance.rimuoviArticolo(nuovoOrdine, nuovoArticolo);
//		System.out.println("4");
//		articoloServiceInstance.rimuoviDaOrdine(nuovoOrdine, nuovoArticolo);
//		System.out.println("3");
//
//		
//		categoriaServiceInstance.rimuovi(nuovaCategoria);
//		articoloServiceInstance.rimuovi(nuovoArticolo);
//		ordineServiceInstance.rimuovi(nuovoOrdine);
		System.out.println(".......testRicercaCategoriaByOrdine fine: PASSED.............");
	}

	private static void testSommaPrezzoArticoliByCategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testSommaPrezzoArticoliByCategoria inizio.............");

		Ordine nuovoOrdine = new Ordine("Lillo", "Via prato");
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		Articolo nuovoArticolo = new Articolo("Pollo", 5);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		ordineServiceInstance.aggiungiArticolo(nuovoOrdine, nuovoArticolo);
		nuovoArticolo.setOrdine(nuovoOrdine);

		Articolo nuovoArticolo2 = new Articolo("Pane", 7);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo2);
		ordineServiceInstance.aggiungiArticolo(nuovoOrdine, nuovoArticolo2);
		nuovoArticolo2.setOrdine(nuovoOrdine);

		Categoria nuovaCategoria = new Categoria("alimentari");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria);
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo2);

		Long risultatoSomma = null;
		risultatoSomma = articoloServiceInstance.sommaArticoliByCategoria(nuovaCategoria);
		if (risultatoSomma == null)
			throw new RuntimeException("testSommaPrezzoArticoliByCategoria fallito");

		System.out.println(".......testSommaPrezzoArticoliByCategoria fine: PASSED.............");
	}
}