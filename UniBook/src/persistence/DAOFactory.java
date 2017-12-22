package persistence;

import persistence.dao.AulaDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DocenteDao;
import persistence.dao.EsameDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.StudenteDao;

public abstract class DAOFactory {
	public static final int POSTGRESQL = 1;

	public static DAOFactory getDAOFactory(int whichFactory) {
		if (whichFactory == POSTGRESQL)
			return new PostgresDAOFactory();
		else
			return null;
	}

	public abstract StudenteDao getStudenteDAO();

	public abstract DocenteDao getDocenteDAO();

	public abstract MessaggioDao getMessaggioDAO();

	public abstract AulaDao getAulaDAO();

	public abstract LezioneDao getLezioneDAO();

	public abstract CorsoDao getCorsoDAO();

	public abstract CorsoDiLaureaDao getCorsoDiLaureaDAO();

	public abstract CalendarioPersonaleDao getCalendarioPersonaleDAO();

	public abstract EsameDao getEsameDAO();

	public abstract UtilDao getUtilDAO();

}
