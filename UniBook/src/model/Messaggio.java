package model;

import java.sql.Timestamp;

public class Messaggio {
	private Timestamp data;

	private String mittente;
	private String destinatario;
	private String testo;
	private long id;

	public Messaggio(String mittente, String destinatario, String testo) {
		
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.testo = testo;
	}

	public Messaggio() {
	}

	public void setData(Timestamp data) {
		this.data = data;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}


	public Timestamp getData() {
		return data;
	}

	public String getMittente() {
		return mittente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getTesto() {
		return testo;
	}

	

}
