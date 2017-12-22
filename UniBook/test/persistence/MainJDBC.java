package persistence;

import java.util.Calendar;
import java.util.Date;

import model.CorsoDiLaurea;
import model.Studente;
import persistence.dao.CorsoDiLaureaDao;
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
		CorsoDiLaurea corso1=new CorsoDiLaurea(0733,"Informatica");
		CorsoDiLaureaDao corsoDao=factory.getCorsoDiLaureaDAO();
		corsoDao.save(corso1);
		
		Studente studente1 = new Studente("168987","Muto","Martina", date1, "DCCNRC93H19Z112B", "martyvolley23@gmail.com", "ciccio", corso1.getCodice());
		StudenteDao studenteDao=factory.getStudenteDAO();
		studenteDao.save(studente1);
	}

}
