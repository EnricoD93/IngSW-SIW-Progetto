package model;

public class DescrizioneCorso {
	private Long codice;
	private String nome;
	private int anno;
	private int oreLezione;
	private int oreEsercitazione;
	private Long corsoDiLaurea;
	private int cfu;

	public DescrizioneCorso() {
	}

	public DescrizioneCorso(Long codice, String nome, int anno, Long corsoDiLaurea, int cfu,int oreLezione, int oreEsercitazione) {
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

	public Long getCorsoDiLaurea() {
		return corsoDiLaurea;
	}

	public void setCorsoDiLaurea(Long corsoDiLaurea) {
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