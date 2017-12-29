package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aula;
import model.Corso;
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
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, studente.getMatricola());
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
	public void setPassword(Studente studente, String password) {
		// TODO Auto-generated method stub

	}
@Override
public List<Corso> getCorsi(String matricola) {
	Connection connection = this.dataSource.getConnection();
	List<Corso> corsi = new ArrayList<>();
	try {
		Corso corso;
		PreparedStatement statement;
		String query = "select * from corso, corsodilaurea,utente,studente where corso.corsodilaurea=corsodilaurea.codice\r\n" + 
				"AND utente.matricola=studente.matricola\r\n" + 
				"AND utente.corsodilaurea=corsodilaurea.codice\r\n"+
				"AND studente.matricola=?";
		statement = connection.prepareStatement(query);
		statement.setString(1, matricola);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			corso = new Corso();
			corso.setCodice(result.getLong("codice"));			
			corso.setAnno(result.getInt("anno"));		
			corso.setCorsoDiLaurea(result.getLong("corsodilaurea"));
			corso.setDescrizione(result.getString("descrizione"));
			corso.setDocente(result.getString("docente"));
			corso.setGiorno(result.getString("giorni"));
			corso.setMateriale(result.getString("materiale"));
			corso.setNome(result.getString("nome"));
			corso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
			corso.setOreLezione(result.getInt("ore_lezioni"));
			corso.setRequisiti(result.getString("requisiti"));
			corsi.add(corso);
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
	return corsi;
}
	// @Override
	// public StudenteCredenziali findByPrimaryKeyCredential(String matricola) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
