package persistence.dao;

import java.util.List;

import model.CorsoDiLaurea;
import model.Messaggio;

public interface MessaggioDao {
	public void save(Messaggio messaggio);  // Create
	public Messaggio findByPrimaryKey(Long codice);     // Retrieve
	public List<Messaggio> findAll();       
	public void update(Messaggio messaggio); //Update
	public void delete(Messaggio messaggio); //Delete	
}
