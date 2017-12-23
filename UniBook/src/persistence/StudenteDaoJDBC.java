package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Studente;
import persistence.dao.StudenteDao;

public class StudenteDaoJDBC implements StudenteDao {

	private DataSource dataSource;

	public StudenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Studente studente) {
		Connection connection=this.dataSource.getConnection();
		try {
		String insert="insert into utente(matricola,nome,cognome,data_nascita,codice_fiscale,email,password,corsodilaurea) values (?,?,?,?,?,?,?,?)";
		PreparedStatement statement =connection.prepareStatement(insert);
		statement.setString(1, studente.getMatricola());
		statement.setString(2,studente.getNome());
		statement.setString(3,studente.getCognome());
		long secs=studente.getDataNascita().getTime();
		statement.setDate(4, new java.sql.Date(secs));
		statement.setString(5,studente.getCodicefiscale());
		statement.setString(6,studente.getEmail());
		statement.setString(7,studente.getPassword());
		statement.setLong(8, studente.getCorsoDiLaurea());
		statement.executeUpdate();

		
		String insert2="insert into studente(matricola) values (?)";
		PreparedStatement statement2=connection.prepareStatement(insert2);
		statement2.setString(1, studente.getMatricola());
		
		statement2.executeUpdate();
	}catch(SQLException e) {
		throw new PersistenceException(e.getMessage());
	}finally {
		try {
			connection.close();
		}catch(SQLException e){
			throw new PersistenceException(e.getMessage());
		}
	}
	}

	@Override
	public Studente findByPrimaryKey(String matricola) {
		Connection connection = this.dataSource.getConnection();
		Studente studente = null;
		try {
			String query = "select * from utente,studente where utente.matricola=studente.matricola and studente.matricola = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				studente.setDataNascita(new java.util.Date(secs));
				studente.setEmail(result.getString("email"));
				studente.setPassword(result.getString("password"));
				studente.setCodicefiscale(result.getString("codice_fiscale"));
				studente.setCorsoDiLaurea(result.getLong("corsodilaurea"));
				
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
		return studente;
	}

	@Override
	public List<Studente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Studente studente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Studente studente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassword(Studente studente, String password) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public StudenteCredenziali findByPrimaryKeyCredential(String matricola) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
