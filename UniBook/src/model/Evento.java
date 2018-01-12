package model;

import java.sql.Timestamp;

public class Evento {
	String title;
	Timestamp inizio;
	Timestamp fine;
	String nota;
	long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Evento() {
	}

	public Evento(String title, Timestamp inizio, Timestamp fine, String nota) {
		this.title = title;
		this.inizio = inizio;
		this.fine = fine;
		this.nota = nota;
	}

	public String getTitle() {
		return title;
	}

	public Timestamp getInizio() {
		return inizio;
	}

	public void setInizio(Timestamp inizio) {
		this.inizio = inizio;
	}

	public Timestamp getFine() {
		return fine;
	}

	public void setFine(Timestamp fine) {
		this.fine = fine;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
