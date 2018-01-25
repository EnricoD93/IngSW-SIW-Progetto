package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user.Evento;
import persistence.dao.EventoDao;

public class EventoDaoJDBC implements EventoDao {
	private DataSource dataSource;

	public EventoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			if (evento.getId() == -1)
				evento.setId(IdBroker.getId(connection));
			String insert = "insert into evento(id,title,inizio,fine,nota) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, evento.getId());
			statement.setString(2, evento.getTitle());
			statement.setTimestamp(3, evento.getInizio());
			statement.setTimestamp(4, evento.getFine());
			statement.setString(5, evento.getNota());

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
	public Evento findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		Evento evento = null;
		try {
			String query = "select * from evento where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				evento = new Evento();
				evento.setId(result.getLong("id"));
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
		return evento;
	}

	@Override
	public List<Evento> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Evento> eventi = new ArrayList<>();
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select * from evento";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = new Evento();
				evento.setId(result.getInt("id"));
				evento.setInizio(result.getTimestamp("inizio"));
				evento.setFine(result.getTimestamp("fine"));
				evento.setTitle(result.getString("title"));
				evento.setNota(result.getString("nota"));
				eventi.add(evento);
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
		return eventi;
	}

	@Override
	public void update(Evento evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM evento WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1,evento.getId());
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
	public List<Evento> findEvent() {

		Connection connection = this.dataSource.getConnection();
		List<Evento> eventi = new ArrayList<>();
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select *\r\n" + "from evento e\r\n" + "where NOT EXISTS(select * \r\n"
					+ "                from lezione\r\n" + "                where lezione.id=e.id)";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = new Evento();
				evento.setId(result.getInt("id"));
				evento.setInizio(result.getTimestamp("inizio"));
				evento.setFine(result.getTimestamp("fine"));
				evento.setTitle(result.getString("title"));
				evento.setNota(result.getString("nota"));
				eventi.add(evento);
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
		return eventi;
	}

}
