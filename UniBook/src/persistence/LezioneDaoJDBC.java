package persistence;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.GiornoCalendario;
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
			String insert = "insert into lezione(data,ora_inizio,ora_fine,corso,aula,tipo) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
		
			statement.setDate(1, lezione.getData().GiornoCalendarioToDate());
			statement.setDouble(2, lezione.getOraInizio());
			statement.setDouble(3, lezione.getOraFine());
			statement.setLong(4, lezione.getCorso());
			statement.setString(5, lezione.getAula());
			statement.setInt(6, lezione.getTipo());

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
		return null;
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
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM lezione WHERE data = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setDate(1, lezione.getData().GiornoCalendarioToDate());
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
