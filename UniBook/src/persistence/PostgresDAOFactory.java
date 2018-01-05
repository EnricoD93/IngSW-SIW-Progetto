package persistence;

import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EsameDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.UtenteDao;

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
	
//	@Override
//	public UtenteDao getStudenteDAO() {
//		return new StudenteDaoJDBC(dataSource);
//	}
//
//	@Override
//	public UtenteDao getDocenteDAO() {
//		return new DocenteDaoJDBC(dataSource);
//	}

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
	public CalendarioPersonaleDao getCalendarioPersonaleDAO() {
		return new CalendarioPersonaleDaoJDBC(dataSource);
	}

	@Override
	public EsameDao getEsameDAO() {
		return null;
	}

	@Override
	public UtilDao getUtilDAO() {
		return new UtilDao(dataSource);
	}

	@Override
	public UtenteDao getUtenteDao() {
		return new UtenteDaoJDBC(dataSource);
	}
	@Override
	public DescrizioneCorsoDao getDescrizioneCorsoDao() {
		return new DescrizioneCorsoDaoJDBC(dataSource);
	}

}
