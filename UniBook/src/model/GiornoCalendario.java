package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GiornoCalendario {
	int giorno;
	int mese;
	int anno;
	String giornoDellaSettimana;
	String[] giorniIt = { "DOMENICA", "LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO" };

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

	public GiornoCalendario(int g, int m, int a) {
		this.giorno = g;
		this.mese = m;
		this.anno = a;
		java.util.GregorianCalendar gc3 = new java.util.GregorianCalendar(g, m, a);
		giornoDellaSettimana = giorniIt[(gc3.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1)];
	}

	public GiornoCalendario() {
		// TODO Auto-generated constructor stub
	}

	public Date parseToDate(String s) {
		String[] info;
		info = s.split("\\s");
		switch (info[2]) {
		case "Gennaio":
			info[2] = "0";
			break;
		case "Febbraio":
			info[2] = "1";
			break;
		case "Marzo":
			info[2] = "2";
			break;
		case "Aprile":
			info[2] = "3";
			break;
		case "Maggio":
			info[2] = "4";
			break;
		case "Giugno":
			info[2] = "5";
			break;
		case "Luglio":
			info[2] = "6";
			break;
		case "Agosto":
			info[2] = "7";
			break;
		case "Settembre":
			info[2] = "8";
			break;
		case "Ottobre":
			info[2] = "9";
			break;
		case "Novembre":
			info[2] = "10";
			break;
		case "Dicembre":
			info[2] = "11";
			break;
		default:
			break;
		}
		
		Calendar g = new java.util.GregorianCalendar(Integer.parseInt(info[3]), Integer.parseInt(info[2]),
				Integer.parseInt(info[1]));
		return new Date(g.getTime().getTime());
	}

	public void parseToGiornoCalendario(Date d) {
		Calendar g = new GregorianCalendar();
		g.setTime(d);
		giorno=g.get(Calendar.DAY_OF_MONTH);
		mese=g.get(Calendar.MONTH) + 1;
		anno=g.get(Calendar.YEAR);
		giornoDellaSettimana = giorniIt[(g.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1)];
	}
public Date GiornoCalendarioToDate() {
	Calendar g = new java.util.GregorianCalendar(getAnno(), getMese(),getGiorno());
	return new Date(g.getTime().getTime());
}
	public void stampa() {
		System.out.println(giorno + "/" + mese + "/" + anno + " è " +giornoDellaSettimana);
	}

	public void setGiornoDellaSettimana(String giornoDellaSettimana) {
		this.giornoDellaSettimana = giornoDellaSettimana;
	}
}
