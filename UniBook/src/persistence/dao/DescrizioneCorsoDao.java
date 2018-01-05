package persistence.dao;

import java.util.List;

import model.Corso;
import model.DescrizioneCorso;
import model.Utente;

public interface DescrizioneCorsoDao {
	public void save(DescrizioneCorso descrizioneCorso);  // Create
	public DescrizioneCorso findByPrimaryKey(Long codice);     // Retrieve
	public List<DescrizioneCorso> findAll();       
	public void update(DescrizioneCorso descrizioneCorso); //Update
	public void delete(DescrizioneCorso descrizioneCorso); //Delete
}
