package model.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EsameSuperato extends Esame {

	private Timestamp data;
	private int voto;
	private String datareale;

	public EsameSuperato(Long corso, String nome, int cfu, Timestamp data, int voto) {
		super(corso, nome, cfu);
		this.data = data;
		this.voto = voto;
		parseDate(data);
	}

	public EsameSuperato() {
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public void parseDate(Timestamp currentdata) {
		Date date = new Date(currentdata.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		datareale = formattedDate;
	}

	public String getDatareale() {
		return datareale;
	}

	public void setDatareale(String datareale) {
		this.datareale = datareale;
	}
}
