package persistence.dao;

import java.sql.Date;
import java.util.List;


import model.CorsoDiLaurea;
import model.Lezione;

public interface LezioneDao {
	public void save(Lezione lezione);  // Create
	public Lezione findByPrimaryKey(Date data);     // Retrieve
	public List<Lezione> findAll();       
	public void update(Lezione lezione); //Update
	public void delete(Lezione lezione); //Delete	
}
