package persistence;

import persistence.dao.AulaDao;
import persistence.dao.AvvisoDao;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.CorsoDiLaureaDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EsameDao;
import persistence.dao.EventoDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.NotificaDao;
import persistence.dao.UtenteDao;

public class PostgresDAOFactory extends DAOFactory {

	private static DataSource dataSource;

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		
			dataSource = new DataSource("jdbc:postgresql://horton.elephantsql.com:5432/vmblzhlt", "vmblzhlt",
					"_Ty3NDgk6VFJ896cOYJt0D8Emu-vzxl0");
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
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
	public CalendarioPersonaleDao getCalendarioPersonaleDAO() {
		return new CalendarioPersonaleDaoJDBC(dataSource);
	}

	@Override
	public EsameDao getEsameDAO() {
		return new EsameDaoJDBC(dataSource);
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

	@Override
	public EventoDao getEventoDAO() {
		return new EventoDaoJDBC(dataSource);
	}

	@Override
	public AvvisoDao getAvvisoDAO() {
		return new AvvisoDaoJDBC(dataSource);
	}

	@Override
	public NotificaDao getNotificaDAO() {
		return new NotificaDaoJDBC(dataSource);
	}
}
