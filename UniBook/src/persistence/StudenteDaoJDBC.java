package persistence;

import java.util.List;

import model.Studente;
import persistence.dao.StudenteDao;

public class StudenteDaoJDBC implements StudenteDao {
	
	private DataSource dataSource;

	public StudenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Studente studente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Studente findByPrimaryKey(String matricola) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Studente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Studente studente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Studente studente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassword(Studente studente, String password) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public StudenteCredenziali findByPrimaryKeyCredential(String matricola) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
