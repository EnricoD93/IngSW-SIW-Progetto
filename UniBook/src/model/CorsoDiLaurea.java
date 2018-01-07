package model;

public class CorsoDiLaurea {
	private String codice;
	private String nome;

	public CorsoDiLaurea() {
	}

	public CorsoDiLaurea(String codice, String nome) {
		this.codice = codice;
		this.nome = nome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
