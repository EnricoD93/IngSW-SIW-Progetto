package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Messaggio;
import model.Utente;
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
			String insert = "insert into messaggio(data,ora,testo,matricola_mitt,matricola_dest) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			long secs = messaggio.getData().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setInt(2, messaggio.getOra());
			statement.setString(3, messaggio.getTesto());
			statement.setString(4, messaggio.getMittente());
			statement.setString(5, messaggio.getDestinatario());

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
				messaggio.setData(result.getDate("data"));
				messaggio.setOra(result.getInt("ora"));
				messaggio.setTesto(result.getString("testo"));
				messaggio.setMittente(result.getString("matricola_mitt"));
				messaggio.setDestinatario(result.getString("matricola_dest"));
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
			String query = "SELECT * FROM messaggio WHERE (messaggio.matricola_mitt=? AND messaggio.matricola_dest=?) OR(messaggio.matricola_mitt=? AND messaggio.matricola_dest=?)";
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
				messaggio.setData(result.getDate("data"));
				messaggio.setDestinatario(result.getString("matricola_mitt"));
				messaggio.setMittente(result.getString("matricola_dest"));
				messaggio.setTesto(result.getString("testo"));
				messaggio.setOra(result.getInt("ora"));
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

}
