package persistence.dao;

import java.util.List;

import model.course.CorsoDiLaurea;
import model.user.Evento;

public interface EventoDao {
	public void save(Evento Evento);  // Create
	public Evento findByPrimaryKey(Long id);     // Retrieve
	public List<Evento> findAll();       
	public List<Evento>findEvent();
	public void update(Evento evento); //Update
	public void delete(Evento evento);
}