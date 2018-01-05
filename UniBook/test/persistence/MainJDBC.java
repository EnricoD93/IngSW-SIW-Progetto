package persistence;

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
import model.Lezione;
import model.Messaggio;
import model.Utente;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
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

		CorsoDiLaurea corsoDiLaureaInformatica = new CorsoDiLaurea(new Long(733), "Informatica");
		CorsoDiLaurea corsoDiLaureaMatematica = new CorsoDiLaurea(new Long(726), "Matematica");
		CorsoDiLaureaDao corsoDiLaureaDao = factory.getCorsoDiLaureaDAO();
		corsoDiLaureaDao.save(corsoDiLaureaInformatica);
		corsoDiLaureaDao.save(corsoDiLaureaMatematica);
		Aula aulaMT5 = new Aula("MT5", 100, corsoDiLaureaInformatica.getCodice());
		Aula aulaMT5Bis = new Aula("MT5Bis", 80, corsoDiLaureaInformatica.getCodice());
		Aula aulaMT1 = new Aula("MT1", 200, corsoDiLaureaMatematica.getCodice());
		Aula aulaMT2 = new Aula("MT2", 60, corsoDiLaureaMatematica.getCodice());
		AulaDao aulaDao = factory.getAulaDAO();
		aulaDao.save(aulaMT5);
		aulaDao.save(aulaMT5Bis);
		aulaDao.save(aulaMT1);
		aulaDao.save(aulaMT2);

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
			Utente perri = new Utente("444", "Simona", "Perri", date, "PRRSNM75F67T981V", "perri@mat.unical.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			Utente cianciaruso = new Utente("333", "Nuccia", "Cianciaruso", date, "NCCCRC87H76H473S",
					"cianciaruso@mat.unical.it", "NNNNNN", corsoDiLaureaInformatica.getCodice(), 1, "111111");
			Utente marino = new Utente("222", "Giuseppe", "Marino", date, "GPPMNR64Y34G764L", "marino@mat.unical.it",
					"PPPPPP", corsoDiLaureaMatematica.getCodice(), 1, "111111");
			UtenteDao docenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			docenteDao.save(ricca);
			docenteDao.save(perri);
			docenteDao.save(cianciaruso);
			docenteDao.save(marino);
			CalendarioPersonale calendarioPersonaleSt = new CalendarioPersonale(st.getMatricola());
			CalendarioPersonale calendarioPersonaleRicca = new CalendarioPersonale(ricca.getMatricola());
			CalendarioPersonale calendarioPersonalePerri = new CalendarioPersonale(perri.getMatricola());
			CalendarioPersonale calendarioPersonaleCianciaruso = new CalendarioPersonale(cianciaruso.getMatricola());
			CalendarioPersonale calendarioPersonaleMarino = new CalendarioPersonale(marino.getMatricola());
			CalendarioPersonaleDao calendarioPersonaleDao = factory.getCalendarioPersonaleDAO();
			calendarioPersonaleDao.save(calendarioPersonaleSt);
			calendarioPersonaleDao.save(calendarioPersonaleRicca);
			calendarioPersonaleDao.save(calendarioPersonalePerri);
			calendarioPersonaleDao.save(calendarioPersonaleCianciaruso);
			calendarioPersonaleDao.save(calendarioPersonaleMarino);
			DescrizioneCorso corsoFondamenti = new DescrizioneCorso(new Long(211), "Fondamenti di Informatica", 1, corsoDiLaureaInformatica.getCodice(), 10,48,48);
			DescrizioneCorsoDao descCorso= factory.getDescrizioneCorsoDao();
			descCorso.save(corsoFondamenti);
			System.out.println("la descrizione: "+ descCorso.findByPrimaryKey(new Long(211)).getNome());
			Corso corsoAnalisi = new Corso(new Long(212), "Analisi Matematica", 2017, "Corso Base di Analisi",
					"Nessun requisito", "Martedi e Giovedi", 24, 72, "link al materiale", cianciaruso.getMatricola(),
					corsoDiLaureaInformatica.getCodice(), 10, cianciaruso.getCognome(), cianciaruso.getNome());
			Corso corsoIngegneria = new Corso(new Long(213), "Ingegneria del Software", 2017,
					"Corso Base di Ingegneria del Software",
					"Fondamenti di Informatica,Programmazione ad Oggetti,Interfacce Grafiche e programmazione ad eventi",
					"Lunedi e Mercoledi", 48, 48, "link al materiale", ricca.getMatricola(),
					corsoDiLaureaInformatica.getCodice(), 10, ricca.getCognome(), ricca.getNome());
			Corso corsoProgrammazioneAdOggetti = new Corso(new Long(214), "Programmazione Ad Oggetti", 2017,
					"Corso Avanzato di Informatica", "Fondamenti di Informatica", "Lunedi e Giovedi", 48, 48,
					"link al materiale", ricca.getMatricola(), corsoDiLaureaInformatica.getCodice(), 10,
					ricca.getCognome(), ricca.getNome());
			Corso corsoGeometria = new Corso(new Long(600), "Geometria", 2017, "Corso Base di Geometria",
					"Nessun requisito", "Martedi e Mercoledi", 24, 72, "link al materiale", marino.getMatricola(),
					corsoDiLaureaMatematica.getCodice(), 5, marino.getCognome(), marino.getNome());
			CorsoDao corsoDao = factory.getCorsoDAO();
			corsoDao.save(corsoAnalisi);
			corsoDao.save(corsoGeometria);
			corsoDao.save(corsoIngegneria);
			corsoDao.save(corsoProgrammazioneAdOggetti);

		//	studenteDao.iscriviStudente(st.getMatricola(), corsoFondamenti.getCodice());

			// controlla qua

//			Lezione lezione = new Lezione(corsoFondamenti.getCodice(), date, 10, 2, aulaMT5.getId());
//			LezioneDao lezioneDao = factory.getLezioneDAO();
//			lezioneDao.save(lezione);
			Messaggio messaggio = new Messaggio(date, st.getMatricola(), ricca.getMatricola(), "Salve", 5);
			MessaggioDao messaggioDao = factory.getMessaggioDAO();
			messaggioDao.save(messaggio);

			// Functionaaaaa!!

			System.out.println(studenteDao.findByPrimaryKey("111").getNome());
			System.out.println(docenteDao.findByPrimaryKey("555").getNome());
			System.out.println(aulaDao.findByPrimaryKey("MT5").getPosti());
			System.out.println(calendarioPersonaleDao.findByPrimaryKey("111").getUtente());
	//		System.out.println(corsoDao.findByPrimaryKey(new Long(211)).getNome());
			System.out.println(corsoDiLaureaDao.findByPrimaryKey(new Long(733)).getNome());
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
