package model;

import java.util.Date;

public class Lezione {
	private Long corso;
	private Date data;
	private double oraInizio;
	private double oraFine;
	private String aula;
	private String tipo;

	public Lezione(Long corso, Date data,double oraInizio, double oraFine, String aula, String tipo) {
		
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

	public double getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(double oraInizio) {
		this.oraInizio = oraInizio;
	}

	public double getOraFine() {
		return oraFine;
	}

	public void setOraFine(double oraFine) {
		this.oraFine = oraFine;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
}
