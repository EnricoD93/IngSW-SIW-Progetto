package model;

public class CorsoDiLaurea {
	private int codice;
	private String nome;

	public CorsoDiLaurea() {
	}

	public CorsoDiLaurea(int codice, String nome) {
		this.codice = codice;
		this.nome = nome;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
