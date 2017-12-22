package model;

import java.util.Date;

public class Docente extends Utente {


	public Docente(String matricola, String nome, String cognome, Date dataNascita, String codicefiscale, String email,
			String password,int corsoDiLaurea) {
		super(matricola, nome, cognome, dataNascita, codicefiscale, email, password,corsoDiLaurea);
	}
	

}
