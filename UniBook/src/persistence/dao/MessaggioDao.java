package persistence.dao;

import java.sql.Date;
import java.util.List;

import model.user.Messaggio;

public interface MessaggioDao {
	public void save(Messaggio messaggio);  // Create
	public Messaggio findByPrimaryKey(Date data);     // Retrieve
	public List<Messaggio> findAll();       
	public void update(Messaggio messaggio); //Update
	public void delete(Messaggio messaggio); //Delete
	public List<Messaggio> findMessagesByUtenti(String utente1,String utente2);
	public void updateUnreadMessages(long id);
}
