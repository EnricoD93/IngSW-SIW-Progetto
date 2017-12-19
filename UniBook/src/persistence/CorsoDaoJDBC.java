package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.Studente;
import persistence.dao.CorsoDao;

public class CorsoDaoJDBC implements CorsoDao {

	private DataSource dataSource;

	public CorsoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Corso corso) {
		if ((corso.getStudenti() == null) || corso.getStudenti().isEmpty()) {
			throw new PersistenceException("Corso non memorizzato: un corso deve avere almeno uno studente"); // vedere
																												// comportamento
																												// in
																												// base
																												// casi
																												// d'uso
		}
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			corso.setCodice(id);
			String insert = "insert into corso(codice, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getCodice());
			statement.setString(2, corso.getNome());

			// connection.setAutoCommit(false);
			// serve in caso gli studenti non siano stati salvati. Il DAO studente apre e
			// chiude una transazione nuova.
			// connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			// salviamo anche tutti gli studenti del gruppo in CASACATA

			/*
			 * this.updateStudenti(corso, connection);
			 * 
			 * VEDERE SE QUESTO CI SERVE
			 * 
			 * 
			 */

			// connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
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
	public Corso findByPrimaryKey(Long id) {
		Connection connection = this.dataSource.getConnection();
		Corso corso = null;
		try {
			PreparedStatement statement;
			String query = "select * from corso where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				corso = new Corso();
				corso.setCodice(result.getLong("codice"));
				corso.setNome(result.getString("nome"));
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

	public Corso findByPrimaryKeyJoin(Long id) {
		Connection connection = this.dataSource.getConnection();
		Corso corso = null;
		try {
			PreparedStatement statement;
			String query = "select c.codice as c_codice, c.nome as c_nome, s.matricola as s_matricola, s.nome as s_nome, "
					+ "s.cognome as s_cognome, s.data_nascita as s_data_nascita, s.indirizzo_codice as s_indirizzo_codice "
					+ "from corso c, iscritto i, studente s " + "where c.codice = ?"
					+ "			AND i.matricola_studente = s.matricola " + "			AND i.corso_codice = c.codice";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			boolean primaRiga = true;
			while (result.next()) {
				if (primaRiga) {
					corso = new Corso();
					corso.setCodice(result.getLong("c_codice"));
					corso.setNome(result.getString("c_nome"));
					primaRiga = false;
				}
				if (result.getString("s_matricola") != null) {
					Studente studente = new Studente();
					studente.setMatricola(result.getString("s_matricola"));
					studente.setNome(result.getString("s_nome"));
					studente.setCognome(result.getString("s_cognome"));
					long secs = result.getDate("s_data_nascita").getTime();
					studente.setDataNascita(new java.util.Date(secs));
					/*
					 * 
					 * 
					 * AGGIUNGERE DATI MANCANTI
					 * 
					 * 
					 */
					corso.addStudente(studente);
				}
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
				corso = findByPrimaryKeyJoin(result.getLong("codice"));
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
			String update = "update corso SET nome = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, corso.getNome());
			statement.setLong(2, corso.getCodice());

			// connection.setAutoCommit(false);
			// connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			/*
			 * this.updateStudenti(corso, connection);// se abbiamo deciso di propagare gli
			 * update seguendo il riferimento
			 * 
			 * VEDERE SE QUESTO CI SERVE
			 * 
			 * 
			 */
			// connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
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
	public void delete(Corso corso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM corso WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, corso.getCodice());

			/*
			 * rimuoviamo gli studenti dal gruppo (ma non dal database) potevano esserci
			 * soluzioni diverse (ad esempio rimuovere tutti gli studenti dal database (ma
			 * in questo caso non avrebbe senso) La scelta dipende dalla semantica delle
			 * operazioni di dominio
			 * 
			 */
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			/*
			 * this.removeForeignKeyFromStudente(corso, connection);//Vedere se questo ci serve
			 *
			 *
			 * ora rimuoviamo il gruppo
			 * 
			 */
			statement.executeUpdate();
			connection.commit();
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
