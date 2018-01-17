package model.course;

import java.io.Serializable;

public class DescrizioneCorso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1652841553024978576L;
	private Long codice;
	private String nome;
	private int anno;
	private int oreLezione;
	private int oreEsercitazione;
	private String corsoDiLaurea;
	private int cfu;

	public DescrizioneCorso() {
	}

	public DescrizioneCorso(Long codice, String nome, int anno, String corsoDiLaurea, int cfu, int oreLezione,
			int oreEsercitazione) {
		this.codice = codice;
		this.nome = nome;
		this.anno = anno;
		this.corsoDiLaurea = corsoDiLaurea;
		this.cfu = cfu;
		this.oreLezione = oreLezione;
		this.oreEsercitazione = oreEsercitazione;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getOreLezione() {
		return oreLezione;
	}

	public void setOreLezione(int oreLezione) {
		this.oreLezione = oreLezione;
	}

	public int getOreEsercitazione() {
		return oreEsercitazione;
	}

	public void setOreEsercitazione(int oreEsercitazione) {
		this.oreEsercitazione = oreEsercitazione;
	}

	public String getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(String corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
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
