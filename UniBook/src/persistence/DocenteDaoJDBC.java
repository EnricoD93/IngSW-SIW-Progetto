package persistence;

import java.util.List;

import model.Studente;
import persistence.dao.DocenteDao;

public class DocenteDaoJDBC implements DocenteDao {
	private DataSource dataSource;

	public DocenteDaoJDBC(DataSource dataSource) {
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
//	public DocenteCredenziali findByPrimaryKeyCredential(String matricola) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
