package persistence.dao;

import java.util.List;

import model.Corso;
import model.Studente;

public interface StudenteDao {

	public void save(Studente studente);  // Create
	public Studente findByPrimaryKey(String matricola);     // Retrieve
	public List<Studente> findAll();       
	public void update(Studente studente); //Update
	public void delete(Studente studente); //Delete	
	public List<Corso> getCorsi(String matricola);
	public void setPassword(Studente studente, String password);
	//public StudenteCredenziali findByPrimaryKeyCredential(String matricola);     // Retrieve
}
