package model.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.course.Lezione;
import persistence.DatabaseManager;
import persistence.dao.LezioneDao;

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
			String giorniLezione, String aula, int tipo, Timestamp oraInizio, Timestamp oraFine, boolean coincidenti) {
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
				LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
				GiornoCalendario g = new GiornoCalendario(i.getGiorno(), i.getMese(), i.getAnno());
				List<Lezione> lezioni = lezioneDao.findAll();
				System.out.println("lezioni" + lezioni.size());
				if (!coincidenti)
					for (int j = 0; j < lezioni.size(); j++) {
						if (lezioni.get(j).getData().uguale(g)) {
							coincidenti = true;
							System.out.println("COINCIDENTI=TRUE");
							break;
						}

						System.out.println(
								g.giorno + "/" + g.mese + "/" + g.anno + " != " + lezioni.get(j).getData().giorno + "/"
										+ lezioni.get(j).getData().mese + "/" + lezioni.get(j).getData().anno);
					}
				lez.add(new Lezione(codice, g, oraIn, oraFin, aula, tipo));
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
