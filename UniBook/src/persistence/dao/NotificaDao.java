package persistence.dao;

import java.util.List;

import model.user.Notifica;

public interface NotificaDao {
	public void save(Notifica notifica);  // Create
	public Notifica findByPrimaryKey(Long id);     // Retrieve
	public List<Notifica> findAll();       
	public void update(Notifica notifica); //Update
	public void delete(Notifica notifica); //Delete
	public int findUnreadNotifications(String matricola);
	public List<Notifica> findAllNotifications(String matricola);
	public void updateUnreadNotification(long id);
}
