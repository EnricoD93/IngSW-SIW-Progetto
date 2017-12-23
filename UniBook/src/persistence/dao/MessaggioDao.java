package persistence.dao;

import java.sql.Date;
import java.util.List;

import model.Messaggio;

public interface MessaggioDao {
	public void save(Messaggio messaggio);  // Create
	public Messaggio findByPrimaryKey(Date data);     // Retrieve
	public List<Messaggio> findAll();       
	public void update(Messaggio messaggio); //Update
	public void delete(Messaggio messaggio); //Delete	
}
