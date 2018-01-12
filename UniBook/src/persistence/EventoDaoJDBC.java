package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.CorsoDiLaurea;
import model.Evento;
import persistence.dao.EventoDao;

public class EventoDaoJDBC implements EventoDao{
	private DataSource dataSource;

	public EventoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Evento evento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into evento(title,inizio,fine,nota) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, evento.getTitle());
			statement.setTimestamp(2, evento.getInizio());
			statement.setTimestamp(3, evento.getFine());
			statement.setString(4, evento.getNota());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public CorsoDiLaurea findByPrimaryKey(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Evento evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Evento evento) {
		// TODO Auto-generated method stub
		
	}

}
