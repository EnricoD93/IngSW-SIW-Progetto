package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			String insert = "insert into aula(id,posti,codice) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, aula.getId());
			statement.setInt(2, aula.getPosti());
			statement.setLong(3, aula.getCorsoDiLaurea());

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
	public Aula findByPrimaryKey(Long codice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aula> findAll() {
		// TODO Auto-generated method stub
		return null;
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
