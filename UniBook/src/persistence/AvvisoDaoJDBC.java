package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

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
			String insert = "insert into avviso(id,titolo,text,corso,data) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, avviso.getId());
			statement.setString(2, avviso.getTitolo());
			statement.setString(3, avviso.getText());
			statement.setLong(4, avviso.getCorso());
			statement.setTimestamp(5, avviso.getData());
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
	public List<Avviso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Avviso avviso) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM avviso WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, id);
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
				avviso.setTitolo(result.getString("titolo"));
				avviso.setData(result.getTimestamp("data"));
				avviso.setText(result.getString("text"));
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

	@Override
	public Avviso findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		Avviso a=null;
		try {
			String query = "select * FROM avviso WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result=statement.executeQuery();
			if (result.next()) {
				a=new Avviso();
				a.setId(result.getLong("id"));
				a.setData(result.getTimestamp("data"));
				a.setText(result.getString("text"));
				a.setTitolo(result.getString("titolo"));
				a.setCorso(result.getLong("corso"));
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
		return a;
	}

}
