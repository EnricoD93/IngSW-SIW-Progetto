package model;

import java.util.Date;

public class Studente extends Utente {
	public Studente() {
		super();
	}

	public Studente(String matricola, String nome, String cognome, Date dataNascita, String codicefiscale, String email,
			String password,Long corsoDiLaurea) {
		super(matricola, nome, cognome, dataNascita, codicefiscale, email, password,corsoDiLaurea);
	}
}