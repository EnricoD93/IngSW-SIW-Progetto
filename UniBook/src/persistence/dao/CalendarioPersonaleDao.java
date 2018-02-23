package persistence.dao;

import java.util.List;

import model.user.CalendarioPersonale;
import model.user.Evento;

public interface CalendarioPersonaleDao {
	public void save(CalendarioPersonale calendarioPersonale); // Create

	public CalendarioPersonale findByPrimaryKey(String utente); // Retrieve

	public List<CalendarioPersonale> findAll();

	public void update(CalendarioPersonale calendarioPersonale); // Update

	public void delete(CalendarioPersonale calendarioPersonale); // Delete

	public List<Evento> findAllEventsUtente(String matricola);

	public  int findNumberEvents(String matricola);

	public void saveEvent(String matricola, Evento evento);

	public void deleteEvent(String matricola, Evento evento);

	public int findNumberLessons(String matricola);
}
