package persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.course.Corso;
import model.course.Lezione;
import model.user.Esame;
import model.user.Evento;
import model.user.GiornoCalendario;
import model.user.Messaggio;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.LezioneDao;

public class LezioneDaoJDBC implements LezioneDao {
	private DataSource dataSource;

	public LezioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Lezione lezione) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			lezione.setId(id);
			String insert = "insert into lezione(id,data,ora_inizio,ora_fine,corso,aula,tipo) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, lezione.getId());
			statement.setDate(2, lezione.getData().GiornoCalendarioToDate());
			statement.setTimestamp(3, lezione.getOraInizio());
			statement.setTimestamp(4, lezione.getOraFine());
			statement.setLong(5, lezione.getCorso());
			statement.setString(6, lezione.getAula());
			statement.setInt(7, lezione.getTipo());

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
	public Lezione findByPrimaryKey(long id) {
		Connection connection = this.dataSource.getConnection();
		Lezione lezione = null;
		try {
			String query = "select * from lezione where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				lezione = new Lezione();
				lezione.setId(result.getLong("id"));
				GiornoCalendario g= new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data"));
				lezione.setData(g);
				lezione.setAula(result.getString("aula"));
				lezione.setCorso(result.getLong("corso"));
				lezione.setOraInizio(result.getTimestamp("ora_inizio"));
				lezione.setOraFine(result.getTimestamp("ora_fine"));
				lezione.setTipo(result.getInt("tipo"));
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
		return lezione;
	}

	@Override
	public List<Lezione> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Lezione> lezioni = new ArrayList<>();
		try {
			Lezione lezione;
			PreparedStatement statement;
			String query = "select * from lezione";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				lezione = new Lezione();
				lezione.setId(result.getInt("id"));
				lezione.setAula(result.getString("aula"));
				lezione.setCorso(result.getLong("corso"));
				GiornoCalendario g= new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data"));
				lezione.setData(g);
				lezione.setOraInizio(result.getTimestamp("ora_inizio"));
				lezione.setOraFine(result.getTimestamp("ora_fine"));
				lezione.setTipo(result.getInt("tipo"));
				lezioni.add(lezione);
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
		return lezioni;
	}

	@Override
	public void update(Lezione lezione) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Lezione lezione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM lezione WHERE data = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setDate(1, lezione.getData().GiornoCalendarioToDate());
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
	public List<Evento> findCourseLessons(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Evento> lezioni = new ArrayList<>();
		Evento lezione = null;
		try {
			String query = "select * from evento,lezione where evento.id=lezione.id AND lezione.corso = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Evento evento = new Evento();
				evento.setId(result.getLong("id"));
				evento.setTitle(result.getString("title"));
				evento.setInizio(result.getTimestamp("inizio"));
				evento.setFine(result.getTimestamp("fine"));
				evento.setNota(result.getString("nota"));
				lezioni.add(evento);
			}
			System.out.println("JDBC: "+lezioni.size());
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return lezioni;

	}

	@Override
	public String findLessonName(Long codice) {
		Connection connection = this.dataSource.getConnection();
		String lezione = null;
		try {
			String query = "select * from corso where codice= ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				lezione = result.getString("nome");
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
		return lezione;

	}

	@Override
	public void deleteLessonByEvent(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM lezione WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, evento.getId());
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
	public void eliminaLezioniDalCalendario(List<Evento> listaEventiCal, List<Evento> listaLezioni,
			CalendarioPersonaleDao calendarioPersonaleDao, String matricola) {
		for (int i = 0; i < listaLezioni.size(); i++) {
			for (int j = 0; j < listaEventiCal.size(); j++) {
				if (listaLezioni.get(i).getId() == listaEventiCal.get(j).getId()) {
					calendarioPersonaleDao.deleteEvent(matricola, listaEventiCal.get(j));
				}
			}
		}
	}

	@Override
	public List<Lezione> findLezioni() {
		Connection connection = this.dataSource.getConnection();
		List<Lezione> lezioni = new ArrayList<>();
		try {
			Lezione lezione;
			PreparedStatement statement;
			String query = "select * " + "from lezione l\r\n" + "where NOT EXISTS(select * \r\n"
					+ "                from evento\r\n" + "                where evento.id=l.id)";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				lezione = new Lezione();
				lezione.setId(result.getInt("id"));
				lezione.setAula(result.getString("aula"));
				lezione.setCorso(result.getLong("corso"));
				GiornoCalendario g= new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data"));
				lezione.setData(g);
				lezione.setTipo(result.getInt("tipo"));
				lezione.setOraInizio(result.getTimestamp("ora_inizio"));
				lezione.setOraFine(result.getTimestamp("ora_fine"));
				lezioni.add(lezione);
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
		return lezioni;
	}

}
