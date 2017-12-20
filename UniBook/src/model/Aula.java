package model;

public class Aula {
	private String id;
	private int posti;
	private Long corsoDiLaurea;

	public Aula() {
	}

	public Aula(String id, int posti,Long corsoDiLaurea) {
		this.id = id;
		this.posti = posti;
		this.corsoDiLaurea=corsoDiLaurea;
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
	
}
