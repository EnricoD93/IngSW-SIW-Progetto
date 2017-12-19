package model;

import java.util.Date;

public class Messaggio {
	private Date data;
	private Utente mittente;
	private Utente destinatario;
	private String oggetto;
	private String testo;

	public Messaggio(Date data, Utente mittente, Utente destinatario, String oggetto, String testo) {
		super();
		this.data = data;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.oggetto = oggetto;
		this.testo = testo;
	}

	public Messaggio() {
	}

}
