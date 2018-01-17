package model.course;

import java.io.Serializable;

public class Aula implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7635935810389625364L;
	private String id;
	private int posti;
	private String corsoDiLaurea;
	private String ubicazione;

	public Aula() {
	}

	public Aula(String id, int posti, String corsoDiLaurea, String ubicazione) {
		this.id = id;
		this.posti = posti;
		this.corsoDiLaurea = corsoDiLaurea;
		this.setUbicazione(ubicazione);
	}

	public String getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(String corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}

	public String getUbicazione() {
		return ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

}
