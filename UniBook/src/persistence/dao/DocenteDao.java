package persistence.dao;

import java.util.List;

import model.Docente;
import model.Studente;

public interface DocenteDao {
	public void save(Docente studente);  // Create
	public Docente findByPrimaryKey(String matricola);     // Retrieve
	public List<Docente> findAll();       
	public void update(Docente studente); //Update
	public void delete(Docente studente); //Delete	
	public void setPassword(Docente docente, String password);
	//public DocenteCredenziali findByPrimaryKeyCredential(String matricola);     // Retrieve
}
