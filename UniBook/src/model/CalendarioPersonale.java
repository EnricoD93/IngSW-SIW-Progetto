package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarioPersonale {
	private String matricola;
	private ArrayList<Lezione> lezioni = new ArrayList<>();
	private ArrayList<GiornoCalendario> eventi = new ArrayList<>();

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

	public ArrayList<Lezione> getLezioniCorso(Long codice, GiornoCalendario inizio, GiornoCalendario fine,
			String giorniLezione, String aula, int tipo, Timestamp oraInizio, Timestamp oraFine) {
		ArrayList<Lezione> lez = new ArrayList<>();
		GiornoCalendario i = new GiornoCalendario(inizio.giorno, inizio.mese, inizio.anno);

		for (; i.diverso(fine); i.next()) {

			if (i.getGiornoDellaSettimana().equals(giorniLezione)) {
				String ora = new SimpleDateFormat("hh:mm").format(new Date(oraInizio.getTime()));
				String fin = new SimpleDateFormat("hh:mm").format(new Date(oraFine.getTime()));
				String[] oraMin = ora.split(":");
				String[] oraMinFin = fin.split(":");
				Calendar cal = new GregorianCalendar(i.getAnno(), i.getMese(), i.getGiorno(),
						Integer.parseInt(oraMin[0]), Integer.parseInt(oraMin[1]));
				Calendar calfin = new GregorianCalendar(i.getAnno(), i.getMese(), i.getGiorno(),
						Integer.parseInt(oraMinFin[0]), Integer.parseInt(oraMinFin[1]));

				Timestamp oraIn = new Timestamp(cal.getTimeInMillis());
				Timestamp oraFin = new Timestamp(calfin.getTimeInMillis());
				lez.add(new Lezione(codice, new GiornoCalendario(i.getGiorno(), i.getMese(), i.getAnno()), oraIn,
						oraFin, aula, tipo));
			}
		}

		return lez;
	}

	public ArrayList<GiornoCalendario> getEventi() {
		return eventi;
	}

	public void setEventi(ArrayList<GiornoCalendario> eventi) {
		this.eventi = eventi;
	}

	public void aggiungiLezione(Lezione l) {
		lezioni.add(l);
		eventi.add(l.getData());
	}

	public String[] getGiorniLezioni(String s) {
		return s.split("\\s");
	}
}
