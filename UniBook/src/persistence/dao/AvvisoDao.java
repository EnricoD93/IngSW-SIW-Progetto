package persistence.dao;

import java.util.List;

import model.course.Avviso;

public interface AvvisoDao {
	public void save(Avviso avviso); // Create

	public Avviso findByPrimaryKey(String id); // Retrieve

	public List<Avviso> findAll();
	public List<Avviso> findAllByCourse(Long codice);
	public void update(Avviso avviso); // Update

	public void delete(Long id);
}
