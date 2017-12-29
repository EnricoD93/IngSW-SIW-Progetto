package persistence.dao;

import java.util.List;

import model.Corso;
import model.Docente;
import model.Utente;

public interface CorsoDao {
	public void save(Corso corso);  // Create
	public Corso findByPrimaryKey(Long codice);     // Retrieve
	public List<Corso> findAll();       
	public void update(Corso corso); //Update
	public void delete(Corso corso); //Delete
	public Utente getDocente(String matricola);
}
