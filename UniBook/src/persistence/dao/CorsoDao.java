package persistence.dao;

import java.util.List;

import model.course.Corso;
import model.user.Esame;
import model.user.Utente;

public interface CorsoDao {
	public void save(Corso corso);  // Create
	public Corso findByPrimaryKey(Long codice);     // Retrieve
	public List<Corso> findAll();       
	public void update(Corso corso); //Update
	public void delete(Corso corso); //Delete
	public Utente getDocente(String matricola);
	public List<Utente> getStudentiIscritti(Long codice);
	public void setPropedeutico(Long esame,Long corso);
	public void deletePropedeutico(Long corso);
	public List<Esame> getEsamiPropedeutici(Long corso);
	public void deleteAvvisi(Long codice);
}
