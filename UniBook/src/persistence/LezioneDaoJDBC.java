package persistence;

import java.util.List;

import model.CorsoDiLaurea;
import persistence.dao.LezioneDao;

public class LezioneDaoJDBC implements LezioneDao {
	private DataSource dataSource;

	public LezioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(CorsoDiLaurea corsoDiLaurea) {
		// TODO Auto-generated method stub

	}

	@Override
	public CorsoDiLaurea findByPrimaryKey(Long codice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorsoDiLaurea> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CorsoDiLaurea corsoDiLaurea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CorsoDiLaurea corsoDiLaurea) {
		// TODO Auto-generated method stub

	}

}
