package model;

public class Aula {
	private String id;
	private int posti;
	private Long corsoDiLaurea;
	private String ubicazione;

	public Aula() {
	}

	public Aula(String id, int posti, Long corsoDiLaurea, String ubicazione) {
		this.id = id;
		this.posti = posti;
		this.corsoDiLaurea = corsoDiLaurea;
		this.setUbicazione(ubicazione);
	}

	public Long getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(Long corsoDiLaurea) {
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
