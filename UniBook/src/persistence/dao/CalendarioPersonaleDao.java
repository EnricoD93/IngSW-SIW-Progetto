package persistence.dao;

import java.util.List;

import model.CalendarioPersonale;

public interface CalendarioPersonaleDao {
	public void save(CalendarioPersonale calendarioPersonale); // Create

	public CalendarioPersonale findByPrimaryKey(Long codice); // Retrieve

	public List<CalendarioPersonale> findAll();

	public void update(CalendarioPersonale calendarioPersonale); // Update

	public void delete(CalendarioPersonale calendarioPersonale); // Delete
}
