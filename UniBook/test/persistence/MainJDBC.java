package persistence;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.course.Aula;
import model.course.CorsoDiLaurea;
import model.course.DescrizioneCorso;
import model.user.CalendarioPersonale;
import model.user.Esame;
import model.user.Messaggio;
import model.user.Utente;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EsameDao;
import persistence.dao.MessaggioDao;
import persistence.dao.UtenteDao;

public class MainJDBC {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(1995, Calendar.MARCH, 21); // // 21 marzo 1995
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		System.out.println("DROP");
		util.dropDatabase();
		System.out.println("CREATE");
		util.createDatabase();

		// ISTANZE FISSE
		CalendarioPersonaleDao calendarioPersonaleDao = factory.getCalendarioPersonaleDAO();
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
		Aula lab31B = new Aula("Laboratorio31B", 45, corsoDiLaureaInformatica.getCodice(), "Cubo 31B - Secondo piano");

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
					"enricodecicco93@gmail.com", "123456", corsoDiLaureaInformatica.getCodice(), 0, "123456", "ciao");
			st3.setProfileImagePath("images/profileImages/169983.jpg");
			date = format.parse("1995-10-23");
			Utente st4 = new Utente("169991", "Martina", "Muto", date, "MTUMTN95R63H919H", "martyvolley23@gmail.com",
					"123456", corsoDiLaureaMatematica.getCodice(), 0, "111111", "ciao");
			st4.setProfileImagePath("images/profileImages/169991.jpg");

			Utente st5 = new Utente("169984", "Marco", "De Luca", date, "DLCMRC95T64T253R", "enricodecicco93@gmail.com",
					"asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st5.setProfileImagePath("images/profileImages/169984.jpg");

			Utente st6 = new Utente("169985", "Nicola", "Greco", date, "GRCNCL95T54Y153T", "enricodecicco93@gmail.com",
					"asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st6.setProfileImagePath("images/profileImages/169985.jpg");

			Utente st7 = new Utente("169986", "Cristian", "De Marco", date, "DMRCRS95T46H564T",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st7.setProfileImagePath("images/profileImages/169986.jpg");
			Utente st8 = new Utente("169987", "Nuccia", "Oliverio", date, "LVRNCC95T67G463B",
					"enricodecicco93@gmail.com", "asdasd", corsoDiLaureaInformatica.getCodice(), 0, "111111", "ciao");
			st8.setProfileImagePath("images/profileImages/169987.jpg");

			CalendarioPersonale calSt3 = new CalendarioPersonale(st3.getMatricola());
			CalendarioPersonale calSt4 = new CalendarioPersonale(st4.getMatricola());
			CalendarioPersonale calSt5 = new CalendarioPersonale(st5.getMatricola());
			CalendarioPersonale calSt6 = new CalendarioPersonale(st6.getMatricola());
			CalendarioPersonale calSt7 = new CalendarioPersonale(st7.getMatricola());
			CalendarioPersonale calSt8 = new CalendarioPersonale(st8.getMatricola());

			UtenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();

			studenteDao.save(st3);
			studenteDao.save(st4);
			studenteDao.save(st5);
			studenteDao.save(st6);
			studenteDao.save(st7);
			studenteDao.save(st8);

			calendarioPersonaleDao.save(calSt3);
			calendarioPersonaleDao.save(calSt4);
			calendarioPersonaleDao.save(calSt5);
			calendarioPersonaleDao.save(calSt6);
			calendarioPersonaleDao.save(calSt7);
			calendarioPersonaleDao.save(calSt8);
			Date dateRicca = format.parse("1964-05-12");
			Utente ricca = new Utente("123456", "Francesco", "Ricca", dateRicca, "RFFDSS43D23J878K",
					"ricca@mat.unical.it", "123456", corsoDiLaureaInformatica.getCodice(), 1, "111111", "ciao");
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

			CalendarioPersonale calRicca = new CalendarioPersonale(ricca.getMatricola());
			CalendarioPersonale calGrasso = new CalendarioPersonale(grasso.getMatricola());
			CalendarioPersonale calVanBon = new CalendarioPersonale(vanbon.getMatricola());
			CalendarioPersonale calTerra = new CalendarioPersonale(terra.getMatricola());
			CalendarioPersonale calSpataro = new CalendarioPersonale(spataro.getMatricola());
			CalendarioPersonale calCali = new CalendarioPersonale(cali.getMatricola());
			CalendarioPersonale calRullo = new CalendarioPersonale(rullo.getMatricola());
			CalendarioPersonale calLeone = new CalendarioPersonale(leone.getMatricola());
			CalendarioPersonale calReale = new CalendarioPersonale(reale.getMatricola());
			CalendarioPersonale calPerri = new CalendarioPersonale(perri.getMatricola());
			CalendarioPersonale calCianciaruso = new CalendarioPersonale(cianciaruso.getMatricola());
			CalendarioPersonale calFuduli = new CalendarioPersonale(fuduli.getMatricola());
			CalendarioPersonale calMontoro = new CalendarioPersonale(montoro.getMatricola());
			CalendarioPersonale calRija = new CalendarioPersonale(rija.getMatricola());
			CalendarioPersonale calSolferino = new CalendarioPersonale(solferino.getMatricola());

			calendarioPersonaleDao.save(calRicca);
			calendarioPersonaleDao.save(calGrasso);
			calendarioPersonaleDao.save(calVanBon);
			calendarioPersonaleDao.save(calTerra);
			calendarioPersonaleDao.save(calSpataro);
			calendarioPersonaleDao.save(calCali);
			calendarioPersonaleDao.save(calRullo);
			calendarioPersonaleDao.save(calLeone);
			calendarioPersonaleDao.save(calReale);
			calendarioPersonaleDao.save(calPerri);
			calendarioPersonaleDao.save(calCianciaruso);
			calendarioPersonaleDao.save(calFuduli);
			calendarioPersonaleDao.save(calMontoro);
			calendarioPersonaleDao.save(calRija);
			calendarioPersonaleDao.save(calSolferino);

			// corsi informatica
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

			// corsi matematica
			DescrizioneCorso corsoAnalisi1 = new DescrizioneCorso(new Long(27002284), "Analisi Matematica 1", 1,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoGeometria1 = new DescrizioneCorso(new Long(27005585), "Geometria 1", 1,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corInglese = new DescrizioneCorso(new Long(27000016), "Inglese - Matematica", 1,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corStoria = new DescrizioneCorso(new Long(27002152), "Storia della Matematica", 1,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoAlgebra = new DescrizioneCorso(new Long(27002038), "Algebra", 1,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoInformatica = new DescrizioneCorso(new Long(27005379), "Informatica", 1,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoMec = new DescrizioneCorso(new Long(27002034), "Meccanica e termodinamica", 1,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoAnalisi2 = new DescrizioneCorso(new Long(27002285), "Analisi Matematica 2", 2,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoGeometria2 = new DescrizioneCorso(new Long(27005606), "Geometria 2", 2,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoLabProg = new DescrizioneCorso(new Long(27002035),
					"Laboratorio di programmazione e calcolo", 2, corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoMecc = new DescrizioneCorso(new Long(27002039), "Meccanica razionale", 2,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoMetodi = new DescrizioneCorso(new Long(27002055),
					"Metodi analitici per la programmazione e il controllo", 2, corsoDiLaureaMatematica.getCodice(), 6,
					24, 72);
			DescrizioneCorso corsoComp = new DescrizioneCorso(new Long(27005225), "Matematica Computazionale", 2,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoFin = new DescrizioneCorso(new Long(27003011), "Matematica Finanziaria", 2,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsolabFi = new DescrizioneCorso(new Long(27006708), "Laboratorio di fisica", 2,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoAnalisi3 = new DescrizioneCorso(new Long(27002286), "Analisi Matematica 3", 3,
					corsoDiLaureaMatematica.getCodice(), 12, 24, 72);
			DescrizioneCorso corsoGeo3 = new DescrizioneCorso(new Long(27005609), "Geometria 3", 3,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoEle = new DescrizioneCorso(new Long(27002037), "Elettricit� e magnetismo", 3,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoProb = new DescrizioneCorso(new Long(27002099),
					"Calcolo delle probabilit� e statistica", 3, corsoDiLaureaMatematica.getCodice(), 9, 24, 72);
			DescrizioneCorso corsoTeo = new DescrizioneCorso(new Long(27002042), "Teorie fisico matematiche", 3,
					corsoDiLaureaMatematica.getCodice(), 6, 24, 72);
			DescrizioneCorso corsoNum = new DescrizioneCorso(new Long(27002043), "Calcolo numerico e programmazione", 3,
					corsoDiLaureaMatematica.getCodice(), 9, 24, 72);

			DescrizioneCorsoDao descCorso = factory.getDescrizioneCorsoDao();
			// salva informatica
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

			// salva matematica
			descCorso.save(corsoAnalisi1);
			descCorso.save(corsoAnalisi2);
			descCorso.save(corsoAnalisi3);
			descCorso.save(corsoGeometria1);
			descCorso.save(corsoGeometria2);
			descCorso.save(corsoGeo3);
			descCorso.save(corsoAlgebra);
			descCorso.save(corsoComp);
			descCorso.save(corsoEle);
			descCorso.save(corsoFin);
			descCorso.save(corsoInformatica);
			descCorso.save(corsolabFi);
			descCorso.save(corsoLabProg);
			descCorso.save(corsoMec);
			descCorso.save(corsoMecc);
			descCorso.save(corsoMetodi);
			descCorso.save(corsoNum);
			descCorso.save(corsoProb);
			descCorso.save(corsoTeo);
			descCorso.save(corInglese);
			descCorso.save(corStoria);

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

			Esame corsoAnalisi1e = new Esame(corsoAnalisi1.getCodice(), corsoAnalisi1.getNome(),
					corsoAnalisi1.getCfu());
			Esame corsoAnalisi2e = new Esame(corsoAnalisi2.getCodice(), corsoAnalisi2.getNome(),
					corsoAnalisi2.getCfu());
			Esame corsoAnalisi3e = new Esame(corsoAnalisi3.getCodice(), corsoAnalisi3.getNome(),
					corsoAnalisi3.getCfu());
			Esame corsoGeometria1e = new Esame(corsoGeometria1.getCodice(), corsoGeometria1.getNome(),
					corsoGeometria1.getCfu());
			Esame corsoGeometria2e = new Esame(corsoGeometria2.getCodice(), corsoGeometria2.getNome(),
					corsoGeometria2.getCfu());
			Esame corsoGeo3e = new Esame(corsoGeo3.getCodice(), corsoGeo3.getNome(), corsoGeo3.getCfu());
			Esame corsoAlgebrae = new Esame(corsoAlgebra.getCodice(), corsoAlgebra.getNome(), corsoAlgebra.getCfu());
			Esame corsoCompe = new Esame(corsoComp.getCodice(), corsoComp.getNome(), corsoComp.getCfu());
			Esame corsoElee = new Esame(corsoEle.getCodice(), corsoEle.getNome(), corsoEle.getCfu());
			Esame corsoFine = new Esame(corsoFin.getCodice(), corsoFin.getNome(), corsoFin.getCfu());
			Esame corsoInformaticae = new Esame(corsoInformatica.getCodice(), corsoInformatica.getNome(),
					corsoInformatica.getCfu());
			Esame corsolabFie = new Esame(corsolabFi.getCodice(), corsolabFi.getNome(), corsolabFi.getCfu());
			Esame corsoLabProge = new Esame(corsoLabProg.getCodice(), corsoLabProg.getNome(), corsoLabProg.getCfu());
			Esame corsoMece = new Esame(corsoMec.getCodice(), corsoMec.getNome(), corsoMec.getCfu());
			Esame corsoMecce = new Esame(corsoMecc.getCodice(), corsoMecc.getNome(), corsoMecc.getCfu());
			Esame corsoMetodie = new Esame(corsoMetodi.getCodice(), corsoMetodi.getNome(), corsoMetodi.getCfu());
			Esame corsoNume = new Esame(corsoNum.getCodice(), corsoNum.getNome(), corsoNum.getCfu());
			Esame corsoProbe = new Esame(corsoProb.getCodice(), corsoProb.getNome(), corsoProb.getCfu());
			Esame corsoTeoe = new Esame(corsoTeo.getCodice(), corsoTeo.getNome(), corsoTeo.getCfu());
			Esame corInglesee = new Esame(corInglese.getCodice(), corInglese.getNome(), corInglese.getCfu());
			Esame corStoriae = new Esame(corStoria.getCodice(), corStoria.getNome(), corStoria.getCfu());

			// salvo informatica
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
			// salvo matematica
			esameDao.save(corsoAnalisi1e);
			esameDao.save(corsoAnalisi2e);
			esameDao.save(corsoAnalisi3e);
			esameDao.save(corsoGeometria1e);
			esameDao.save(corsoGeometria2e);
			esameDao.save(corsoGeo3e);
			esameDao.save(corsoAlgebrae);
			esameDao.save(corsoCompe);
			esameDao.save(corsoElee);
			esameDao.save(corsoFine);
			esameDao.save(corsoInformaticae);
			esameDao.save(corsolabFie);
			esameDao.save(corsoLabProge);
			esameDao.save(corsoMece);
			esameDao.save(corsoMecce);
			esameDao.save(corsoMetodie);
			esameDao.save(corsoNume);
			esameDao.save(corsoProbe);
			esameDao.save(corsoTeoe);
			esameDao.save(corInglesee);
			esameDao.save(corStoriae);

			Timestamp t = new Timestamp(System.currentTimeMillis());
			MessaggioDao messaggioDao = factory.getMessaggioDAO();
			Messaggio messaggio2 = new Messaggio(st3.getMatricola(), ricca.getMatricola(), "Salve prof,\r\n"
					+ "Ricordo che sul calendario che mostrate di solito a lezione � fissata una prova per il 21/11 su Git e JUnit.\r\n"
					+ "Volevo chiedervi se � confermato che ci sar�, visto che in questa settimana non c�� stata alcuna lezione per chiedervelo di persona.\r\n"
					+ "Grazie in anticipo,\r\n" + "De Cicco E.\r\n", t);
			messaggioDao.save(messaggio2);
			t = new Timestamp(System.currentTimeMillis());
			System.out.println("FINISHED");

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
