package persistence;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.Aula;
import model.CalendarioPersonale;
import model.Corso;
import model.CorsoDiLaurea;
import model.DescrizioneCorso;
import model.Evento;
import model.GiornoCalendario;
import model.Lezione;
import model.Messaggio;
import model.Utente;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.UtenteDao;

public class MainJDBC {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(1995, Calendar.MARCH, 21); // // 21 marzo 1995
		Date date1 = (Date) cal.getTime();
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		util.createDatabase();

		// ISTANZE FISSE

		CorsoDiLaurea corsoDiLaureaInformatica = new CorsoDiLaurea("0733", "Informatica");
		CorsoDiLaurea corsoDiLaureaMatematica = new CorsoDiLaurea("0726", "Matematica");
		CorsoDiLaureaDao corsoDiLaureaDao = factory.getCorsoDiLaureaDAO();
		corsoDiLaureaDao.save(corsoDiLaureaInformatica);
		corsoDiLaureaDao.save(corsoDiLaureaMatematica);
		Aula aulaMT5 = new Aula("MT5", 160, corsoDiLaureaInformatica.getCodice(), "Cubo 31A - Piano terra");
		Aula aulaMT5Bis = new Aula("MT5Bis", 90, corsoDiLaureaInformatica.getCodice(), "Cubo 31A - Piano terra");
		Aula aulaMT1 = new Aula("MT1", 110, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Piano terra");
		Aula aulaMT2 = new Aula("MT2", 48, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Piano terra");
		Aula aulaMT3 = new Aula("MT3", 48, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Piano terra");
		Aula aulaMT4 = new Aula("MT4", 56, corsoDiLaureaMatematica.getCodice(), "Cubo 30B - Piano terra");
		Aula aulaMT6 = new Aula("MT6", 72, corsoDiLaureaMatematica.getCodice(), "Cubo 30B - Piano terra");
		Aula aulaMT8 = new Aula("MT8", 28, corsoDiLaureaMatematica.getCodice(), "Cubo 30B - Piano terra");
		Aula aulaMT13 = new Aula("MT13", 32, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Secondo piano");
		Aula aulaMT14 = new Aula("MT14", 24, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Secondo piano");
		Aula aulaMT15 = new Aula("MT15", 45, corsoDiLaureaMatematica.getCodice(), "Cubo 31A - Primo piano");
		Aula lab31B = new Aula("Laboratorio 31 B", 45, corsoDiLaureaMatematica.getCodice(), "Cubo 31B - Secondo piano");

		AulaDao aulaDao = factory.getAulaDAO();
		aulaDao.save(aulaMT1);
		aulaDao.save(aulaMT2);
		aulaDao.save(aulaMT3);
		aulaDao.save(aulaMT4);
		aulaDao.save(aulaMT5);
		aulaDao.save(aulaMT5Bis);
		aulaDao.save(aulaMT6);
		aulaDao.save(aulaMT8);
		aulaDao.save(aulaMT13);
		aulaDao.save(aulaMT14);
		aulaDao.save(aulaMT15);
		aulaDao.save(lab31B);

		// ISTANZE DI PROVA POSTGRESS

		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
			Date date;

			date = format.parse("1999-02-02");
			Utente st = new Utente("111", "Ciccio", "Rossi", date, "RFFDSS43D23J878K", "ciccio@studenti.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 0, "111111");
			Utente st2 = new Utente("100", "Giuseppe", "Verdi", date, "GPPVDR97Y76T518D", "verdi@studenti.unical.it",
					"DDDDDD", corsoDiLaureaMatematica.getCodice(), 0, "111111");
			date = format.parse("1993-06-19");
			Utente st3 = new Utente("169983", "Enrico", "De Cicco", date, "DCCNRC93H19Z112B",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111");
			st3.setProfileImagePath("images/profileImages/169983.jpg");
			date = format.parse("1995-10-23");
			Utente st4 = new Utente("169991", "Martina", "Muto", date, "MTUMTN95R63H919H", "martyvolley23@gmail.com",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 0, "111111");
			st4.setProfileImagePath("images/profileImages/169991.jpg");
			UtenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			studenteDao.save(st);
			studenteDao.save(st2);
			studenteDao.save(st3);
			studenteDao.save(st4);

			Date dateRicca = format.parse("1964-05-12");
			Utente ricca = new Utente("555", "Francesco", "Ricca", dateRicca, "RFFDSS43D23J878K", "ricca@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			ricca.setProfileImagePath("images/profileImages/ricca.png");
			Utente spataro = new Utente("12547", "William", "Spataro", dateRicca, "SPTWLM43D23J878K",
					"spataro@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			spataro.setProfileImagePath("images/profileImages/spataro.JPG");
			Utente cali = new Utente("12437", "Francesco", "Calimeri", dateRicca, "CLMFRC43D23J878K",
					"calimeri@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			cali.setProfileImagePath("images/profileImages/cali.jpg");
			Utente grasso = new Utente("124547", "Giovanni", "Grasso", dateRicca, "GRSGVN43D23J878K",
					"grasso@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			grasso.setProfileImagePath("images/profileImages/grasso.jpg");
			Utente leone = new Utente("12467", "Nicola", "Leone", dateRicca, "LNENCL43D23J878K", "leone@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			leone.setProfileImagePath("images/profileImages/leone.jpg");
			Utente reale = new Utente("12448", "Kristian", "Reale", dateRicca, "RLEKRS43D23J878K",
					"reale@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			reale.setProfileImagePath("images/profileImages/reale.jpg");
			Utente rullo = new Utente("12442", "Pasquale", "Rullo", dateRicca, "RLLPSQ43D23J878K",
					"rullo@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			rullo.setProfileImagePath("images/profileImages/rullo.jpg");
			Utente terra = new Utente("126547", "Giorgio", "Terracina", dateRicca, "TRCGRG43D23J878K",
					"terracina@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			terra.setProfileImagePath("images/profileImages/terracina.jpg");
			Utente vanbon = new Utente("124677", "John", "Van Bon", dateRicca, "VNBJHN43D23J878K",
					"vanbon@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			vanbon.setProfileImagePath("images/profileImages/vanbon.jpg");
			Utente perri = new Utente("444", "Simona", "Perri", date, "PRRSNM75F67T981V", "perri@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			perri.setProfileImagePath("images/profileImages/simona.jpg");
			Utente cianciaruso = new Utente("333", "Nuccia", "Cianciaruso", date, "NCCCRC87H76H473S",
					"cianciaruso@mat.unical.it", "NNNNNN", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			cianciaruso.setProfileImagePath("images/profileImages/cianci.jpg");
			Utente marino = new Utente("222", "Giuseppe", "Marino", date, "GPPMNR64Y34G764L", "marino@mat.unical.it",
					"PPPPPP", corsoDiLaureaMatematica.getCodice(), 1, "111111");
			UtenteDao docenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			docenteDao.save(ricca);
			docenteDao.save(grasso);
			docenteDao.save(vanbon);
			docenteDao.save(terra);
			docenteDao.save(spataro);
			docenteDao.save(cali);
			docenteDao.save(rullo);
			docenteDao.save(leone);
			docenteDao.save(reale);
			docenteDao.save(perri);
			docenteDao.save(cianciaruso);
			docenteDao.save(marino);

			// CalendarioPersonale calendarioPersonaleRicca = new
			// CalendarioPersonale(ricca.getMatricola());
			// CalendarioPersonale calendarioPersonalePerri = new
			// CalendarioPersonale(perri.getMatricola());
			// CalendarioPersonale calendarioPersonaleCianciaruso = new
			// CalendarioPersonale(cianciaruso.getMatricola());
			// CalendarioPersonale calendarioPersonaleMarino = new
			// CalendarioPersonale(marino.getMatricola());

			// calendarioPersonaleDao.save(calendarioPersonaleRicca);
			// calendarioPersonaleDao.save(calendarioPersonalePerri);
			// calendarioPersonaleDao.save(calendarioPersonaleCianciaruso);
			// calendarioPersonaleDao.save(calendarioPersonaleMarino);
			DescrizioneCorso corsoFondamenti = new DescrizioneCorso(new Long(27000002), "Fondamenti di Informatica", 1,
					corsoDiLaureaInformatica.getCodice(), 12, 48, 48);
			DescrizioneCorso corsoAnalisi = new DescrizioneCorso(new Long(27005730), "Analisi Matematica", 1,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoOggetti = new DescrizioneCorso(new Long(27002209), "Programmazione ad Oggetti", 2,
					corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoAlgoritmi = new DescrizioneCorso(new Long(27002211), "Algoritmi e Strutture Dati", 2,
					corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoArchitettura = new DescrizioneCorso(new Long(27002212),
					"Architettura degli Elaboratori", 2, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoBasi = new DescrizioneCorso(new Long(27000109), "Basi di Dati", 2,
					corsoDiLaureaInformatica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoIngegneria = new DescrizioneCorso(new Long(27000110), "Ingegneria del Software", 3,
					corsoDiLaureaInformatica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoSIW = new DescrizioneCorso(new Long(27002217), "Web Computing", 3,
					corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoIGPE = new DescrizioneCorso(new Long(27002214),
					"Interfacce Grafiche e Programmazione ad Eventi", 2, corsoDiLaureaInformatica.getCodice(), 9, 24,
					72);
			DescrizioneCorso corsoDiscreta = new DescrizioneCorso(new Long(27000107), "Matematica Discreta", 1,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoMAD = new DescrizioneCorso(new Long(27002041), "Matematica per l'Analisi dei Dati", 2,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoSistemi = new DescrizioneCorso(new Long(27002215), "Sistemi Operativi e Reti", 3,
					corsoDiLaureaInformatica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoIntelligenza = new DescrizioneCorso(new Long(27002216), "Intelligenza Artificiale", 3,
					corsoDiLaureaInformatica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoRicerca = new DescrizioneCorso(new Long(27000023), "Ricerca Operativa", 2,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoParalleli = new DescrizioneCorso(new Long(27002222),
					"Algoritmi Paralleli e Sistemi Distribuiti", 2, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoLinguaggi = new DescrizioneCorso(new Long(27002213),
					"Linguaggi e logiche per l'informatica", 1, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoFisica = new DescrizioneCorso(new Long(27000005), "Fisica", 1,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoInglese = new DescrizioneCorso(new Long(27002277), "Inglese", 1,
					corsoDiLaureaInformatica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoEconomia = new DescrizioneCorso(new Long(27000035),
					"Economia e Organizzazione Aziendale", 1, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);

			DescrizioneCorsoDao descCorso = factory.getDescrizioneCorsoDao();
			descCorso.save(corsoFondamenti);
			descCorso.save(corsoAnalisi);
			descCorso.save(corsoOggetti);
			descCorso.save(corsoAlgoritmi);
			descCorso.save(corsoArchitettura);
			descCorso.save(corsoBasi);
			descCorso.save(corsoIngegneria);
			descCorso.save(corsoSIW);
			descCorso.save(corsoIGPE);
			descCorso.save(corsoDiscreta);
			descCorso.save(corsoMAD);
			descCorso.save(corsoSistemi);
			descCorso.save(corsoIntelligenza);
			descCorso.save(corsoRicerca);
			descCorso.save(corsoParalleli);
			descCorso.save(corsoLinguaggi);
			descCorso.save(corsoFisica);
			descCorso.save(corsoInglese);
			descCorso.save(corsoEconomia);
			GiornoCalendario dataI = new GiornoCalendario(2, 10, 2017);
			GiornoCalendario dataF = new GiornoCalendario(10, 1, 2018);
			Corso corsoIngegneriaSW = new Corso(new Long(213), "Ingegneria del Software", 2017,
					"Corso Base di Ingegneria del Software", "Lunedi e Mercoledi",
					"Fondamenti di Informatica,Programmazione ad Oggetti,Interfacce Grafiche e programmazione ad eventi",
					48, 48, "link al materiale", ricca.getMatricola(), corsoDiLaureaInformatica.getCodice(), 10,
					ricca.getCognome(), ricca.getNome(), dataI, dataF);
			Corso corsoProgrammazioneAdOggetti = new Corso(new Long(214), "Programmazione Ad Oggetti", 2017,
					"Lunedi e Giovedi", "Corso Avanzato di Informatica", "Fondamenti di Informatica", 48, 48,
					"link al materiale", ricca.getMatricola(), corsoDiLaureaInformatica.getCodice(), 10,
					ricca.getCognome(), ricca.getNome(), dataI, dataF);
			Corso corsoGeometria = new Corso(new Long(600), "Geometria", 2017, "Corso Base di Geometria",
					"Martedi e Mercoledi", "Nessun requisito", 24, 72, "link al materiale", marino.getMatricola(),
					corsoDiLaureaMatematica.getCodice(), 5, marino.getCognome(), marino.getNome(), dataI, dataF);
			CorsoDao corsoDao = factory.getCorsoDAO();
			corsoDao.save(corsoGeometria);
			corsoDao.save(corsoIngegneriaSW);
			corsoDao.save(corsoProgrammazioneAdOggetti);

			studenteDao.iscriviStudente(st.getMatricola(), (long) 213);
			studenteDao.iscriviStudente(st2.getMatricola(), (long) 213);
			studenteDao.iscriviStudente(st3.getMatricola(), (long) 213);
			studenteDao.iscriviStudente(st4.getMatricola(), (long) 213);
			studenteDao.iscriviStudente(st3.getMatricola(), (long) 600);
			studenteDao.iscriviStudente(st4.getMatricola(), (long) 600);

			// controlla qua
			CalendarioPersonale calendarioPersonaleSt = new CalendarioPersonale(ricca.getMatricola());
			CalendarioPersonaleDao calendarioPersonaleDao = factory.getCalendarioPersonaleDAO();
			Lezione lezione = new Lezione(corsoIngegneriaSW.getCodice(), dataI, 8.30, 10.30, aulaMT5.getId(), 0);
			LezioneDao lezioneDao = factory.getLezioneDAO();
			lezioneDao.save(lezione);
			calendarioPersonaleSt.aggiungiLezione(lezione);
			calendarioPersonaleDao.save(calendarioPersonaleSt);
			// System.out.println("la size degli eventi del calendario è
			// "+calendarioPersonaleSt.getEventi().size());
			// System.out.println(calendarioPersonaleDao.findByPrimaryKey(ricca.getMatricola()).getEventi().get(0).getAnno());
			Timestamp t = new Timestamp(System.currentTimeMillis());
			MessaggioDao messaggioDao = factory.getMessaggioDAO();
			Messaggio messaggio1 = new Messaggio(st3.getMatricola(), ricca.getMatricola(), "Salve", t);
			messaggioDao.save(messaggio1);
			t = new Timestamp(System.currentTimeMillis());
			Messaggio messaggio2 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Salve prof,\r\n"
					+ "Ricordo che sul calendario che mostrate di solito a lezione è fissata una prova per il 21/11 su Git e JUnit.\r\n"
					+ "Volevo chiedervi se è confermato che ci sarà, visto che in questa settimana non c’è stata alcuna lezione per chiedervelo di persona.\r\n"
					+ "Grazie in anticipo,\r\n" + "De Cicco E.\r\n", t);
			messaggioDao.save(messaggio2);
			t = new Timestamp(System.currentTimeMillis());
			Messaggio messaggio3 = new Messaggio(st3.getMatricola(), ricca.getMatricola(), "Come stai?", t);
			messaggioDao.save(messaggio3);
			t = new Timestamp(System.currentTimeMillis());
			Messaggio messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);
			messaggio4 = new Messaggio(ricca.getMatricola(), st3.getMatricola(), "Bene", t);
			messaggioDao.save(messaggio4);

			t = new Timestamp(System.currentTimeMillis());
			Messaggio messaggio = new Messaggio(st3.getMatricola(), perri.getMatricola(), "Salve perri", t);
			messaggioDao.save(messaggio);

			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			Calendar cal2 = Calendar.getInstance();
			cal2.set(2018, Calendar.FEBRUARY, 28); // // 21 marzo 1995
			cal2.set(Calendar.HOUR_OF_DAY, 13);
			cal2.set(Calendar.MINUTE,30);
			Date dateEvento = (Date) cal2.getTime();
			Timestamp inizio = new Timestamp(dateEvento.getTime());
			Timestamp fine = new Timestamp(dateEvento.getTime());

			Evento evento = new Evento("primoEvento", inizio, fine, "Evento di prova");
			eventoDao.save(evento);
			// Functionaaaaa!!

			System.out.println(studenteDao.findByPrimaryKey("111").getNome());
			System.out.println(docenteDao.findByPrimaryKey("555").getNome());
			System.out.println(aulaDao.findByPrimaryKey("MT5").getPosti());
			// System.out.println(calendarioPersonaleDao.findByPrimaryKey("111").getUtente());
			// System.out.println(corsoDao.findByPrimaryKey(new Long(211)).getNome());
			// System.out.println(lezioneDao.findByPrimaryKey(new
			// java.sql.Date(100)).getAula());
			for (Aula aula : aulaDao.findAll()) {
				System.out.println("L'aula è la " + aula.getId() + " appartiene al corso di laurea "
						+ aula.getCorsoDiLaurea() + " ha a disposizione " + aula.getPosti());
			}

			for (Corso cor : studenteDao.getCorsi(st.getMatricola())) {
				System.out.println("Il corso è quello di " + cor.getNome() + " è di " + cor.getCfu());
			}

			System.out.println(corsoDao.getDocente("555").getNome() + " " + corsoDao.getDocente("555").getCognome());

			for (Corso cor : studenteDao.getCorsiIscritto(st.getMatricola())) {
				System.out.println(
						"Il corso a cui " + st.getNome() + " " + st.getCognome() + " è iscritto è " + cor.getNome());
			}
			for (Corso cor : docenteDao.getCorsiDocente(ricca.getMatricola())) {
				System.out.println("Il corso di cui " + ricca.getNome() + " " + ricca.getCognome() + " è titolare è "
						+ cor.getNome());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FUNCTIONAAAAAA!
		// Da register.java non lo so provare :)
	}

}
