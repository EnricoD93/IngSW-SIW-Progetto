package persistence.dao;

import java.util.List;

import model.CalendarioPersonale;
import model.Evento;

public interface CalendarioPersonaleDao {
	public void save(CalendarioPersonale calendarioPersonale); // Create

	public CalendarioPersonale findByPrimaryKey(String utente); // Retrieve

	public List<CalendarioPersonale> findAll();

	public void update(CalendarioPersonale calendarioPersonale); // Update

	public void delete(CalendarioPersonale calendarioPersonale); // Delete

	public List<Evento> findAllEventsUtente(String matricola);
	public void saveEvent(String matricola,Evento evento);

}
