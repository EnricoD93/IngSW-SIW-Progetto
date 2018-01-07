package model;

import java.io.Serializable;

public class Corso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8357367383173334272L;
	private Long codice;
	private String nome;
	private int anno;
	private String descrizione;
	private String materiale;
	private String giorno;
	private String requisiti;
	private String docente;
	private String nomeDocente;
	private String cognomeDocente;
	private int oreLezione;
	private int oreEsercitazione;
	private String corsoDiLaurea;
	private int cfu;

	public Corso() {
	}

	public Corso(Long codice, String nome, int anno, String corsoDiLaurea, int cfu) {
		this.codice = codice;
		this.nome = nome;
		this.anno = anno;
		this.corsoDiLaurea = corsoDiLaurea;
		this.cfu = cfu;
		this.giorno = "";
		this.descrizione = "";
		this.materiale = "";
		this.requisiti = "";
		this.oreLezione = 0;
		this.oreEsercitazione = 0;
		this.docente = "";
		this.cognomeDocente = "";
		this.nomeDocente = "";
	}

	public Corso(Long codice, String nome, int anno, String descrizione, String giorno, String requisiti,
			int oreLezione, int oreEsercitazione, String materiale, String docente, String corsoDiLaurea, int cfu,
			String cognomeDocente, String nomeDocente) {
		this.codice = codice;
		this.nome = nome;
		this.anno = anno;
		this.descrizione = descrizione;
		this.giorno = giorno;
		this.requisiti = requisiti;
		this.oreLezione = oreLezione;
		this.oreEsercitazione = oreEsercitazione;
		this.materiale = materiale;
		this.docente = docente;
		this.corsoDiLaurea = corsoDiLaurea;
		this.cfu = cfu;
		this.cognomeDocente = cognomeDocente;
		this.nomeDocente = nomeDocente;
	}

	public String getCognomeDocente() {
		return cognomeDocente;
	}

	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}

	public String getNomeDocente() {
		return nomeDocente;
	}

	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMateriale() {
		return materiale;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getRequisiti() {
		return requisiti;
	}

	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
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

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}
}
