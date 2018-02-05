package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user.Notifica;
import persistence.dao.NotificaDao;

public class NotificaDaoJDBC implements NotificaDao {
	DataSource dataSource;

	public NotificaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Notifica notifica) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			notifica.setId(id);
			String insert = "insert into notifica(id,data,testo,type,matricola_dest,letta) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, notifica.getId());
			statement.setTimestamp(2, notifica.getData());
			statement.setString(3, notifica.getTesto());
			statement.setInt(4, notifica.getType());
			statement.setString(5, notifica.getDestinatario());
			statement.setBoolean(6, notifica.isLetta());

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
	public Notifica findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		Notifica notifica = null;
		try {
			String query = "select * from notifica where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				notifica = new Notifica();
				notifica.setData(result.getTimestamp("data"));
				notifica.setTesto(result.getString("testo"));
				notifica.setType(result.getInt("type"));
				notifica.setDestinatario(result.getString("matricola_dest"));
				notifica.parseDate(notifica.getData());
				notifica.setLetta(result.getBoolean("letta"));
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
		return notifica;
	}

	@Override
	public List<Notifica> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Notifica notifica) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Notifica notifica) {
		// TODO Auto-generated method stub

	}

	@Override
	public int findUnreadNotifications(String matricola) {
		Connection connection = this.dataSource.getConnection();
		int count = 0;
		try {
			String query = "select count(*) from notifica where notifica.matricola_dest=? AND notifica.letta='FALSE'";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<Notifica> findAllNotifications(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Notifica> notifiche = new ArrayList<>();
		try {
			String query = "select * from notifica where notifica.matricola_dest=? AND notifica.letta='FALSE' ORDER BY notifica.data DESC";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			Notifica n = null;
			while (result.next()) {
				n = new Notifica();
				n.setData(result.getTimestamp("data"));
				n.setDestinatario(result.getString("matricola_dest"));
				n.setId(result.getLong("id"));
				n.setLetta(result.getBoolean("letta"));
				n.setTesto(result.getString("testo"));
				n.parseDate(n.getData());
				n.setType(result.getInt("type"));
				notifiche.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return notifiche;
	}

	@Override
	public void updateUnreadNotification(long id) {

	}

}
