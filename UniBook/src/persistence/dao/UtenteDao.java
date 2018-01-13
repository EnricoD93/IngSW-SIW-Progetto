package persistence.dao;

import java.sql.Timestamp;
import java.util.List;

import model.Corso;
import model.Esame;
import model.EsameSuperato;
import model.Utente;

public interface UtenteDao {
	public void save(Utente utente); // Create

	public Utente findByPrimaryKey(String matricola); // Retrieve

	public List<Utente> findAll();

	public void update(Utente utente); // Update

	public void delete(Utente utente); // Delete

	public List<Corso> getCorsi(String matricola);

	public void setPassword(Utente utente, String password);

	public List<Corso> getCorsiIscritto(String matricola);

	public void iscriviStudente(String matricola, Long codice);

	public List<Corso> getCorsiDocente(String matricola);

	public void updateImage(Utente utente, String fileName);

	public void eliminaIscrizioneStudente(String matricola, Long codice);

	public Utente findUtenteByEmail(String email);

	public boolean iscritto(String matricola, Long codice);

	public List<Utente> findColleaguesByCorsoDiLaurea(Utente utente);

	public List<Utente> findAllProfessor();

	public List<Utente> findMessageSendersByMatricola(String matricola);

	public List<EsameSuperato> findEsamiSuperati(String matricola);

	public void superaEsame(String matricola, Esame ingegneriaSW, Timestamp timestamp, int i);
}
