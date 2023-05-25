package app;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoriAutomaticiDAO;
import dao.EmissioneAbbonamentoDAO;
import dao.EmissioneBigliettoDAO;
import dao.MezzoDAO;
import dao.TesseraDAO;
import dao.TrattaDAO;
import dao.UtenteDAO;
import dao.VenditoriAutorizzatiDAO;
import dao.VidimazioneBigliettiDAO;
import entities.Mezzo;
import entities.Utente;
import entities.VenditoriAutorizzati;
import util.JpaUtil;

public class MainInterattivo {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManager em = emf.createEntityManager();

		MezzoDAO md = new MezzoDAO(em);
		TrattaDAO td = new TrattaDAO(em);
		EmissioneBigliettoDAO ebd = new EmissioneBigliettoDAO(em);
		EmissioneAbbonamentoDAO ead = new EmissioneAbbonamentoDAO(em);
		DistributoriAutomaticiDAO dad = new DistributoriAutomaticiDAO(em);
		VenditoriAutorizzatiDAO vad = new VenditoriAutorizzatiDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		TesseraDAO ted = new TesseraDAO(em);
		VidimazioneBigliettiDAO vbd = new VidimazioneBigliettiDAO(em);

		// Login/Register
		System.out.println("Benvenuto all'app di trasporti pubblici!");
		System.out.println("1. Accedi");
		System.out.println("2. Nuovo nell'app? Registrati");
		int sceltaUtente = scanner.nextInt();

		switch (sceltaUtente) {
		case 1:
			// Login
			System.out.println("Inserisci l'ID dell'utente:");
			String idUtente = scanner.next();

			Utente utente = ud.getById(idUtente);

			if (utente == null) {
				System.out.println("Utente non valido.");
				// Gestire l'errore o uscire dall'applicazione
				break;
			}
			System.out.println("Che bello rivederti " + utente.getNome() + " "
					+ utente.getCognome());
			break;

		case 2:
			// Creazione utente (Register)
			scanner.nextLine(); // Pulisce lo scanner
			System.out.println("Inserisci il tuo nome: ");
			String nomeScelto = scanner.nextLine();
			System.out.println("Inserisci il tuo cognome: ");
			String cognomeScelto = scanner.nextLine();
			Utente nuovoUtente = new Utente(nomeScelto, cognomeScelto);
			ud.save(nuovoUtente);
			break;

		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}

		// Recupera tutti i venditori autorizzati e visualizzali all'utente
		System.out.println("seleziona il venditore:");
		List<VenditoriAutorizzati> venditori = vad.getAllVenditoriAutorizzati();
		for (VenditoriAutorizzati venditore : venditori) {
			System.out.println(venditore.getIdPuntoVendita() + ". "
					+ venditore.getNomeNegozio());
		}
		int venditoreScelto = scanner.nextInt();

		System.out.println("Cosa desideri acquistare?");
		System.out.println("1. Biglietto");
		System.out.println("2. Abbonamento");
		System.out.print("Scelta: ");

		int tipoAcquisto = scanner.nextInt();

		switch (tipoAcquisto) {
		case 1:
			// Logica per l'acquisto del biglietto
//			EmissioneBiglietto bigliettoScelto = new EmissioneBiglietto(
//					LocalDate.now(), utente, venditore);

//			System.out.println(venditore.emettiBiglietto(utente).toString());
//			ebd.save(bigliettoScelto);

			System.out.println("Seleziona un mezzo per la tratta disponibile:");

			// Recupera i mezzi disponibili e visualizzali all'utente
			List<Mezzo> mezziDisponibili = md.getAllMezzi();
			for (Mezzo mezzo : mezziDisponibili) {
				System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo());
			}

			int mezzoScelto = scanner.nextInt();

			System.out.println("Biglietto emesso!");
			System.out.println("Buon viaggio! WOOOO!");
			System.out.println(
					"***Ricordarsi di convalidare il biglietto sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out.println("Vuoi timbrare il biglietto sul mezzo " + "("
					+ mezzoScelto + ")" + " selezionato? (S/N)");
			String confermaTimbro = scanner.next();

			if (confermaTimbro.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato <---
				// inserire qui
				System.out.println("Biglietto timbrato correttamente sul mezzo "
						+ mezzoScelto + "bravo picciotto buon viaggio!.");
			} else {
				System.out.println(
						"Ricordati di convalidare il biglietto sul mezzo una volta salito o verrai multato a sangue.");
			}
			break;
		case 2:
			// Logica per l'acquisto dell'abbonamento <--- inserire qui

			System.out.println("Abbonamento emesso!");

			int uscita;
			do {
				System.out.println(
						"Ecco i mezzi disponibili: (premi 0 per uscire)");
				// Recupera i mezzi disponibili e visualizzali all'utente
				List<Mezzo> mezziDisponibiliPerTessera = md.getAllMezzi();
				for (Mezzo mezzo : mezziDisponibiliPerTessera) {
					System.out.println(
							mezzo.getId() + ". " + mezzo.getTipoMezzo());
				}

				uscita = scanner.nextInt();
				if (uscita == 0) {
					System.out.println("Uscita dall'app.");
					em.close();
					emf.close();
					System.exit(0);
				} else {
					// Altre possibili azioni in base alla selezione del mezzo
					// <--- inserire qui
					// (opzionale)
				}
			} while (uscita != 0);

			System.out.println("Buon viaggio! WOOOOOO!!!");
			System.out.println(
					"***Ricordarsi di convalidare la tessera sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out.println(
					"Vuoi timbrare la tessera sul mezzo selezionato? (S/N)");
			String confermaTessera = scanner.next();

			if (confermaTessera.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato <---
				// inserire qui
				System.out.println(
						"Tessera timbrata correttamente sul mezzo bravo picciotto buon viaggio!.");
			} else {
				System.out.println(
						"Ricordati di convalidare la tessera sul mezzo una volta saliti o verrai mandato a quel paese.");
			}
			break;
		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}
		em.close();
		emf.close();
	}
}
