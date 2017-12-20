package model;

import java.sql.Time;
import java.util.Date;

public class Messaggio {
	private Date data;
	private String mittente;
	private String destinatario;
	private String testo;
	private Time ora;

	public Messaggio(Date data, String mittente, String destinatario, String testo,Time ora) {
		super();
		this.data = data;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.testo = testo;
		this.ora=ora;
	}

	public Messaggio() {
	}

	public void setData(Date data) {
		this.data = data;
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

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public Date getData() {
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

	public Time getOra() {
		return ora;
	}

}
