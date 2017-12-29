package model;


public class Corso {
	private Long codice;
	private String nome;
	private int anno;
	private String descrizione;
	private String materiale;
	private String giorno;
	private String requisiti;
	private String docente;
	private int oreLezione;
	private int oreEsercitazione;
	private Long corsoDiLaurea;
	private int cfu;

	public Corso() {
	}

	public Corso(Long codice, String nome, int anno, String descrizione, String giorno, String requisiti,
			int oreLezione, int oreEsercitazione, String materiale, String docente, Long corsoDiLaurea,int cfu) {
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
		this.cfu=cfu;
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

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}
}
