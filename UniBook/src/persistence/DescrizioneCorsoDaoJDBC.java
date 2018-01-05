package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Corso;
import model.DescrizioneCorso;
import persistence.dao.DescrizioneCorsoDao;

public class DescrizioneCorsoDaoJDBC implements DescrizioneCorsoDao{
	private DataSource dataSource;

	public DescrizioneCorsoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(DescrizioneCorso descrizioneCorso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into descrizioneCorso(codice,nome,anno,corsodilaurea,ore_lezioni,ore_esercitazioni,cfu) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, descrizioneCorso.getCodice());
			statement.setString(2, descrizioneCorso.getNome());
			statement.setInt(3, descrizioneCorso.getAnno());
			statement.setLong(4, descrizioneCorso.getCorsoDiLaurea());
			statement.setInt(5, descrizioneCorso.getOreLezione());
			statement.setInt(6, descrizioneCorso.getOreEsercitazione());
			statement.setInt(7, descrizioneCorso.getCfu());

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
	public DescrizioneCorso findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		DescrizioneCorso descrizioneCorso = null;
		try {
			String query = "select * from descrizioneCorso where codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				descrizioneCorso = new DescrizioneCorso();
				descrizioneCorso.setCodice(result.getLong("codice"));
				descrizioneCorso.setNome(result.getString("nome"));
				descrizioneCorso.setAnno(result.getInt("anno"));
				descrizioneCorso.setOreLezione(result.getInt("ore_lezioni"));
				descrizioneCorso.setOreEsercitazione(result.getInt("ore_esercitazioni"));
				descrizioneCorso.setCorsoDiLaurea(result.getLong("corsodilaurea"));
				descrizioneCorso.setCfu(result.getInt("cfu"));
				
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
		return descrizioneCorso;
	}

	@Override
	public List<DescrizioneCorso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DescrizioneCorso descrizioneCorso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DescrizioneCorso descrizioneCorso) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM descrizioneCorso WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, descrizioneCorso.getCodice());
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