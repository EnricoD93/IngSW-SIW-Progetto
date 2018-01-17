package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user.Esame;
import persistence.dao.EsameDao;

public class EsameDaoJDBC implements EsameDao {

	private DataSource dataSource;

	public EsameDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Esame esame) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into esame(corso,nome,cfu) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, esame.getCorso());
			statement.setString(2, esame.getNome());
			statement.setInt(3, esame.getCfu());

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
	public Esame findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		Esame esame = null;
		try {
			String query = "select * from esame where corso = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				esame = new Esame();
				esame.setCorso(result.getLong("codice"));
				esame.setNome(result.getString("nome"));
				esame.setCfu(result.getInt("cfu"));
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
		return esame;
	}

	@Override
	public List<Esame> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Esame> esami = new ArrayList<>();
		try {
			Esame esame;
			PreparedStatement statement;
			String query = "select * from esame";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				esame = new Esame();
				esame.setCorso(result.getLong("codice"));
				esame.setNome(result.getString("nome"));
				esame.setCfu(result.getInt("cfu"));

				esami.add(esame);
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
		return esami;
	}

	@Override
	public void update(Esame esame) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Esame esame) {

		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM esame WHERE corso = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, esame.getCorso());
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

}
