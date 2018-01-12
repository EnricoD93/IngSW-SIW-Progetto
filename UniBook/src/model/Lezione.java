package model;

import java.sql.Timestamp;

public class Lezione {
	private Long corso;
	private GiornoCalendario data;
	private Timestamp oraInizio;
	private Timestamp oraFine;
	private String aula;
	private int tipo;
	private long id;

	public Lezione(Long corso, GiornoCalendario data,Timestamp oraInizio, Timestamp oraFine, String aula, int tipo) {
		
		this.corso=corso;
		this.data = data;
		this.oraInizio=oraInizio;
		this.oraFine=oraFine;
		this.aula=aula;
		this.tipo=tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
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

	public GiornoCalendario getData() {
		return data;
	}

	public void setData(GiornoCalendario data) {
		this.data = data;
	}

	public Timestamp getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(Timestamp oraInizio) {
		this.oraInizio = oraInizio;
	}

	public Timestamp getOraFine() {
		return oraFine;
	}

	public void setOraFine(Timestamp oraFine) {
		this.oraFine = oraFine;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
}
