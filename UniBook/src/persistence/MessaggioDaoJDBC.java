package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.CorsoDiLaurea;
import model.Messaggio;
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
	public Messaggio findByPrimaryKey(Long codice) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

}
