package persistence.dao;

import java.util.List;

import model.course.Corso;
import model.course.DescrizioneCorso;
import model.user.Utente;

public interface DescrizioneCorsoDao {
	public void save(DescrizioneCorso descrizioneCorso);  // Create
	public DescrizioneCorso findByPrimaryKey(Long codice);     // Retrieve
	public List<DescrizioneCorso> findAll();       
	public void update(DescrizioneCorso descrizioneCorso); //Update
	public void delete(DescrizioneCorso descrizioneCorso); //Delete
	public List<DescrizioneCorso> findNotCreatedCourses();
}
