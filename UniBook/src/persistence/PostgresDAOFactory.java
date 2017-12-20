package persistence;

import persistence.dao.AulaDao;
import persistence.dao.AvvisoDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DocenteDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.StudenteDao;

public class PostgresDAOFactory extends DAOFactory {
	
	private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/Unibook","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	
	@Override
	public StudenteDao getStudenteDAO() {
		return new StudenteDaoJDBC(dataSource);
	}

	@Override
	public DocenteDao getDocenteDAO() {
		return new DocenteDaoJDBC(dataSource);
	}

	@Override
	public MessaggioDao getMessaggioDAO() {
		return new MessaggioDaoJDBC(dataSource);
	}

	@Override
	public AulaDao getAulaDAO() {
		return new AulaDaoJDBC(dataSource);
	}

	@Override
	public LezioneDao getLezioneDAO() {
		return new LezioneDaoJDBC(dataSource);
	}

	@Override
	public CorsoDao getCorsoDAO() {
		return new CorsoDaoJDBC(dataSource);
	}

	@Override
	public CorsoDiLaureaDao getCorsoDiLaureaDAO() {
		return new CorsoDiLaureaDaoJDBC(dataSource);
	}

	@Override
	public AvvisoDao getAvvisoDAO() {
		return new AvvisoDaoJDBC(dataSource);
	}

}