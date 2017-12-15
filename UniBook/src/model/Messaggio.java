package model;

import java.util.Date;

public class Messaggio {
	private Date data;
	private User mittente;
	private User destinatario;
	private String oggetto;
	private String testo;

	public Messaggio(Date data, User mittente, User destinatario, String oggetto, String testo) {
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
