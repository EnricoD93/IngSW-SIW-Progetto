package persistence;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.course.Aula;
import model.course.Corso;
import model.course.CorsoDiLaurea;
import model.course.DescrizioneCorso;
import model.course.Lezione;
import model.user.CalendarioPersonale;
import model.user.Esame;
import model.user.GiornoCalendario;
import model.user.Messaggio;
import model.user.Notifica;
import model.user.Utente;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EsameDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.NotificaDao;
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
		Aula aulaMT6 = new Aula("MT6", 72, corsoDiLaureaInformatica.getCodice(), "Cubo 30B - Piano terra");
		Aula aulaMT8 = new Aula("MT8", 28, corsoDiLaureaMatematica.getCodice(), "Cubo 30B - Piano terra");
		Aula aulaMT13 = new Aula("MT13", 32, corsoDiLaureaInformatica.getCodice(), "Cubo 31B - Secondo piano");
		Aula aulaMT14 = new Aula("MT14", 24, corsoDiLaureaInformatica.getCodice(), "Cubo 31B - Secondo piano");
		Aula aulaMT15 = new Aula("MT15", 45, corsoDiLaureaMatematica.getCodice(), "Cubo 31A - Primo piano");
		Aula lab31B = new Aula("Laboratorio 31 B", 45, corsoDiLaureaInformatica.getCodice(),
				"Cubo 31B - Secondo piano");

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
			date = format.parse("1993-06-19");
			Utente st3 = new Utente("169983", "Enrico", "De Cicco", date, "DCCNRC93H19Z112B",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st3.setProfileImagePath("images/profileImages/169983.jpg");
			date = format.parse("1995-10-23");
			Utente st4 = new Utente("169991", "Martina", "Muto", date, "MTUMTN95R63H919H", "martyvolley23@gmail.com",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st4.setProfileImagePath("images/profileImages/169991.jpg");
			
			Utente st5 = new Utente("169984", "Marco", "De Luca", date, "DLCMRC95T64T253R",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st5.setProfileImagePath("images/profileImages/169984.jpg");
			
			Utente st6 = new Utente("169985", "Nicola", "Greco", date, "GRCNCL95T54Y153T",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st6.setProfileImagePath("images/profileImages/169985.jpg");
			
			Utente st7 = new Utente("169986", "Cristian", "De Marco", date, "DMRCRS95T46H564T",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st7.setProfileImagePath("images/profileImages/169986.jpg");
			Utente st8 = new Utente("169987", "Nuccia", "Oliverio", date, "LVRNCC95T67G463B",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st8.setProfileImagePath("images/profileImages/169987.jpg");
			
			UtenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();

			studenteDao.save(st3);
			studenteDao.save(st4);
			studenteDao.save(st5);
			studenteDao.save(st6);
			studenteDao.save(st7);
			studenteDao.save(st8);

			Date dateRicca = format.parse("1964-05-12");
			Utente ricca = new Utente("555", "Francesco", "Ricca", dateRicca, "RFFDSS43D23J878K", "ricca@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			ricca.setProfileImagePath("images/profileImages/ricca.png");
			Utente spataro = new Utente("12547", "William", "Spataro", dateRicca, "SPTWLM43D23J878K",
					"spataro@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			spataro.setProfileImagePath("images/profileImages/spataro.JPG");
			Utente cali = new Utente("12437", "Francesco", "Calimeri", dateRicca, "CLMFRC43D23J878K",
					"calimeri@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			cali.setProfileImagePath("images/profileImages/cali.jpg");
			Utente grasso = new Utente("124547", "Giovanni", "Grasso", dateRicca, "GRSGVN43D23J878K",
					"grasso@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			grasso.setProfileImagePath("images/profileImages/grasso.jpg");
			Utente leone = new Utente("12467", "Nicola", "Leone", dateRicca, "LNENCL43D23J878K", "leone@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			leone.setProfileImagePath("images/profileImages/leone.jpg");
			Utente reale = new Utente("12448", "Kristian", "Reale", dateRicca, "RLEKRS43D23J878K",
					"reale@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			reale.setProfileImagePath("images/profileImages/reale.jpg");
			Utente rullo = new Utente("12442", "Pasquale", "Rullo", dateRicca, "RLLPSQ43D23J878K",
					"rullo@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			rullo.setProfileImagePath("images/profileImages/rullo.jpg");
			Utente terra = new Utente("126547", "Giorgio", "Terracina", dateRicca, "TRCGRG43D23J878K",
					"terracina@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			terra.setProfileImagePath("images/profileImages/terracina.jpg");
			Utente vanbon = new Utente("124677", "John", "Van Bon", dateRicca, "VNBJHN43D23J878K",
					"vanbon@mat.unical.it", "TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			vanbon.setProfileImagePath("images/profileImages/vanbon.jpg");
			Utente perri = new Utente("444", "Simona", "Perri", date, "PRRSNM75F67T981V", "perri@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			perri.setProfileImagePath("images/profileImages/simona.jpg");
			Utente cianciaruso = new Utente("333", "Nuccia", "Cianciaruso", date, "NCCCRC87H76H473S",
					"cianciaruso@mat.unical.it", "NNNNNN", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
			cianciaruso.setProfileImagePath("images/profileImages/cianci.jpg");
			Utente fuduli = new Utente("222", "Antonio", "Fuduli", date, "FDLNTN64Y34G764L", "fuduli@mat.unical.it",
					"PPPPPP", corsoDiLaureaMatematica.getCodice(), 1, "111111", "ciao");
			fuduli.setProfileImagePath("images/profileImages/fuduli.jpg");
			Utente montoro = new Utente("999", "Luigi", "Montoro", date, "LGUMNT64Y35F761T", "montoro@mat.unical.it",
					"PPPPPP", corsoDiLaureaMatematica.getCodice(), 1, "111111", "ciao");
			montoro.setProfileImagePath("images/profileImages/montoro.jpg");
			Utente rija = new Utente("121", "Maurizio", "Rija", date, "MRZRJI70G15Z323T", "rija@unical.it", "PPPPPP",
					corsoDiLaureaMatematica.getCodice(), 1, "111111", "ciao");
			rija.setProfileImagePath("images/profileImages/rija.jpg");
			Utente solferino = new Utente("131", "Viviana", "Solferino", date, "SLFVIV66T45T656Y",
					"solferino@unical.it", "PPPPPP", corsoDiLaureaMatematica.getCodice(), 1, "111111", "ciao");
			solferino.setProfileImagePath("images/profileImages/solferino.jpg");
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
			docenteDao.save(fuduli);
			docenteDao.save(montoro);
			docenteDao.save(rija);
			docenteDao.save(solferino);

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
					corsoDiLaureaInformatica.getCodice(), 12, 24, 72);
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
					corsoDiLaureaInformatica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoMAD = new DescrizioneCorso(new Long(27002041), "Matematica per l'Analisi dei Dati", 2,
					corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoSistemi = new DescrizioneCorso(new Long(27002215), "Sistemi Operativi e Reti", 3,
					corsoDiLaureaInformatica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoIntelligenza = new DescrizioneCorso(new Long(27002216), "Intelligenza Artificiale", 3,
					corsoDiLaureaInformatica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoRicerca = new DescrizioneCorso(new Long(27000023), "Ricerca Operativa", 2,
					corsoDiLaureaInformatica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoParalleli = new DescrizioneCorso(new Long(27002222),
					"Algoritmi Paralleli e Sistemi Distribuiti", 2, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoLinguaggi = new DescrizioneCorso(new Long(27002213),
					"Linguaggi e logiche per l'informatica", 1, corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoFisica = new DescrizioneCorso(new Long(27000005), "Fisica", 1,
					corsoDiLaureaInformatica.getCodice(), 6, 24, 72);
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
			
			EsameDao esameDao = factory.getEsameDAO();

			Esame esameFondamenti = new Esame(corsoFondamenti.getCodice(), corsoFondamenti.getNome(),
					corsoFondamenti.getCfu());
			Esame esameAnalisi = new Esame(corsoAnalisi.getCodice(), corsoAnalisi.getNome(), corsoAnalisi.getCfu());
			Esame esameOggetti = new Esame(corsoOggetti.getCodice(), corsoOggetti.getNome(), corsoOggetti.getCfu());
			Esame esameAlgoritmi = new Esame(corsoAlgoritmi.getCodice(), corsoAlgoritmi.getNome(),
					corsoAlgoritmi.getCfu());
			Esame esameArchitettura = new Esame(corsoArchitettura.getCodice(), corsoArchitettura.getNome(),
					corsoArchitettura.getCfu());
			Esame esameBasi = new Esame(corsoBasi.getCodice(), corsoBasi.getNome(), corsoBasi.getCfu());
			Esame esameIngegneria = new Esame(corsoIngegneria.getCodice(), corsoIngegneria.getNome(),
					corsoIngegneria.getCfu());
			Esame esameSIW = new Esame(corsoSIW.getCodice(), corsoSIW.getNome(), corsoSIW.getCfu());
			Esame esameIGPE = new Esame(corsoIGPE.getCodice(), corsoIGPE.getNome(), corsoIGPE.getCfu());
			Esame esameDiscreta = new Esame(corsoDiscreta.getCodice(), corsoDiscreta.getNome(), corsoDiscreta.getCfu());
			Esame esameMAD = new Esame(corsoMAD.getCodice(), corsoMAD.getNome(), corsoMAD.getCfu());
			Esame esameSistemi = new Esame(corsoSistemi.getCodice(), corsoSistemi.getNome(), corsoSistemi.getCfu());
			Esame esameIntelligenza = new Esame(corsoIntelligenza.getCodice(), corsoIntelligenza.getNome(),
					corsoIntelligenza.getCfu());
			Esame esameRicerca = new Esame(corsoRicerca.getCodice(), corsoRicerca.getNome(), corsoRicerca.getCfu());
			Esame esameParalleli = new Esame(corsoParalleli.getCodice(), corsoParalleli.getNome(),
					corsoParalleli.getCfu());
			Esame esameLinguaggi = new Esame(corsoLinguaggi.getCodice(), corsoLinguaggi.getNome(),
					corsoLinguaggi.getCfu());
			Esame esameFisica = new Esame(corsoFisica.getCodice(), corsoFisica.getNome(), corsoFisica.getCfu());
			Esame esameInglese = new Esame(corsoInglese.getCodice(), corsoInglese.getNome(), corsoInglese.getCfu());
			Esame esameEconomia = new Esame(corsoEconomia.getCodice(), corsoEconomia.getNome(), corsoEconomia.getCfu());
			esameDao.save(esameEconomia);
			esameDao.save(esameInglese);
			esameDao.save(esameFisica);
			esameDao.save(esameLinguaggi);
			esameDao.save(esameParalleli);
			esameDao.save(esameRicerca);
			esameDao.save(esameIntelligenza);
			esameDao.save(esameSistemi);
			esameDao.save(esameMAD);
			esameDao.save(esameDiscreta);
			esameDao.save(esameIGPE);
			esameDao.save(esameSIW);
			esameDao.save(esameIngegneria);
			esameDao.save(esameBasi);
			esameDao.save(esameArchitettura);
			esameDao.save(esameAlgoritmi);
			esameDao.save(esameOggetti);
			esameDao.save(esameAnalisi);
			esameDao.save(esameFondamenti);

			// controlla qua
			CalendarioPersonale calendarioPersonaleSt = new CalendarioPersonale(ricca.getMatricola());
			CalendarioPersonale calendarioPersonaleM = new CalendarioPersonale(st4.getMatricola());
			CalendarioPersonale calendarioPersonaleE = new CalendarioPersonale(st3.getMatricola());
			CalendarioPersonaleDao calendarioPersonaleDao = factory.getCalendarioPersonaleDAO();
			calendarioPersonaleDao.save(calendarioPersonaleSt);
			calendarioPersonaleDao.save(calendarioPersonaleM);
			calendarioPersonaleDao.save(calendarioPersonaleE);
		
			Timestamp t = new Timestamp(System.currentTimeMillis());
			MessaggioDao messaggioDao = factory.getMessaggioDAO();
			Messaggio messaggio2 = new Messaggio(st3.getMatricola(), ricca.getMatricola(), "Salve prof,\r\n"
					+ "Ricordo che sul calendario che mostrate di solito a lezione è fissata una prova per il 21/11 su Git e JUnit.\r\n"
					+ "Volevo chiedervi se è confermato che ci sarà, visto che in questa settimana non c’è stata alcuna lezione per chiedervelo di persona.\r\n"
					+ "Grazie in anticipo,\r\n" + "De Cicco E.\r\n", t);
			messaggioDao.save(messaggio2);
			t = new Timestamp(System.currentTimeMillis());

			NotificaDao notificaDao = DatabaseManager.getInstance().getDaoFactory().getNotificaDAO();
			t = new Timestamp(System.currentTimeMillis());
			Notifica notifica = new Notifica(st3.getMatricola(), t, 2);
			notificaDao.save(notifica);
			
			// Functionaaaaa!!

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FUNCTIONAAAAAA!
		// Da register.java non lo so provare :)
		System.out.println("done");
	}

}
