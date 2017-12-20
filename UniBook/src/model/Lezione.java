package model;

import java.util.Date;
import java.util.Set;

public class Lezione {
	private Long corso;
	private Date data;
	private int oraInizio;
	private String durata;
	private String aula;

	public Lezione(Long corso, Date data,int oraInizio, String durata, String aula) {
		
		this.corso=corso;
		this.data = data;
		this.oraInizio=oraInizio;
		this.durata=durata;
		this.aula=aula;
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

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		aula = aula;
	}
	
}
