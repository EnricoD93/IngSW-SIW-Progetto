package model;

import java.util.Date;

public class Docente extends Utente {

	public Docente() {
		super();
	}

	public Docente(String matricola, String nome, String cognome, Date dataNascita, String codicefiscale, String email,
			String password) {
		super(matricola, nome, cognome, dataNascita, codicefiscale, email, password);
	}
	

}
