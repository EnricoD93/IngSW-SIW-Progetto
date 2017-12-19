package persistence;

import persistence.dao.AulaDao;
import persistence.dao.AvvisoDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DocenteDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.StudenteDao;

public abstract class DAOFactory {
	public static final int POSTGRESQL = 1;

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		case POSTGRESQL:
			return new PostgresDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract StudenteDao getStudenteDAO();

	public abstract DocenteDao getDocenteDAO();

	public abstract MessaggioDao getMessaggioDAO();

	public abstract AulaDao getAulaDAO();

	public abstract LezioneDao getLezioneDAO();

	public abstract CorsoDao getCorsoDAO();

	public abstract CorsoDiLaureaDao getCorsoDiLaureaDAO();

	public abstract AvvisoDao getAvvisoDAO();

}
