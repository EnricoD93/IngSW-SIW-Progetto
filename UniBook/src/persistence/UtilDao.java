package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {

	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void dropDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop table if exists esame;" + "drop table if exists messaggio;"
					+ "drop table if exists calendariopersonale;" + "drop table if exists lezione;"
					+ "drop table if exists aula;" + "drop table if exists iscritto;" + "drop table if exists corso;"
					+ "drop table if exists studente;" + "drop table if exists docente;"
					+ "drop table if exists utente;" + "drop table if exists corsodilaurea;";

			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed drop database");

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

	public void createDatabase() {

		Connection connection = dataSource.getConnection();
		try {

			String create = "create table corsodilaurea(\"codice\" int primary key,nome VARCHAR(255));"
					+ "create table utente(matricola VARCHAR(255) primary key,nome VARCHAR(255),cognome varchar(255),email VARCHAR(255),password VARCHAR(255),data_nascita DATE,codice_fiscale VARCHAR(16),corsodilaurea int REFERENCES corsodilaurea(\"codice\"),ruolo int,verifycode VARCHAR(255));"
					+ "create table corso (\"codice\" bigint primary key, nome VARCHAR(255),anno int,descrizione VARCHAR(255),requisiti VARCHAR(255),giorni VARCHAR(255),ore_lezioni int,ore_esercitazioni int,materiale VARCHAR(255),\"docente\" VARCHAR(255) REFERENCES utente(\"matricola\"),corsodilaurea bigint REFERENCES corsodilaurea(\"codice\"),cfu int,cognomeDocente VARCHAR(255), nomeDocente VARCHAR(255));"
					+ "create table aula(\"id\" VARCHAR(255) primary key,posti int,\"codice\" bigint REFERENCES corsodilaurea(\"codice\"));"
					+ "create table messaggio(data DATE primary key,ora int,testo VARCHAR(255),matricola_mitt VARCHAR(255) REFERENCES utente(\"matricola\"),matricola_dest VARCHAR(255) REFERENCES utente(\"matricola\"));"
					+ "create table lezione(data DATE primary key, ora_inizio int, durata int,\"corso\" bigint REFERENCES corso(\"codice\"),\"aula\" VARCHAR(255) REFERENCES aula(\"id\"));"
					+ "create table calendariopersonale(matricola VARCHAR(255) primary key REFERENCES utente(\"matricola\"));"
					+ "create table esame(\"codice\" bigint primary key REFERENCES corso(\"codice\"));"
					+ "create table iscritto(\"codice\" bigint primary key REFERENCES corso(\"codice\"), matricola VARCHAR(255) REFERENCES utente(\"matricola\"));"

			;

			//
			// + "create table afferisce(\"id\" bigint primary key, corso_codice bigint
			// REFERENCES corso(\"codice\"), corsodilaurea_codice bigint REFERENCES
			// corsodilaurea(\"codice\"));"
			// + "nome VARCHAR(255),cognome VARCHAR(255),"
			// + "data_nascita DATE, gruppo_id bigint REFERENCES gruppo(\"id\"),
			// indirizzo_codice bigint REFERENCES indirizzo(\"codice\"), password
			// VARCHAR(255));"
			// + "create table iscritto(\"id\" bigint primary key, matricola_studente
			// CHARACTER(8) REFERENCES studente(\"matricola\"), corso_codice bigint
			// REFERENCES corso(\"codice\"));";

			PreparedStatement statement = connection.prepareStatement(create);

			statement.executeUpdate();
			System.out.println("Executed create database");

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

	public void resetDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM studente";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM gruppo";
			statement = connection.prepareStatement(delete);

			delete = "delete FROM corso";
			statement = connection.prepareStatement(delete);

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
