package model;

import java.sql.Timestamp;

public class Esame {

	private Long corso;
	private String nome;
	private String voto;
	private Timestamp data;
	private int cfu;
	

	public Esame(Long corso, String nome, String voto, Timestamp data, int cfu) {
		this.corso = corso;
		this.nome = nome;
		this.voto = voto;
		this.data = data;
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

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}
}
