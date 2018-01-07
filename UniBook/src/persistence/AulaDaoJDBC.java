package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aula;
import persistence.dao.AulaDao;

public class AulaDaoJDBC implements AulaDao {
	private DataSource dataSource;

	public AulaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Aula aula) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into aula(id,posti,codice,ubicazione) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, aula.getId());
			statement.setInt(2, aula.getPosti());
			statement.setString(3, aula.getCorsoDiLaurea());
			statement.setString(4, aula.getUbicazione());
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
	public Aula findByPrimaryKey(String id) {
		Connection connection = this.dataSource.getConnection();
		Aula aula = null;
		try {
			String query = "select * from aula where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				aula = new Aula();
				aula.setId(result.getString("id"));
				aula.setPosti(result.getInt("posti"));
				aula.setCorsoDiLaurea(result.getString("codice"));
				aula.setUbicazione(result.getString("ubicazione"));
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
		return aula;
	}

	@Override
	public List<Aula> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Aula> aule = new ArrayList<>();
		try {
			Aula aula;
			PreparedStatement statement;
			String query = "select * from aula";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				aula = new Aula();
				aula.setId(result.getString("id"));				
				aula.setCorsoDiLaurea(result.getString("codice"));
				aula.setPosti(result.getInt("posti"));
				aula.setUbicazione(result.getString("ubicazione"));
				aule.add(aula);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return aule;
	}

	@Override
	public void update(Aula aula) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Aula aula) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM aula WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, aula.getId());
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
