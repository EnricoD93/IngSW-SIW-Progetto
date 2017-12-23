package persistence;

import java.sql.Time;
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
		Date date1 = cal.getTime();
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();		
		util.createDatabase();
		
		//ISTANZE FISSE
		
		CorsoDiLaurea corso1=new CorsoDiLaurea(new Long(773),"Informatica");
		CorsoDiLaureaDao corsoDiLaureaDao=factory.getCorsoDiLaureaDAO();
		corsoDiLaureaDao.save(corso1);
		Aula aula1=new Aula("MT5", 100, corso1.getCodice());
		AulaDao aulaDao=factory.getAulaDAO();
		aulaDao.save(aula1);
		
		//ISTANZE DI PROVA POSTGRESS
		
		try {	
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
		Date date;
		date=format.parse("1999-02-02");
		Studente st = new Studente("111", "Ciccio", "Rossi", date,"RFFDSS43D23J878K","ciccio@libero.it","TTTTTT",corso1.getCodice());
		StudenteDao studenteDao=DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		studenteDao.save(st);
		Docente doc = new Docente("555", "Francesco", "Ricca", date,"RFFDSS43D23J878K","ciccio@libero.it","TTTTTT",corso1.getCodice());
		DocenteDao docenteDao=DatabaseManager.getInstance().getDaoFactory().getDocenteDAO();
		docenteDao.save(doc);
		CalendarioPersonale calendarioPersonale=new CalendarioPersonale(st.getMatricola());
		CalendarioPersonaleDao calendarioPersonaleDao =factory.getCalendarioPersonaleDAO();
		calendarioPersonaleDao.save(calendarioPersonale);
		Corso corso=new Corso(new Long(211),"Fondamenti",2017,"Corso Base di Informatica","Nessun requisito","Lunedi e Venerdi",48,48,"link al materiale",doc.getMatricola(),corso1.getCodice());
		CorsoDao corsoDao =factory.getCorsoDAO();
		corsoDao.save(corso);
	
		//controlla qua
		
		//	Lezione lezione=new Lezione(corso.getCodice(),date,10,2,aula1.getId());
//		LezioneDao lezioneDao =factory.getLezioneDAO();
	//	lezioneDao.save(lezione);
	//	Messaggio messaggio=new Messaggio(date,st.getMatricola(),doc.getMatricola(),"Salve",5);
	//	MessaggioDao messaggioDao =factory.getMessaggioDAO();
	//	messaggioDao.save(messaggio);
	
		//Functionaaaaa!!
		
		System.out.println(studenteDao.findByPrimaryKey("111").getNome());  
		System.out.println(docenteDao.findByPrimaryKey("555").getNome());  
		System.out.println(aulaDao.findByPrimaryKey("MT5").getPosti());  
		System.out.println(calendarioPersonaleDao.findByPrimaryKey("111").getUtente());  
		System.out.println(corsoDao.findByPrimaryKey(new Long(211)).getNome());  
		System.out.println(corsoDiLaureaDao.findByPrimaryKey(new Long(773)).getNome());  
	//	System.out.println(lezioneDao.findByPrimaryKey(new java.sql.Date(100)).getAula());  
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		//FUNCTIONAAAAAA!
		//Da register.java non lo so provare :)
	}

}
