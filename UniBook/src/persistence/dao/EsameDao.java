package persistence.dao;

import java.util.List;

import model.course.CorsoDiLaurea;
import model.user.Esame;

public interface EsameDao {
	public void save(Esame esame); // Create

	public Esame findByPrimaryKey(Long codice); // Retrieve

	public List<Esame> findAll();

	public void update(Esame esame); // Update

	public void delete(Esame esame); // Delete
}
