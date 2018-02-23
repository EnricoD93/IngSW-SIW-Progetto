package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user.CalendarioPersonale;
import model.user.Evento;
import model.user.Utente;
import persistence.dao.CalendarioPersonaleDao;

public class CalendarioPersonaleDaoJDBC implements CalendarioPersonaleDao {
	private DataSource dataSource;

	public CalendarioPersonaleDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CalendarioPersonale calendarioPersonale) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into calendariopersonale(matricola) values (?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, calendarioPersonale.getMatricola());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public CalendarioPersonale findByPrimaryKey(String matricola) {
		Connection connection = this.dataSource.getConnection();
		CalendarioPersonale calendarioPersonale = null;
		try {
			String query = "select * from calendariopersonale where matricola = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				calendarioPersonale = new CalendarioPersonale();
				calendarioPersonale.setMatricola(result.getString("matricola"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return calendarioPersonale;
	}

	@Override
	public List<CalendarioPersonale> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CalendarioPersonale calendarioPersonale) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Evento> findAllEventsUtente(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Evento> listaEventi = new ArrayList<>();
		try {
			PreparedStatement statement;
			String query = "select * from evento,contiene where contiene.calendariopersonale = ? AND evento.id=contiene.evento";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Evento evento = new Evento();
				evento.setId(result.getLong("id"));
				evento.setTitle(result.getString("title"));
				evento.setInizio(result.getTimestamp("inizio"));
				evento.setFine(result.getTimestamp("fine"));
				evento.setNota(result.getString("nota"));
				listaEventi.add(evento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return listaEventi;
	}

	@Override
	public void delete(CalendarioPersonale calendarioPersonale) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM calendariopersonale WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, calendarioPersonale.getMatricola());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void saveEvent(String matricola, Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into contiene(calendariopersonale,evento) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, matricola);
			statement.setLong(2, evento.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	@Override
	public void deleteEvent(String matricola, Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "delete FROM contiene WHERE calendariopersonale=? AND evento=?";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, matricola);
			statement.setLong(2, evento.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	@Override
	public int findNumberEvents(String matricola) {
		Connection connection = this.dataSource.getConnection();
		int count=0;
		try {
			PreparedStatement statement;
			String query = "select count(evento)\r\n" + 
					"from evento, contiene, calendariopersonale \r\n" + 
					"where evento.id=contiene.evento AND contiene.calendariopersonale=calendariopersonale \r\n" + 
					"AND calendariopersonale.matricola=? AND NOT EXISTS (select e\r\n" + 
					"                                                          	from lezione, evento e\r\n" + 
					"                                                          	where lezione.id=evento.id AND e.id=lezione.id)";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return count;
	}
	@Override
	public int findNumberLessons(String matricola) {
		Connection connection = this.dataSource.getConnection();
		int count=0;
		try {
			PreparedStatement statement;
			String query = "select count(evento)\r\n" + 
					"	from lezione, contiene, calendariopersonale\r\n" + 
					"	where lezione.id=contiene.evento AND contiene.calendariopersonale=calendariopersonale \r\n" + 
					"	AND calendariopersonale.matricola=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return count;
	}

	

}
