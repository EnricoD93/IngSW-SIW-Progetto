package model;

public class Aula {
	String nome;
	int posti;

	public Aula() {
	}

	public Aula(String nome, int posti) {
		this.nome = nome;
		this.posti = posti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}
	
}
