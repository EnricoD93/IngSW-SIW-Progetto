package persistence.dao;

import java.util.Date;
import java.util.List;

import model.Evento;
import model.Lezione;

public interface LezioneDao {
	public void save(Lezione lezione); // Create

	public Lezione findByPrimaryKey(long id); // Retrieve

	public List<Lezione> findAll();

	public void update(Lezione lezione); // Update

	public void delete(Lezione lezione); // Delete

	public List<Evento> findCourseLessons(Long codice);

	public String findLessonName(Long codice);

	public void deleteLessonByEvent(Evento evento);

	public void eliminaLezioniDalCalendario(List<Evento> listaEventiCal, List<Evento> listaLezioni,
			CalendarioPersonaleDao calendarioPersonaleDao, String matricola);
}
