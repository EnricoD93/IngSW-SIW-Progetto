package model;

public class GiornoCalendario {
	int giorno;
	int mese;
	int anno;
	String giornoDellaSettimana;

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getGiornoDellaSettimana() {
		return giornoDellaSettimana;
	}

	public GiornoCalendario(int g, int m, int a, String giornoDellaSettimana) {
		this.giorno = g;
		this.mese = m;
		this.anno = a;
		this.giornoDellaSettimana=giornoDellaSettimana;
	}

	public void Stampa(GiornoCalendario g) {
		System.out.println(g.giorno + "/" + g.mese + "/ " + g.anno + " è " + g.giornoDellaSettimana);
	}
	public void setGiornoDellaSettimana(String giornoDellaSettimana) {
		this.giornoDellaSettimana = giornoDellaSettimana;
	}
}
