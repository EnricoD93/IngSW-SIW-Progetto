package model.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Notifica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3662826029891335483L;
	private Timestamp data;
	private String destinatario;
	private String testo;
	private int type;
	static HashMap<Integer, String> testoNotifiche=new HashMap<>();
	private long id;
	private String datareale;
	private boolean letta;
	
	private void init() {
	testoNotifiche.put(0, "E' stata aggiunta una nuova lezione al corso di");
	testoNotifiche.put(1, "E' stata rimossa una lezione dal corso di");
	testoNotifiche.put(2, "E' stato cancellato il corso di");
	testoNotifiche.put(3, "E' stato pubblicato un avviso nel corso di");
	testoNotifiche.put(4, "Sei stato rimosso dal corso di");
	}


	public Notifica() {
		init();
	}

	public Notifica(String destinatario, Timestamp currentdata,Integer type,String soggetto) {
		init();
		this.data = currentdata;
		this.destinatario = destinatario;
		this.type=type;
		this.testo=testoNotifiche.get(type)+" "+soggetto+".";
		System.out.println("testo:"+testo);
		parseDate(currentdata);
		this.letta = false;
	}
	public Notifica(String destinatario, Timestamp currentdata,Integer type) {
		init();
		this.data = currentdata;
		this.destinatario = destinatario;
		this.type=type;
		this.testo=testoNotifiche.get(type);
		System.out.println("testo:"+testo);
		parseDate(currentdata);
		this.letta = false;
	}

	public void parseDate(Timestamp currentdata) {
		Date date = new Date(currentdata.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String formattedDate = sdf.format(date);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		setDatareale(formattedDate);
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDatareale() {
		return datareale;
	}

	public void setDatareale(String datareale) {
		this.datareale = datareale;
	}

	public boolean isLetta() {
		return letta;
	}

	public void setLetta(boolean letta) {
		this.letta = letta;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static HashMap<Integer, String> getTestoNotifiche() {
		return testoNotifiche;
	}

	public static void setTestoNotifiche(HashMap<Integer, String> testoNotifiche) {
		Notifica.testoNotifiche = testoNotifiche;
	}

	

}
