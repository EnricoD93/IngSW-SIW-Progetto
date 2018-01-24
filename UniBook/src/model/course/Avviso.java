package model.course;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Avviso {
	private long id;
	private String titolo;
	private String text;
	private long corso;
	private Timestamp data;
	private String datareale;

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
		parseDate(data);
	}

	public String getDatareale() {
		return datareale;
	}

	public void setDatareale(String datareale) {
		this.datareale = datareale;
	}

	public Avviso() {

	}

	public long getCorso() {
		return corso;
	}

	public void setCorso(long corso) {
		this.corso = corso;
	}

	public Avviso(String text, String titolo, long corso, Timestamp data) {
		this.titolo = titolo;
		this.text = text;
		this.corso = corso;
		this.data = data;
		parseDate(data);
	}

	public void parseDate(Timestamp currentdata) {
		Date date = new Date(currentdata.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String formattedDate = sdf.format(date);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		setDatareale(formattedDate);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

}
