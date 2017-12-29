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
import model.Docente;
import model.Lezione;
import model.Messaggio;
import model.Studente;
import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DocenteDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.StudenteDao;

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

		CorsoDiLaurea corsoDiLaureaInformatica = new CorsoDiLaurea(new Long(773), "Informatica");
		CorsoDiLaurea corsoDiLaureaMatematica = new CorsoDiLaurea(new Long(552), "Matematica");
		CorsoDiLaureaDao corsoDiLaureaDao = factory.getCorsoDiLaureaDAO();
		corsoDiLaureaDao.save(corsoDiLaureaInformatica);
		corsoDiLaureaDao.save(corsoDiLaureaMatematica);
		Aula aulaMT5 = new Aula("MT5", 100, corsoDiLaureaInformatica.getCodice());
		Aula aulaMT1 = new Aula("MT1", 200, corsoDiLaureaMatematica.getCodice());
		AulaDao aulaDao = factory.getAulaDAO();
		aulaDao.save(aulaMT5);
		aulaDao.save(aulaMT1);

		// ISTANZE DI PROVA POSTGRESS

		try {
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
			Date date;

			date = format.parse("1999-02-02");
			Studente st = new Studente("111", "Ciccio", "Rossi", date, "RFFDSS43D23J878K", "ciccio@libero.it", "TTTTTT",
					corsoDiLaureaInformatica.getCodice());
			StudenteDao studenteDao = DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
			studenteDao.save(st);
			Docente ricca = new Docente("555", "Francesco", "Ricca", date, "RFFDSS43D23J878K", "ciccio@libero.it",
					"TTTTTT", corsoDiLaureaInformatica.getCodice());
			Docente cianciaruso = new Docente("333", "Nuccia", "Cianciaruso", date, "NCCCRC87H76H473S", "cianciaruso@libero.it",
					"NNNNNN", corsoDiLaureaInformatica.getCodice());
			Docente marino = new Docente("222", "Giuseppe", "Marino", date, "GPPMNR64Y34G764L", "marino@libero.it",
					"PPPPPP", corsoDiLaureaMatematica.getCodice());
			DocenteDao docenteDao = DatabaseManager.getInstance().getDaoFactory().getDocenteDAO();
			docenteDao.save(ricca);
			docenteDao.save(cianciaruso);
			CalendarioPersonale calendarioPersonale = new CalendarioPersonale(st.getMatricola());
			CalendarioPersonaleDao calendarioPersonaleDao = factory.getCalendarioPersonaleDAO();
			calendarioPersonaleDao.save(calendarioPersonale);
			Corso corsoFondamenti = new Corso(new Long(211), "Fondamenti", 2017, "Corso Base di Informatica", "Nessun requisito",
					"Lunedi e Venerdi", 48, 48, "link al materiale", ricca.getMatricola(), corsoDiLaureaInformatica.getCodice());
			Corso corsoAnalisi = new Corso(new Long(212), "Analisi", 2017, "Corso Base di Analisi", "Nessun requisito",
					"Martedi e Giovedi", 24, 72, "link al materiale", cianciaruso.getMatricola(), corsoDiLaureaInformatica.getCodice());
			Corso corsoGeometria = new Corso(new Long(600), "Geometria", 2017, "Corso Base di Geometria", "Nessun requisito",
					"Martedi e Mercoledi", 24, 72, "link al materiale", marino.getMatricola(), corsoDiLaureaMatematica.getCodice());
			CorsoDao corsoDao = factory.getCorsoDAO();
			corsoDao.save(corsoFondamenti);
			corsoDao.save(corsoAnalisi);

			// controlla qua

			Lezione lezione = new Lezione(corsoFondamenti.getCodice(), date, 10, 2, aulaMT5.getId());
			LezioneDao lezioneDao = factory.getLezioneDAO();
			lezioneDao.save(lezione);
			Messaggio messaggio = new Messaggio(date, st.getMatricola(), ricca.getMatricola(), "Salve", 5);
			MessaggioDao messaggioDao = factory.getMessaggioDAO();
			messaggioDao.save(messaggio);

			// Functionaaaaa!!

			System.out.println(studenteDao.findByPrimaryKey("111").getNome());
			System.out.println(docenteDao.findByPrimaryKey("555").getNome());
			System.out.println(aulaDao.findByPrimaryKey("MT5").getPosti());
			System.out.println(calendarioPersonaleDao.findByPrimaryKey("111").getUtente());
			System.out.println(corsoDao.findByPrimaryKey(new Long(211)).getNome());
			System.out.println(corsoDiLaureaDao.findByPrimaryKey(new Long(773)).getNome());
			// System.out.println(lezioneDao.findByPrimaryKey(new
			// java.sql.Date(100)).getAula());
			for (Aula aula : aulaDao.findAll()) {
				System.out.println("L'aula � la " + aula.getId() + " appartiene al corso di laurea "
						+ aula.getCorsoDiLaurea() + " ha a disposizione " + aula.getPosti());
			}
	
			for (Corso cor : studenteDao.getCorsi(st)) {
				System.out.println("Il corso � quello di "+cor.getNome());
			}
		
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FUNCTIONAAAAAA!
		// Da register.java non lo so provare :)
	}

}
