package model.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Notifica {

	private Timestamp data;
	private String destinatario;
	private String testo;
	private int type;
	static HashMap<Integer, String> testoNotifiche=new HashMap<>();
	
	private void init() {
	testoNotifiche.put(0, "è stata aggiunta una lezione.");
	testoNotifiche.put(1, "è stata rimossa una lezione.");
	testoNotifiche.put(2, "è stato cancellato");
	testoNotifiche.put(3, "è stato pubblicato un avviso");
	testoNotifiche.put(4, "Sei stato rimosso dal corso di");
	}

	private long id;
	private String datareale;
	private boolean letta;

	public Notifica() {
		init();
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
