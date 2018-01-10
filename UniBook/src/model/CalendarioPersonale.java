package model;

import java.util.ArrayList;

public class CalendarioPersonale {
	private String matricola;
	ArrayList<Lezione> lezioni = new ArrayList<>();

	public CalendarioPersonale(String matricola) {
		this.matricola = matricola;
	}

	public CalendarioPersonale() {
	}

	public ArrayList<Lezione> getLezioni() {
		return lezioni;
	}

	public boolean bisestile(int anno) {
		return (anno % 400 == 0) || (anno % 4 == 0 && anno % 100 != 0);
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public void setLezioni(ArrayList<Lezione> lezioni) {
		this.lezioni = lezioni;
	}

	public ArrayList<Lezione> getLezioniCorso(Long codice,GiornoCalendario inizio, GiornoCalendario fine, String giorniLezione,String aula, int tipo,double oraInizio,double oraFine) {
		ArrayList<Lezione> lez = new ArrayList<>();
		String[] giorniLez = getGiorniLezioni(giorniLezione);
		for (GiornoCalendario i = inizio; i != fine; i.next()) {
			for (int j = 0; j < giorniLez.length; j++)
				if (i.getGiornoDellaSettimana() == giorniLez[j]) {
					lez.add(new Lezione(codice,i,oraInizio,oraFine,aula,tipo));
				}

		}
		return lez;
	}

	// java.util.GregorianCalendar gc = new
	// java.util.GregorianCalendar(java.util.Locale.ITALY);
	// int maxGiorni = 0;
	//
	// for (int k = 2018; k < 2021; k++)
	// for (int j = 0; j < 12; j++) {
	// if (j == 3 || j == 5 || j == 8 || j == 10)
	// maxGiorni = 30;
	// else if (j == 0 || j == 2 || j == 4 || j == 6 || j == 7 || j == 9 || j == 11)
	// maxGiorni = 31;
	// else if (bisestile(k))
	// maxGiorni = 29;
	// else
	// maxGiorni = 28;
	//
	// for (int i = 1; i <= maxGiorni; i++) {
	// calendarioPersonale.add(new GiornoCalendario(i, j + 1, k));
	//
	// }
	// }
	public String[] getGiorniLezioni(String s) {
		return s.split("\\s");
	}
}
