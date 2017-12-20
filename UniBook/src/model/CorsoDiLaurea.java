package model;

import java.util.HashSet;
import java.util.Set;

public class CorsoDiLaurea {
	private Long codice;
	private String nome;


	public CorsoDiLaurea() {
	}

	public CorsoDiLaurea(String nome) {
		this.nome = nome;
	}

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
