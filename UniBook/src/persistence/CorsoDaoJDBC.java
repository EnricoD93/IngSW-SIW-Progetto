package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.course.Aula;
import model.course.Corso;
import model.user.Esame;
import model.user.GiornoCalendario;
import model.user.Utente;
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
			String insert = "insert into corso(codice,nome,anno,descrizione,requisiti,giorni,ore_lezioni,ore_esercitazioni,materiale,docente,corsodilaurea,cfu,cognomeDocente,nomeDocente,data_inizio,data_fine) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			statement.setString(11, corso.getCorsoDiLaurea());
			statement.setInt(12, corso.getCfu());
			statement.setString(13, corso.getCognomeDocente());
			statement.setString(14, corso.getNomeDocente());
			statement.setDate(15, corso.getDataInizio().GiornoCalendarioToDate());
			statement.setDate(16, corso.getDataFine().GiornoCalendarioToDate());
			

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
				corso.setCorsoDiLaurea(result.getString("corsodilaurea"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
				GiornoCalendario g=new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data_inizio"));
				corso.setDataInizio(g);
				g=new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data_fine"));
				corso.setDataFine(g);
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
		Connection connection = this.dataSource.getConnection();
		List<Corso> corsi = new ArrayList<>();
		try {
			Corso corso;
			PreparedStatement statement;
			String query = "select * from corso";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
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
				corso.setCorsoDiLaurea(result.getString("corsodilaurea"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
				GiornoCalendario g=new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data_inizio"));
				corso.setDataInizio(g);
				g=new GiornoCalendario();
				g.parseToGiornoCalendario(result.getDate("data_fine"));
				corso.setDataFine(g);
				corsi.add(corso);
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
		return corsi;
	}

	@Override
	public void update(Corso corso) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "UPDATE corso SET descrizione=?,requisiti=?,giorni=?,materiale=?,data_inizio=?,data_fine=? WHERE corso.codice=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, corso.getDescrizione());
			statement.setString(2, corso.getRequisiti());
			statement.setString(3, corso.getGiorno());
			statement.setString(4, corso.getMateriale());
			statement.setDate(5, corso.getDataInizio().GiornoCalendarioToDate());
			statement.setDate(6, corso.getDataFine().GiornoCalendarioToDate());
			statement.setLong(7, corso.getCodice());
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
				d.setCorsoDiLaurea(result.getString("corsodilaurea"));
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

	@Override
	public List<Utente> getStudentiIscritti(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti = new ArrayList<>();
		try {
			Utente utente;
			PreparedStatement statement;
			String query = "select * from utente,iscritto where iscritto.codice = ? AND iscritto.matricola=utente.matricola ORDER BY utente.cognome";
			statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCognome(result.getString("cognome"));
				utente.setNome(result.getString("nome"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setDataNascita(result.getDate("data_nascita"));
				utente.setEmail(result.getString("email"));
				utente.setMatricola(result.getString("matricola"));
				utente.setProfileImagePath(result.getString("imagepath"));
				utente.setPassword(result.getString("password"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setDescrizione(result.getString("descrizione"));

				utenti.add(utente);
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
		return utenti;
	}

	@Override
	public void setPropedeutico(Long esame, Long corso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into propedeutico(esame,corso) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, esame);
			statement.setLong(2, corso);
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
	public List<Esame> getEsamiPropedeutici(Long codice) {
		Connection connection = this.dataSource.getConnection();
		List<Esame> esami = new ArrayList<>();
		try {
			Esame esame;
			String query = "select * from esame,propedeutico where esame.corso=propedeutico.esame AND propedeutico.corso=?";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				esame = new Esame();
				esame.setCorso(result.getLong("corso"));
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
	public void deletePropedeutico(Long corso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "delete FROM propedeutico WHERE corso=?";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso);
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