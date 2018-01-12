package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Messaggio {
	private Timestamp data;

	private String mittente;
	private String destinatario;
	private String testo;
	private long id;
	private String datareale;

	public Messaggio(String mittente, String destinatario, String testo, Timestamp currentdata) {
		this.data = currentdata;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.testo = testo;
		parseDate(currentdata);
	}

	public void parseDate(Timestamp currentdata) {
		Date date = new Date(currentdata.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String formattedDate = sdf.format(date);
		setDatareale(formattedDate);
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

	public String getDatareale() {
		return datareale;
	}

	public void setDatareale(String datareale) {
		this.datareale = datareale;
	}

}
