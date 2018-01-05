package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.CorsoDiLaurea;
import persistence.dao.CorsoDiLaureaDao;

public class CorsoDiLaureaDaoJDBC implements CorsoDiLaureaDao {

	private DataSource dataSource;

	public CorsoDiLaureaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CorsoDiLaurea corsoDiLaurea) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into corsodilaurea(codice, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corsoDiLaurea.getCodice());
			statement.setString(2, corsoDiLaurea.getNome());

			// connection.setAutoCommit(false);
			// serve in caso gli studenti non siano stati salvati. Il DAO studente apre e
			// chiude una transazione nuova.
			// connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();

			
			insert= "insert into descrizioneCorso(codice, nome,anno,corsodilaurea, cfu,ore_lezioni, ore_esercitazioni) values (?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(insert);
			statement.setLong(1,new Long(211));
			statement.setString(2, corsoDiLaurea.getNome());
			
			// this.updateCorsi(corsoDiLaurea, connection);
			// connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new PersistenceException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public CorsoDiLaurea findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		CorsoDiLaurea corsoDiLaurea = null;
		try {
			String query = "select * from corsodilaurea where codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				corsoDiLaurea= new CorsoDiLaurea();
				corsoDiLaurea.setCodice(result.getLong("codice"));
				corsoDiLaurea.setNome(result.getString("nome"));
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
		return corsoDiLaurea;
	}

	@Override
	public List<CorsoDiLaurea> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CorsoDiLaurea corsoDiLaurea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CorsoDiLaurea corsoDiLaurea) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM corsodilaurea WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, corsoDiLaurea.getCodice());
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
