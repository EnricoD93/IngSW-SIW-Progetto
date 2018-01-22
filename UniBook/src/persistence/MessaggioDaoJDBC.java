package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.user.Messaggio;
import model.user.Utente;
import persistence.dao.MessaggioDao;

public class MessaggioDaoJDBC implements MessaggioDao {
	private DataSource dataSource;

	public MessaggioDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Messaggio messaggio) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			messaggio.setId(id);
			String insert = "insert into messaggio(id,data,testo,matricola_mitt,matricola_dest,letta) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, messaggio.getId());
			statement.setTimestamp(2, messaggio.getData());
			statement.setString(3, messaggio.getTesto());
			statement.setString(4, messaggio.getMittente());
			statement.setString(5, messaggio.getDestinatario());
			statement.setBoolean(6, messaggio.isLetta());

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
	public Messaggio findByPrimaryKey(Date data) {
		Connection connection = this.dataSource.getConnection();
		Messaggio messaggio = null;
		try {
			String query = "select * from messaggio where data = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, data);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				messaggio = new Messaggio();
				messaggio.setData(result.getTimestamp("data"));
				messaggio.setTesto(result.getString("testo"));
				messaggio.setMittente(result.getString("matricola_mitt"));
				messaggio.setDestinatario(result.getString("matricola_dest"));
				messaggio.parseDate(messaggio.getData());
				messaggio.setLetta(result.getBoolean("letta"));
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
		return messaggio;
	}

	@Override
	public List<Messaggio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Messaggio messaggio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Messaggio messaggio) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM messaggio WHERE data = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			long secs = messaggio.getData().getTime();
			statement.setDate(1, new java.sql.Date(secs));
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
	public List<Messaggio> findMessagesByUtenti(String utente1, String utente2) {
		Connection connection = this.dataSource.getConnection();
		List<Messaggio> messaggi;
		try {
			String query = "SELECT * FROM messaggio WHERE (messaggio.matricola_mitt=? AND messaggio.matricola_dest=?) OR(messaggio.matricola_mitt=? AND messaggio.matricola_dest=?) ORDER BY messaggio.data";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente1);
			statement.setString(2, utente2);
			statement.setString(3, utente2);
			statement.setString(4, utente1);

			ResultSet result = statement.executeQuery();
			messaggi = new ArrayList<>();
			Messaggio messaggio;
			while (result.next()) {
				messaggio = new Messaggio();
				messaggio.setId(result.getLong("id"));
				messaggio.setData(result.getTimestamp("data"));
				messaggio.setDestinatario(result.getString("matricola_dest"));
				messaggio.setMittente(result.getString("matricola_mitt"));
				messaggio.setTesto(result.getString("testo"));
				messaggio.parseDate(messaggio.getData());
				messaggio.setLetta(result.getBoolean("letta"));
				messaggi.add(messaggio);
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
		return messaggi;
	}

	@Override
	public void updateUnreadMessages(long id) {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE messaggio " + "SET letta = true " + "WHERE id = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
