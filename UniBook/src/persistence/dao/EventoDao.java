package persistence.dao;

import java.util.List;

import model.CorsoDiLaurea;
import model.Evento;

public interface EventoDao {
	public void save(Evento Evento);  // Create
	public CorsoDiLaurea findByPrimaryKey(String title);     // Retrieve
	public List<Evento> findAll();       
	public void update(Evento evento); //Update
	public void delete(Evento evento);
}