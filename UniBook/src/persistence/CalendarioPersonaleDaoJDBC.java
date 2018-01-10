package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.CalendarioPersonale;
import persistence.dao.CalendarioPersonaleDao;

public class CalendarioPersonaleDaoJDBC implements CalendarioPersonaleDao {
	private DataSource dataSource;

	public CalendarioPersonaleDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CalendarioPersonale calendarioPersonale) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into calendariopersonale(matricola) values (?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, calendarioPersonale.getMatricola());
			
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
	public CalendarioPersonale findByPrimaryKey(String matricola) {
		Connection connection = this.dataSource.getConnection();
		CalendarioPersonale calendarioPersonale = null;
		try {
			String query = "select * from calendariopersonale where matricola = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				calendarioPersonale= new CalendarioPersonale();
				calendarioPersonale.setMatricola(result.getString("matricola"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return calendarioPersonale;
	}

	@Override
	public List<CalendarioPersonale> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CalendarioPersonale calendarioPersonale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CalendarioPersonale calendarioPersonale) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM calendariopersonale WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, calendarioPersonale.getMatricola());
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

}
