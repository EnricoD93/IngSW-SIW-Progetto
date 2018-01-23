package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.course.Avviso;
import model.user.Messaggio;
import persistence.dao.AvvisoDao;

public class AvvisoDaoJDBC implements AvvisoDao {
	DataSource dataSource;

	public AvvisoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Avviso avviso) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			avviso.setId(id);
			String insert = "insert into avviso(id,text,corso,data) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, avviso.getId());
			statement.setString(2, avviso.getText());
			statement.setLong(3, avviso.getCorso());
			statement.setTimestamp(4, avviso.getData());
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
	public Avviso findByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Avviso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Avviso avviso) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Avviso avviso) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Avviso> findAllByCourse(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Avviso> avvisi;
		try {
			String query = "SELECT * FROM avviso WHERE avviso.corso=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);

			ResultSet result = statement.executeQuery();
			avvisi = new ArrayList<>();
			Avviso avviso;
			while (result.next()) {
				avviso = new Avviso();
				avviso.setId(result.getLong("id"));
				avviso.setData(result.getTimestamp("data"));
				avviso.setText(result.getString("text"));
				avviso.parseDate(avviso.getData());
				avviso.setCorso(result.getLong("corso"));
				avvisi.add(avviso);
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
		return avvisi;
	}

}
