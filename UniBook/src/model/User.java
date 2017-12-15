package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User {
	private String matricola;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String codicefiscale;
	private String email;
	private String password;

	public User() {
	}

	public User(String matricola, String nome, String cognome, Date dataNascita, String codicefiscale, String email,
			String password) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codicefiscale = codicefiscale;
		this.email = email;
		this.password = password;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int hashCode() {
		return this.matricola.hashCode();
	}

	public boolean equals(Object object) {
		Studente studente = (Studente) object;
		return (this.getMatricola() == studente.getMatricola());
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Studente[" + this.getMatricola() + ", " + this.getNome() + ", " + this.getCognome() + ", "
				+ sdf.format(this.getDataNascita()) + "]";
	}

}
