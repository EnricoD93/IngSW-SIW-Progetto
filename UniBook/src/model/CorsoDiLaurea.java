package model;

public class CorsoDiLaurea {
	private Long codice;
	private String nome;

	public CorsoDiLaurea() {
	}

	public CorsoDiLaurea(Long codice, String nome) {
		this.codice = codice;
		this.nome = nome;
	}

	public CorsoDiLaurea(Long cdl) {
		int i = cdl.intValue();
		this.codice = cdl;
		switch (i) {
		case 733:
			this.nome = "Informatica";
			break;

		default:
			break;
		}
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
