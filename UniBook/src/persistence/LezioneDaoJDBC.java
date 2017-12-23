package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Aula;
import model.CorsoDiLaurea;
import model.Lezione;
import persistence.dao.LezioneDao;

public class LezioneDaoJDBC implements LezioneDao {
	private DataSource dataSource;

	public LezioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Lezione lezione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into lezione(data,ora_inizio,durata,corso,aula) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			long secs = lezione.getData().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setInt(2, lezione.getOraInizio());
			statement.setInt(3, lezione.getDurata());
			statement.setLong(4, lezione.getCorso());
			statement.setString(5, lezione.getAula());
			

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
	public Lezione findByPrimaryKey(Date data) {
		Connection connection = this.dataSource.getConnection();
		Lezione lezione = null;
		try {
			String query = "select * from lezione where data = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, new java.sql.Date(data.getTime()));
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				lezione = new Lezione();
				lezione.setData(result.getDate("data"));
				lezione.setOraInizio(result.getInt("ore_inizio"));
				lezione.setDurata(result.getInt("durata"));
				lezione.setCorso(result.getLong("corso"));
				lezione.setAula(result.getString("aula"));
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
		return lezione;
	}

	@Override
	public List<Lezione> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Lezione lezione) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Lezione lezione) {
		// TODO Auto-generated method stub

	}

}
