package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corso;
import model.Studente;
import persistence.dao.CorsoDao;

public class CorsoDaoJDBC implements CorsoDao {

	private DataSource dataSource;

	public CorsoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Corso corso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into corso(codice,nome,anno,descrizione,requisiti,giorni,ore_lezioni,ore_esercitazioni,materiale,docente,corsodilaurea) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, corso.getCodice());
			statement.setString(2, corso.getNome());
			statement.setInt(3, corso.getAnno());
			statement.setString(4, corso.getDescrizione());
			statement.setString(5, corso.getRequisiti());
			statement.setString(6, corso.getGiorno());
			statement.setInt(7, corso.getOreLezione());
			statement.setInt(8, corso.getOreEsercitazione());
			statement.setString(9, corso.getMateriale());
			statement.setString(10, corso.getDocente());
			statement.setLong(11, corso.getCorsoDiLaurea());
			
			
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
	public Corso findByPrimaryKey(Long codice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Corso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Corso corso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Corso corso) {
		// TODO Auto-generated method stub
		
	}

}
