package persistence.dao;

import java.util.List;

import model.Corso;
import model.Utente;

public interface UtenteDao {
	public void save(Utente utente);  // Create
	public Utente findByPrimaryKey(String matricola);     // Retrieve
	public List<Utente> findAll();       
	public void update(Utente utente); //Update
	public void delete(Utente utente); //Delete	
	public List<Corso> getCorsi(String matricola);
	public void setPassword(Utente utente, String password);
}