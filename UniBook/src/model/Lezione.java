package model;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.Set;

public class Lezione {
	Date data;
	Set<DayOfWeek> giorni;
	String time;

	public Lezione(Date data, Set<DayOfWeek> giorni, String time) {
		super();
		this.data = data;
		this.giorni = giorni;
		this.time = time;
	}

	public Lezione() {
	}
}
