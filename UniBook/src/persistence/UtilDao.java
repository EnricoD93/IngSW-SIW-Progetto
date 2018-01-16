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
			String delete = "drop SEQUENCE if exists sequenza_id;" + "drop table if exists messaggio;"+"drop table if exists presenza;"
					+ "drop table if exists lezione;" + "drop table if exists aula;" + "drop table if exists iscritto;"
					+ "drop table if exists studente;" + "drop table if exists docente;"
					+ "drop table if exists contiene;" + "drop table if exists evento;"
					+ "drop table if exists calendariopersonale;" + "drop table if exists superato;"
					+ "drop table if exists esame;" + "drop table if exists descrizionecorso;"
					+ "drop table if exists corso;" + "drop table if exists utente;"
					+ "drop table if exists corsodilaurea;";

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

			String create = "create SEQUENCE sequenza_id;"
					+ "create table corsodilaurea(\"codice\" VARCHAR(255) primary key,nome VARCHAR(255));"
					+ "create table utente(matricola VARCHAR(255) primary key,nome VARCHAR(255),cognome varchar(255),email VARCHAR(255),password VARCHAR(255),data_nascita DATE,codice_fiscale VARCHAR(16),corsodilaurea VARCHAR(255) REFERENCES corsodilaurea(\"codice\"),ruolo int,verifycode VARCHAR(255),imagepath VARCHAR(255));"
					+ "create table corso(\"codice\" bigint primary key, nome VARCHAR(255),anno int,descrizione VARCHAR(255),requisiti VARCHAR(255),giorni VARCHAR(255),ore_lezioni int,ore_esercitazioni int,materiale VARCHAR(255),\"docente\" VARCHAR(255) REFERENCES utente(\"matricola\"),corsodilaurea VARCHAR(255) REFERENCES corsodilaurea(\"codice\"),cfu int,cognomeDocente VARCHAR(255), nomeDocente VARCHAR(255),data_inizio Date, data_fine Date);"
					+ "create table aula(\"id\" VARCHAR(255) primary key,posti int,\"codice\" VARCHAR(255) REFERENCES corsodilaurea(\"codice\"),ubicazione VARCHAR(255));"
					+ "create table messaggio(\"id\" bigint primary key, data timestamp, testo text,matricola_mitt VARCHAR(255) REFERENCES utente(\"matricola\"),matricola_dest VARCHAR(255) REFERENCES utente(\"matricola\"));"
					+ "create table lezione(\"id\" bigint primary key,data DATE , ora_inizio timestamp, ora_fine timestamp,\"corso\" bigint REFERENCES corso(\"codice\"),\"aula\" VARCHAR(255) REFERENCES aula(\"id\"),tipo VARCHAR(255));"
					+ "create table calendariopersonale(matricola VARCHAR(255) primary key REFERENCES utente(\"matricola\"));"
					+ "create table iscritto(\"codice\" bigint REFERENCES corso(\"codice\"), matricola VARCHAR(255) REFERENCES utente(\"matricola\"));"
					+ "create table descrizionecorso(\"codice\" bigint primary key, nome VARCHAR(255),anno int,corsodilaurea VARCHAR(255) REFERENCES corsodilaurea(\"codice\"), cfu int, ore_lezioni int,ore_esercitazioni int );"
					+ "create table esame(corso bigint primary key REFERENCES descrizionecorso(\"codice\"),nome VARCHAR(255),cfu int);"
					+ "create table evento(\"id\" bigint primary key, title VARCHAR(255),inizio timestamp, fine timestamp, nota text );"
					+ "create table contiene(calendariopersonale VARCHAR(255) REFERENCES calendariopersonale(\"matricola\"), evento bigint REFERENCES evento(\"id\"));"
					+ "create table superato(esame bigint REFERENCES esame(\"corso\"), studente VARCHAR(255) REFERENCES utente(\"matricola\"),data timestamp,voto int);"
					+ "create table presenza(studente VARCHAR(255) REFERENCES utente(\"matricola\"), lezione bigint REFERENCES lezione (\"id\"));";

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
