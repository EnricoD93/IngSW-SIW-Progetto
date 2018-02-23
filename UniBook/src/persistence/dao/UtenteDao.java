package persistence.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.course.Corso;
import model.user.Esame;
import model.user.EsameSuperato;
import model.user.Messaggio;
import model.user.Utente;

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

	public List<Esame> findEsamiIscritto(String matricola);

	public List<Esame> findEsamiNonSuperati(String matricola);

	public void salvaPresenza(String matricola, Long lezione);

	public void deletePresenze(Long lezione);

	public int findPresenze(String matricola, Long corso);

	public void deletePresenzeStudente(String matricola, Long corso);

	public HashMap<String, Long> findAllPresenze(Long corso);

	public int findUnreadMessages(String dest, String mitt);

	public int findAllUnreadMessages(String utente);

	public List<Messaggio> findAllUnreadMessages(String utente, String dest);

	public void deleteExam(String matricola, Esame e);

	public void passwordModify(String matricola, String password);

	public void emailModify(String matricola, String email);

	public void setVerifyCode(String matricola, String code);

	public void descrizioneModify(String matricola, String descrizione);
}
