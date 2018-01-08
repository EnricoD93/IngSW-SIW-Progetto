package model;

import java.util.ArrayList;

public class CalendarioPersonale {
	ArrayList<GiornoCalendario> calendarioPersonale = new ArrayList<>();

	public CalendarioPersonale() {
		java.util.GregorianCalendar gc = new java.util.GregorianCalendar(java.util.Locale.ITALY);
		int maxGiorni = 0;
		String[] giorniIt = { "DOMENICA", "LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO" };
		for (int k = 2018; k < 2021; k++)
			for (int j = 0; j < 12; j++) {
				if (j == 3 || j == 5 || j == 8 || j == 10)
					maxGiorni = 30;
				else if (j == 0 || j == 2 || j == 4 || j == 6 || j == 7 || j == 9 || j == 11)
					maxGiorni = 31;
				else if (bisestile(k))
					maxGiorni = 29;
				else
					maxGiorni = 28;

				for (int i = 1; i <= maxGiorni; i++) {
					java.util.GregorianCalendar gc3 = new java.util.GregorianCalendar(k, j, i);
					calendarioPersonale.add(new GiornoCalendario(i, j + 1, k,
							giorniIt[(gc3.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1)]));
				}
			}
	}

	public ArrayList<GiornoCalendario> getCalendarioPersonale() {
		return calendarioPersonale;
	}

	public boolean bisestile(int anno) {
		return (anno % 400 == 0) || (anno % 4 == 0 && anno % 100 != 0);
	}
}

