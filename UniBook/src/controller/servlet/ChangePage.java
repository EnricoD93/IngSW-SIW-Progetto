package controller.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.json.JSONException;
import org.json.JSONObject;

import model.course.Aula;
import model.course.Avviso;
import model.course.Corso;
import model.course.DescrizioneCorso;
import model.course.Lezione;
import model.user.Esame;
import model.user.EsameSuperato;
import model.user.Evento;
import model.user.Messaggio;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.AulaDao;
import persistence.dao.AvvisoDao;
import persistence.dao.CorsoDao;
import persistence.dao.DescrizioneCorsoDao;
import persistence.dao.EsameDao;
import persistence.dao.LezioneDao;
import persistence.dao.MessaggioDao;
import persistence.dao.UtenteDao;

public class ChangePage extends HttpServlet {
	String request;
	List<Aula> aule;
	List<Corso> corsi;
	List<Avviso> avvisi;
	List<Esame> esami;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		request = req.getParameter("request");
		if (request.equals("inviaMessaggio")) {
			String dest = req.getParameter("dest");
			String mitt = req.getParameter("mitt");
			String testo = req.getParameter("text");
			Timestamp t = new Timestamp(System.currentTimeMillis());
			Messaggio m = new Messaggio(mitt, dest, testo, t);
			String date = m.getDatareale();
			JSONObject datareale = new JSONObject();
			try {
				datareale.put("date", date);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MessaggioDao messDao = DatabaseManager.getInstance().getDaoFactory().getMessaggioDAO();
			messDao.save(m);
			resp.setContentType("application/json");
			resp.getWriter().print(datareale);
		}
	}

	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		request = req.getParameter("request");
		if (session.getAttribute("currentUser") != null) {
			Utente currentUser = (Utente) session.getAttribute("currentUser");
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			CorsoDao corsoDao = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
			AulaDao aulaDao = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
			AvvisoDao avvDao = DatabaseManager.getInstance().getDaoFactory().getAvvisoDAO();
			if (request != null) {

				switch (request) {

				case "aule":
					aule = aulaDao.findAll();
					req.setAttribute("aule", aule);
					req.getRequestDispatcher("aule.jsp").forward(req, resp);
					break;

				case "calendario":
					corsi = utenteDao.getCorsiDocente(currentUser.getMatricola());
					AulaDao aula = DatabaseManager.getInstance().getDaoFactory().getAulaDAO();
					List<Aula> aule = aula.findAll();
					req.setAttribute("corsi", corsi);
					req.setAttribute("aule", aule);
					req.getRequestDispatcher("calendarioPersonale.jsp").forward(req, resp);
					break;

				case "corsi":
					if (currentUser.getRuolo() == 0) {
						Map<Long, Integer> percentuali = new HashMap<Long, Integer>();
						corsi = utenteDao.getCorsiIscritto(currentUser.getMatricola());
						DescrizioneCorsoDao descrizioneCorsoDao = DatabaseManager.getInstance().getDaoFactory()
								.getDescrizioneCorsoDao();
						for (int i = 0; i < corsi.size(); i++) {
							Long codice = corsi.get(i).getCodice();
							int ore = descrizioneCorsoDao.findByPrimaryKey(codice).getOreLezione()
									+ descrizioneCorsoDao.findByPrimaryKey(codice).getOreEsercitazione();

							int presenze = utenteDao.findPresenze(currentUser.getMatricola(), codice);
							int percentuale = ((presenze*3) * 100) / ore;
							percentuali.put(corsi.get(i).getCodice(), percentuale);
						}
						req.setAttribute("percentuali", percentuali);

					} else if (currentUser.getRuolo() == 1) {
						corsi = utenteDao.getCorsiDocente(currentUser.getMatricola());
					}
					req.setAttribute("corsi", corsi);
					req.getRequestDispatcher("corsi.jsp").forward(req, resp);
					break;

				case "profilo":
					String profileId = req.getParameter("id");
					Utente utente = utenteDao.findByPrimaryKey(profileId);
					req.setAttribute("profilo", utente);
					req.getRequestDispatcher("profilo.jsp").forward(req, resp);
					break;

				case "corso":
					Long codice = Long.parseLong(req.getParameter("id"));
					Corso currentCourse = corsoDao.findByPrimaryKey(codice);
					Utente courseDocente = utenteDao.findByPrimaryKey(currentCourse.getDocente());
					avvisi = avvDao.findAllByCourse(codice);
					esami = corsoDao.getEsamiPropedeutici(codice);
					String giorniLezione = currentCourse.getGiorno();
					String[] giorni = giorniLezione.split("_");
					ArrayList<HashMap<String, String>> lista = new ArrayList<>();
					for (int i = 0; i < giorni.length; i++) {
						String[] giorno = giorni[i].split(",");
						HashMap<String, String> h = new HashMap<>();
						h.put("giorno", giorno[0]);
						try {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
							Date parsedDate;
							parsedDate = dateFormat.parse(giorno[1]);;
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							giorno[1] = sdf.format(parsedDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						h.put("dalle", giorno[1]);
						try {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
							Date parsedDate;
							parsedDate = dateFormat.parse(giorno[2]);;
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
							giorno[2] = sdf.format(parsedDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						h.put("alle", giorno[2]);
						h.put("tipo", giorno[3]);
						h.put("aula", giorno[4]);

						lista.add(h);
					}
					for (HashMap<String, String> hashMap : lista) {
						System.out.println(hashMap);
					}
					req.setAttribute("courseDocente", courseDocente);
					req.setAttribute("currentCourse", currentCourse);
					req.setAttribute("esami", esami);
					req.setAttribute("giorni", lista);
					req.setAttribute("advices", avvisi);
					req.getRequestDispatcher("course.jsp").forward(req, resp);
					break;

				case "listaStudenti":
					Long codicec = Long.parseLong(req.getParameter("codice"));
					List<Utente> studentiIscritti = corsoDao.getStudentiIscritti(codicec);
					LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
					Corso currentCourse1 = corsoDao.findByPrimaryKey(codicec);
					req.setAttribute("studentiIscritti", studentiIscritti);
					req.setAttribute("currentCourse", currentCourse1);
					List<Evento> eventi = lezioneDao.findCourseLessons(codicec);
					List<Lezione> lezioni = new ArrayList<>();
					for (int i = 0; i < eventi.size(); i++) {
						lezioni.add(lezioneDao.findByPrimaryKey(eventi.get(i).getId()));
					}
					req.setAttribute("lezioni", lezioni);
					req.setAttribute("codice", codicec);
					req.getRequestDispatcher("studentiIscritti.jsp").forward(req, resp);
					break;

				case "creaCorso":
					List<DescrizioneCorso> listaCorsi;
					List<DescrizioneCorso> allCorsi;
					List<Aula> listaAule;
					DescrizioneCorsoDao descrizioneDao = DatabaseManager.getInstance().getDaoFactory()
							.getDescrizioneCorsoDao();
					listaAule = aulaDao.findAll();
					listaCorsi = descrizioneDao.findNotCreatedCourses();
					allCorsi = descrizioneDao.findAll();
					req.setAttribute("listaCorsi", listaCorsi);
					req.setAttribute("listaAule", listaAule);
					req.setAttribute("allCorsi", allCorsi);
					req.getRequestDispatcher("createCourse.jsp").forward(req, resp);
					break;
				case "modificaCorso":
					System.out.println("entro");
					Long corso = Long.parseLong(req.getParameter("corso"));
					List<Aula> listaAule2;
					listaAule2 = aulaDao.findAll();
					req.setAttribute("listaAule", listaAule2);
					CorsoDao corsoDao2 = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
					Corso c = corsoDao2.findByPrimaryKey(corso);
					String inizio = c.getDataInizio().toString();
					String fine = c.getDataFine().toString();
					req.setAttribute("currentCourse", c);
					DescrizioneCorsoDao descrizioneDao2 = DatabaseManager.getInstance().getDaoFactory()
							.getDescrizioneCorsoDao();
					List<Esame> propedeuticità = corsoDao2.getEsamiPropedeutici(c.getCodice());
					Map<Long, Esame> prop = new HashMap<>();
					for (Esame esame : propedeuticità) {
						prop.put(esame.getCorso(), esame);
					}
					listaCorsi = descrizioneDao2.findNotCreatedCourses();
					allCorsi = descrizioneDao2.findAll();
					req.setAttribute("inizio", inizio);
					req.setAttribute("fine", fine);
					req.setAttribute("listaCorsi", listaCorsi);
					req.setAttribute("allCorsi", allCorsi);
					req.setAttribute("prop", prop);

					req.getRequestDispatcher("modifyCourse.jsp").forward(req, resp);
					break;
				case "modificaC":
					Long corso3 = Long.parseLong(req.getParameter("corso"));

					CorsoDao corsoDao3 = DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
					Corso c1 = corsoDao3.findByPrimaryKey(corso3);

					String giorniLezione1 = c1.getGiorno();
					String[] giorni1 = giorniLezione1.split("_");
					ArrayList<HashMap<String, String>> lista1 = new ArrayList<>();
					for (int i = 0; i < giorni1.length; i++) {
						System.out.println("giorni " + giorni1[i]);
					}
					for (int i = 0; i < giorni1.length; i++) {
						String[] giorno1 = giorni1[i].split(",");
						HashMap<String, String> h = new HashMap<>();
						h.put("giorno", giorno1[0]);
						h.put("dalle", giorno1[1]);
						h.put("alle", giorno1[2]);
						h.put("tipo", giorno1[3]);
						h.put("aula", giorno1[4]);

						lista1.add(h);
					}

					JSONObject json = new JSONObject();
					try {
						json.put("lista", lista1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resp.setContentType("application/json");
					resp.getWriter().print(json);

					break;
				case "colleghi":
					List<Utente> colleghi;
					colleghi = utenteDao.findColleaguesByCorsoDiLaurea(currentUser);
					req.setAttribute("colleghi", colleghi);
					req.getRequestDispatcher("colleghi.jsp").forward(req, resp);
					break;

				case "docenti":
					List<Utente> docenti;
					docenti = utenteDao.findAllProfessor();
					req.setAttribute("docenti", docenti);
					req.getRequestDispatcher("docenti.jsp").forward(req, resp);
					break;

				case "messaggi":
					List<Utente> conversazioni;
					conversazioni = utenteDao.findMessageSendersByMatricola(currentUser.getMatricola());
					System.out.println(conversazioni);
					Map<String, Integer> letti = new HashMap<String, Integer>();
					for (Utente utente2 : conversazioni) {
						letti.put(utente2.getMatricola(),
								utenteDao.findUnreadMessages(utente2.getMatricola(), currentUser.getMatricola()));
					}
					req.setAttribute("conversazioni", conversazioni);
					req.setAttribute("letti", letti);
					req.getRequestDispatcher("messaggi.jsp").forward(req, resp);
					break;

				case "conversazione":
					String id = req.getParameter("id");
					Utente u = utenteDao.findByPrimaryKey(id);
					MessaggioDao messDao = DatabaseManager.getInstance().getDaoFactory().getMessaggioDAO();
					List<Messaggio> messaggi;
					messaggi = messDao.findMessagesByUtenti(currentUser.getMatricola(), id);
					for (Messaggio messaggio : messaggi) {
						if (messaggio.getDestinatario().equals(currentUser.getMatricola()))
							messDao.updateUnreadMessages(messaggio.getId());
					}
					req.setAttribute("messaggi", messaggi);
					req.setAttribute("utenteConversazione", u);
					req.getRequestDispatcher("conversazioni.jsp").forward(req, resp);
					break;
				case "esami":
					List<EsameSuperato> esami = utenteDao.findEsamiSuperati(currentUser.getMatricola());
					List<Esame> esamiIscritto = utenteDao.findEsamiIscritto(currentUser.getMatricola());
					List<Esame> esamiNonSuperati = utenteDao.findEsamiNonSuperati(currentUser.getMatricola());
					double mediaProv = 0;
					double votoPartenzaProv = 0;
					int votoPartenza = 0;
					int media = 0;
					int lode = 0;
					if (esami.size() > 0) {
						for (int i = 0; i < esami.size(); i++) {
							if (esami.get(i).getVoto() == 31) {
								lode++;
								esami.get(i).setVoto(30);
							}
							mediaProv += esami.get(i).getVoto();
						}
						mediaProv /= esami.size();
						media = (int) Math.round(mediaProv);
						System.out.println(media);
						votoPartenzaProv = (media * 11) / 3;
						votoPartenzaProv = Math.round(votoPartenzaProv);
						votoPartenzaProv = votoPartenzaProv + (lode * 0.2);
						votoPartenzaProv = Math.round(votoPartenzaProv);
						votoPartenza = (int) votoPartenzaProv;
					}
					req.setAttribute("media", media);
					req.setAttribute("votoPartenza", votoPartenza);
					req.setAttribute("esami", esami);
					req.setAttribute("esamiIscritto", esamiIscritto);
					req.setAttribute("esamiNonSuperati", esamiNonSuperati);
					req.getRequestDispatcher("esami.jsp").forward(req, resp);
					break;

				default:
					resp.setStatus(404);
					break;
				}

			} else {
				req.getRequestDispatcher("home").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("home").forward(req, resp);
		}
	}

}
