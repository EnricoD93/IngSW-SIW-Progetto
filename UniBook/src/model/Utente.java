package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utente {
	private String matricola;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String codicefiscale;
	private String email;
	private String password;
	private Long corsoDiLaurea;
	private int ruolo;
	private String verifyCode;
	private String profileImagePath;
	
	public Utente() {
	}

	public Utente(String matricola, String nome, String cognome, Date dataNascita, String codicefiscale, String email,
			String password, Long corsoDiLaurea, int ruolo, String verifyCode) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codicefiscale = codicefiscale;
		this.email = email;
		this.password = password;
		this.corsoDiLaurea = corsoDiLaurea;
		this.ruolo = ruolo;
		this.verifyCode = verifyCode;
		profileImagePath="images/profileImages/default.png";
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

	public String getDataNascitaString() {
		String giorno,mese;
		if(dataNascita.getDay()<10)
			giorno="0"+dataNascita.getDay();
		else
			giorno=String.valueOf(dataNascita.getDay());
		if(dataNascita.getMonth()<10)
			mese="0"+dataNascita.getMonth();
		else
			mese=String.valueOf(dataNascita.getMonth());
		
		return ""+giorno+"/"+mese+"/"+dataNascita.getYear();
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

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Studente[" + this.getMatricola() + ", " + this.getNome() + ", " + this.getCognome() + ", "
				+ sdf.format(this.getDataNascita()) + "]";
	}

	public Long getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(Long corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
	}

	public int getRuolo() {
		return ruolo;
	}

	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

}
