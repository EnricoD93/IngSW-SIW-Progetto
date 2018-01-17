package persistence.dao;

import java.util.List;

import model.course.Aula;

public interface AulaDao {
	public void save(Aula aula);  // Create
	public Aula findByPrimaryKey(String id);     // Retrieve
	public List<Aula> findAll();       
	public void update(Aula aula); //Update
	public void delete(Aula aula); //Delete	
}
