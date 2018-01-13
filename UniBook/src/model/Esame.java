package model;

public class Esame {

	private Long corso;
	private String nome;
	private int cfu;

	public Esame(Long corso, String nome, int cfu) {
		this.corso = corso;
		this.nome = nome;
		this.cfu = cfu;
	}

	public Esame() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Long getCorso() {
		return corso;
	}

	public void setCorso(Long corso) {
		this.corso = corso;
	}
}
