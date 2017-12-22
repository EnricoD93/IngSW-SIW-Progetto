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
		// TODO Auto-generated method stub
		
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
