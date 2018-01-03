package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Corso;
import model.Utente;
import persistence.dao.CorsoDao;

public class CorsoDaoJDBC implements CorsoDao {

	private DataSource dataSource;

	public CorsoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Corso corso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into corso(codice,nome,anno,descrizione,requisiti,giorni,ore_lezioni,ore_esercitazioni,materiale,docente,corsodilaurea,cfu,cognomeDocente,nomeDocente) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getCodice());
			statement.setString(2, corso.getNome());
			statement.setInt(3, corso.getAnno());
			statement.setString(4, corso.getDescrizione());
			statement.setString(5, corso.getRequisiti());
			statement.setString(6, corso.getGiorno());
			statement.setInt(7, corso.getOreLezione());
			statement.setInt(8, corso.getOreEsercitazione());
			statement.setString(9, corso.getMateriale());
			statement.setString(10, corso.getDocente());
			statement.setLong(11, corso.getCorsoDiLaurea());
			statement.setInt(12, corso.getCfu());
			statement.setString(13, corso.getCognomeDocente());
			statement.setString(14, corso.getNomeDocente());

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
	public Corso findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		Corso corso = null;
		try {
			String query = "select * from corso where codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				corso = new Corso();
				corso.setCodice(result.getLong("codice"));
				corso.setNome(result.getString("nome"));
				corso.setAnno(result.getInt("anno"));
				corso.setDescrizione(result.getString("descrizione"));
				corso.setRequisiti(result.getString("requisiti"));
				corso.setGiorno(result.getString("giorni"));
				corso.setOreLezione(result.getInt("ore_lezioni"));
				corso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
				corso.setMateriale(result.getString("materiale"));
				corso.setDocente(result.getString("docente"));
				corso.setCorsoDiLaurea(result.getLong("corsodilaurea"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
				
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
		return corso;
	}

	@Override
	public List<Corso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Corso corso) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Corso corso) {

		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM corso WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, corso.getCodice());
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
	public Utente getDocente(String matricola) {
		Connection connection = this.dataSource.getConnection();
		Utente d = null;
		try {
			String query = "select * from utente,corso where corso.docente=utente.matricola AND utente.matricola=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				d = new Utente();
				d.setCodicefiscale(result.getString("codice_fiscale"));
				d.setNome(result.getString("nome"));
				d.setMatricola(result.getString("matricola"));
				d.setCognome(result.getString("cognome"));
				d.setDataNascita(result.getDate("data_nascita"));
				d.setEmail(result.getString("email"));
				d.setPassword(result.getString("password"));
				d.setCorsoDiLaurea(result.getLong("corsodilaurea"));
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

		return d;
	}

}
