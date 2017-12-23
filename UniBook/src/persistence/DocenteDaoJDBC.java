package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Docente;
import persistence.dao.DocenteDao;

public class DocenteDaoJDBC implements DocenteDao {
	private DataSource dataSource;

	public DocenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Docente docente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into utente(matricola,nome,cognome,data_nascita,codice_fiscale,email,password,corsodilaurea) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, docente.getMatricola());
			statement.setString(2, docente.getNome());
			statement.setString(3, docente.getCognome());
			long secs = docente.getDataNascita().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			statement.setString(5, docente.getCodicefiscale());
			statement.setString(6, docente.getEmail());
			statement.setString(7, docente.getPassword());
			statement.setLong(8, docente.getCorsoDiLaurea());
			statement.executeUpdate();
			
			
			String insert2="insert into docente(matricola) values (?)";
			PreparedStatement statement2=connection.prepareStatement(insert2);
			statement2.setString(1, docente.getMatricola());
			
			statement2.executeUpdate();
			
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
	public Docente findByPrimaryKey(String matricola) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Docente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Docente docente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Docente docente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, docente.getMatricola());
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
	public void setPassword(Docente studente, String password) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public DocenteCredenziali findByPrimaryKeyCredential(String matricola) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
