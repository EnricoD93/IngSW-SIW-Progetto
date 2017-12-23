package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		Connection connection = this.dataSource.getConnection();
		Docente docente = null;
		try {
			String query = "select * from utente,docente where utente.matricola=docente.matricola and docente.matricola = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				docente = new Docente();
				docente.setMatricola(result.getString("matricola"));				
				docente.setNome(result.getString("nome"));
				docente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				docente.setDataNascita(new java.util.Date(secs));
				docente.setEmail(result.getString("email"));
				docente.setPassword(result.getString("password"));
				docente.setCodicefiscale(result.getString("codice_fiscale"));
				docente.setCorsoDiLaurea(result.getLong("corsodilaurea"));
				
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
		return docente;
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
