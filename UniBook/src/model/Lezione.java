package model;

import java.util.Date;

public class Lezione {
	private Long corso;
	private Date data;
	private int oraInizio;
	private int oraFine;
	private String aula;
	private String tipo;

	public Lezione(Long corso, Date data,int oraInizio, int oraFine, String aula, String tipo) {
		
		this.corso=corso;
		this.data = data;
		this.oraInizio=oraInizio;
		this.oraFine=oraFine;
		this.aula=aula;
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Lezione() {
	}

	public Long getCorso() {
		return corso;
	}

	public void setCorso(Long corso) {
		this.corso = corso;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}

	public int getOraFine() {
		return oraFine;
	}

	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
}
