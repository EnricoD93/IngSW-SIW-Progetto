package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.course.Corso;
import model.user.Esame;
import model.user.EsameSuperato;
import model.user.Evento;
import model.user.Messaggio;
import model.user.Utente;
import persistence.dao.LezioneDao;
import persistence.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into utente(matricola,nome,cognome,data_nascita,codice_fiscale,email,password,corsodilaurea,ruolo,verifycode,imagepath,descrizione) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getMatricola());
			statement.setString(2, utente.getNome());
			statement.setString(3, utente.getCognome());
			long secs = utente.getDataNascita().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			statement.setString(5, utente.getCodicefiscale());
			statement.setString(6, utente.getEmail());
			statement.setString(7, utente.getPassword());
			statement.setString(8, utente.getCorsoDiLaurea());
			statement.setInt(9, utente.getRuolo());
			statement.setString(10, utente.getVerifyCode());
			statement.setString(11, utente.getProfileImagePath());
			statement.setString(12, utente.getDescrizione());
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
	public Utente findByPrimaryKey(String matricola) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			String query = "select * from utente where matricola = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
				utente.setDescrizione(result.getString("descrizione"));
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
		return utente;
	}

	@Override
	public List<Utente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti;
		try {
			String query = "select * from utente";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			utenti = new ArrayList<>();
			Utente utente;
			while (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
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
	public void update(Utente utente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, utente.getMatricola());
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
	public List<Corso> getCorsi(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Corso> corsi = new ArrayList<>();
		try {
			Corso corso;
			PreparedStatement statement;
			String query = "select * from corso, corsodilaurea,utente where corso.corsodilaurea=corsodilaurea.codice\r\n"
					+ "AND utente.matricola=?\r\n"
					+ "AND utente.corsodilaurea=corsodilaurea.codice\r\n ORDER BY corso.codice";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				corso = new Corso();
				corso.setCodice(result.getLong("codice"));
				corso.setAnno(result.getInt("anno"));
				corso.setCorsoDiLaurea(result.getString("corsodilaurea"));
				corso.setDescrizione(result.getString("descrizione"));
				corso.setDocente(result.getString("docente"));
				corso.setGiorno(result.getString("giorni"));
				corso.setMateriale(result.getString("materiale"));
				corso.setNome(result.getString("nome"));
				corso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
				corso.setOreLezione(result.getInt("ore_lezioni"));
				corso.setRequisiti(result.getString("requisiti"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
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
	public void setPassword(Utente utente, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Corso> getCorsiIscritto(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Corso> corsi = new ArrayList<>();
		try {
			Corso corso;
			PreparedStatement statement;
			String query = "select * from corso,utente,iscritto where corso.codice=iscritto.codice\r\n"
					+ "AND utente.matricola=?\r\n" + " AND utente.ruolo=0\r\n"
					+ "AND utente.matricola=iscritto.matricola\r\n ORDER BY corso.codice";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				corso = new Corso();
				corso.setCodice(result.getLong("codice"));
				corso.setAnno(result.getInt("anno"));
				corso.setCorsoDiLaurea(result.getString("corsodilaurea"));
				corso.setDescrizione(result.getString("descrizione"));
				corso.setDocente(result.getString("docente"));
				corso.setGiorno(result.getString("giorni"));
				corso.setMateriale(result.getString("materiale"));
				corso.setNome(result.getString("nome"));
				corso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
				corso.setOreLezione(result.getInt("ore_lezioni"));
				corso.setRequisiti(result.getString("requisiti"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
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
	public void iscriviStudente(String matricola, Long codice) {
		System.out.println("iscrivo lo studente " + matricola + " al corso " + codice);
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into iscritto(codice,matricola) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, codice);
			statement.setString(2, matricola);

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
	public List<Corso> getCorsiDocente(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Corso> corsi = new ArrayList<>();
		try {
			Corso corso;
			PreparedStatement statement;
			String query = "select * from corso,utente where utente.ruolo=1\r\n" + "AND utente.matricola=?\r\n"
					+ "AND corso.docente=utente.matricola\r\n ORDER BY corso.codice";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				corso = new Corso();
				corso.setCodice(result.getLong("codice"));
				corso.setAnno(result.getInt("anno"));
				corso.setCorsoDiLaurea(result.getString("corsodilaurea"));
				corso.setDescrizione(result.getString("descrizione"));
				corso.setDocente(result.getString("docente"));
				corso.setGiorno(result.getString("giorni"));
				corso.setMateriale(result.getString("materiale"));
				corso.setNome(result.getString("nome"));
				corso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
				corso.setOreLezione(result.getInt("ore_lezioni"));
				corso.setRequisiti(result.getString("requisiti"));
				corso.setCfu(result.getInt("cfu"));
				corso.setCognomeDocente(result.getString("cognomeDocente"));
				corso.setNomeDocente(result.getString("nomeDocente"));
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
	public void updateImage(Utente utente, String fileName) {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE utente " + "SET imagepath = ? " + "WHERE matricola = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, fileName);
			statement.setString(2, utente.getMatricola());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void eliminaIscrizioneStudente(String matricola, Long codice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM iscritto WHERE matricola = ? AND codice = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, matricola);
			statement.setLong(2, codice);
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
	public Utente findUtenteByEmail(String email) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			String query = "select * from utente where email = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
				utente.setDescrizione(result.getString("descrizione"));

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
		return utente;
	}

	@Override
	public boolean iscritto(String matricola, Long codice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * from iscritto where matricola = ? AND codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.setLong(2, codice);
			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return false;

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
		return true;
	}

	@Override
	public List<Utente> findColleaguesByCorsoDiLaurea(Utente u) {
		Connection connection = this.dataSource.getConnection();
		List<Utente> colleagues;
		try {
			String query = "select * from utente where corsodilaurea = ? AND ruolo = ? AND matricola != ? ORDER BY utente.cognome";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getCorsoDiLaurea());
			statement.setInt(2, u.getRuolo());
			statement.setString(3, u.getMatricola());
			ResultSet result = statement.executeQuery();
			colleagues = new ArrayList<>();
			Utente utente;
			while (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
				utente.setDescrizione(result.getString("descrizione"));
				colleagues.add(utente);
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
		return colleagues;
	}

	@Override
	public List<Utente> findAllProfessor() {
		Connection connection = this.dataSource.getConnection();
		List<Utente> docenti;
		try {
			String query = "select * from utente where ruolo = ? ORDER BY utente.cognome";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, 1);
			ResultSet result = statement.executeQuery();
			docenti = new ArrayList<>();
			Utente utente;
			while (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
				utente.setDescrizione(result.getString("descrizione"));
				docenti.add(utente);
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
		return docenti;
	}

	@Override
	public int findAllUnreadMessages(String dest) {
		Connection connection = this.dataSource.getConnection();
		int count = 0;
		try {
			String query = "select count(*) from messaggio where messaggio.matricola_dest=? AND messaggio.letta='FALSE'";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, dest);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return count;
	}

	@Override
	public List<Messaggio> findAllUnreadMessages(String dest, String mitt) {
		Connection connection = this.dataSource.getConnection();
		List<Messaggio> mess;
		try {
			String query = "select * from messaggio where messaggio.matricola_dest=? AND messaggio.matricola_mitt=? AND messaggio.letta='FALSE'";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(2, dest);
			statement.setString(1, mitt);
			ResultSet result = statement.executeQuery();
			mess = new ArrayList<>();
			Messaggio m;
			while (result.next()) {
				m = new Messaggio();
				m.setData(result.getTimestamp("data"));
				m.setId(result.getLong("id"));
				m.parseDate(m.getData());
				m.setDestinatario(result.getString("matricola_dest"));
				m.setMittente(result.getString("matricola_mitt"));
				m.setLetta(result.getBoolean("letta"));
				m.setTesto(result.getString("testo"));
				mess.add(m);
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
		return mess;
	}

	@Override
	public int findUnreadMessages(String mitt, String dest) {
		Connection connection = this.dataSource.getConnection();
		int count = 0;
		try {
			String query = "select count(*) from messaggio where messaggio.matricola_mitt=? AND messaggio.matricola_dest=? AND messaggio.letta='FALSE'";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, mitt);
			statement.setString(2, dest);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return count;
	}

	@Override
	public List<Utente> findMessageSendersByMatricola(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti;
		try {
			String query = "SELECT DISTINCT utente.* FROM utente,messaggio WHERE (messaggio.matricola_mitt=? OR messaggio.matricola_dest=?) AND (messaggio.matricola_mitt=utente.matricola OR messaggio.matricola_dest=utente.matricola) AND (utente.matricola!=?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.setString(2, matricola);
			statement.setString(3, matricola);
			ResultSet result = statement.executeQuery();
			utenti = new ArrayList<>();
			Utente utente;
			while (result.next()) {
				utente = new Utente();
				utente.setMatricola(result.getString("matricola"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setCodicefiscale(result.getString("codice_fiscale"));
				utente.setCorsoDiLaurea(result.getString("corsodilaurea"));
				utente.setRuolo(result.getInt("ruolo"));
				utente.setVerifyCode(result.getString("verifycode"));
				utente.setProfileImagePath(result.getString("imagepath"));
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
	public void superaEsame(String matricola, Esame ingegneriaSW, Timestamp data, int voto) {
		System.out.println("lo studente " + matricola + " supera l'esame: " + ingegneriaSW.getNome());
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into superato(esame,studente,data,voto) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, ingegneriaSW.getCorso());
			statement.setString(2, matricola);
			statement.setTimestamp(3, data);
			statement.setInt(4, voto);

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
	public List<EsameSuperato> findEsamiSuperati(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<EsameSuperato> esamiSuperati = new ArrayList<>();
		try {
			EsameSuperato esame;
			String query = "select esame.corso,esame.nome,esame.cfu,superato.data,superato.voto from superato,esame,utente where superato.esame=esame.corso AND superato.studente=utente.matricola AND utente.matricola=? ORDER BY esame.nome";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				esame = new EsameSuperato();
				esame.setCorso(result.getLong("corso"));
				esame.setNome(result.getString("nome"));
				esame.setCfu(result.getInt("cfu"));
				esame.setData(result.getTimestamp("data"));
				esame.parseDate(esame.getData());
				esame.setVoto(result.getInt("voto"));
				esamiSuperati.add(esame);
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
		return esamiSuperati;
	}

	@Override
	public List<Esame> findEsamiIscritto(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Esame> esamiSuperati = new ArrayList<>();
		try {
			Esame esame;
			String query = "select * from esame e,iscritto where iscritto.codice=e.corso AND iscritto.matricola=? AND NOT EXISTS(select * from superato where e.corso=superato.esame AND superato.studente=? ORDER BY e.nome)";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.setString(2, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				esame = new Esame();
				esame.setCorso(result.getLong("corso"));
				esame.setNome(result.getString("nome"));
				esame.setCfu(result.getInt("cfu"));
				esamiSuperati.add(esame);
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
		return esamiSuperati;
	}

	@Override
	public List<Esame> findEsamiNonSuperati(String matricola) {
		Connection connection = this.dataSource.getConnection();
		List<Esame> esamiSuperati = new ArrayList<>();
		try {
			Esame esame;
			String query = "select *\r\n" + "from esame e\r\n" + "where EXISTS (select *\r\n"
					+ "from descrizionecorso cor, corsodilaurea cdl, utente u\r\n"
					+ "where e.corso=cor.codice AND cdl.codice=cor.corsodilaurea AND u.matricola=? AND u.corsodilaurea=cdl.codice) AND\r\n"
					+ "NOT EXISTS(select *\r\n" + "          from superato s, utente u\r\n"
					+ "          where s.esame=e.corso AND u.matricola=s.studente AND u.matricola=?) AND NOT EXISTS(select *\r\n"
					+ "          from iscritto i, utente u\r\n"
					+ "          where i.codice=e.corso AND u.matricola=i.matricola AND u.matricola=?) ORDER BY e.nome";
			PreparedStatement statement;

			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.setString(2, matricola);
			statement.setString(3, matricola);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				esame = new Esame();
				esame.setCorso(result.getLong("corso"));
				esame.setNome(result.getString("nome"));
				esame.setCfu(result.getInt("cfu"));
				esamiSuperati.add(esame);
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
		return esamiSuperati;
	}

	@Override
	public void salvaPresenza(String matricola, Long lezione) {
		System.out.println(lezione + " " + matricola);
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into presenza(studente,lezione) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, matricola);
			statement.setLong(2, lezione);

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
	public void deletePresenze(Long lezione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM presenza WHERE lezione = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, lezione);

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
	public void deleteExam(String matricola, Esame exam) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM superato WHERE studente = ? AND esame= ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, matricola);
			statement.setLong(2, exam.getCorso());
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
	public int findPresenze(String matricola, Long corso) {
		int count = 0;
		LezioneDao lezione = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		List<Evento> lezioni = lezione.findCourseLessons(corso);
		for (int i = 0; i < lezioni.size(); i++) {

			Connection connection = this.dataSource.getConnection();
			String query = "select count(*) from presenza where studente=? AND lezione=?";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				statement.setString(1, matricola);
				statement.setLong(2, lezioni.get(i).getId());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					count += result.getInt("count");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}
		}

		return count;
	}

	@Override
	public void deletePresenzeStudente(String matricola, Long corso) {
		LezioneDao lezione = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		List<Evento> lezioni = lezione.findCourseLessons(corso);
		for (int i = 0; i < lezioni.size(); i++) {

			Connection connection = this.dataSource.getConnection();
			String query = "delete * from presenza where studente=? AND lezione=?";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				statement.setString(1, matricola);
				statement.setLong(2, lezioni.get(i).getId());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}
		}

	}

	@Override
	public HashMap<String, Long> findAllPresenze(Long corso) {
		HashMap<String, Long> map = new HashMap<>();
		LezioneDao lezione = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		List<Evento> lezioni = lezione.findCourseLessons(corso);
		for (int i = 0; i < lezioni.size(); i++) {

			Connection connection = this.dataSource.getConnection();
			String query = "select * from presenza where lezione=?";
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement(query);
				statement.setLong(1, lezioni.get(i).getId());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					map.put(result.getString("studente"), result.getLong("lezione"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}
		}

		return map;
	}

	@Override
	public void passwordModify(String matricola, String password) {

		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE utente " + "SET password = ? " + "WHERE matricola = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, password);
			statement.setString(2, matricola);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void emailModify(String matricola, String email) {

		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE utente " + "SET email = ? " + "WHERE matricola = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, matricola);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setVerifyCode(String matricola, String code) {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE utente " + "SET verifycode = ? " + "WHERE matricola = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, code);
			statement.setString(2, matricola);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void descrizioneModify(String matricola, String descrizione) {

		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "UPDATE utente " + "SET descrizione = ? " + "WHERE matricola = ?";
		System.out.println("setto descrizione");
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, descrizione);
			statement.setString(2, matricola);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
