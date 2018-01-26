package controller.servlet;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import model.course.Corso;
import model.course.Lezione;
import model.user.Evento;
import model.user.Utente;
import persistence.DatabaseManager;
import persistence.dao.CalendarioPersonaleDao;
import persistence.dao.CorsoDao;
import persistence.dao.LezioneDao;
import persistence.dao.UtenteDao;

public class SignUpForCourse extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String request = req.getParameter("request");
		Utente currentUser=(Utente) session.getAttribute("currentUser");
		UtenteDao udao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Long codice = Long.parseLong(req.getParameter("codice"));
		Utente u = (Utente) session.getAttribute("currentUser");
		String typedPassword = req.getParameter("typedPassword");
		String matricola = req.getParameter("matricola");
		CalendarioPersonaleDao calendarioPersonaleDao = DatabaseManager.getInstance().getDaoFactory()
				.getCalendarioPersonaleDAO();
		List<Evento> listaEventiCal = calendarioPersonaleDao.findAllEventsUtente(u.getMatricola());
		LezioneDao lezioneDao = DatabaseManager.getInstance().getDaoFactory().getLezioneDAO();
		List<Evento> listaLezioni = lezioneDao.findCourseLessons(codice);
		CorsoDao corsoDao=DatabaseManager.getInstance().getDaoFactory().getCorsoDAO();
		if (request.equals("iscrizioneM")) {
			Utente studente=udao.findByPrimaryKey(matricola);
			if (studente != null) {
				if (udao.iscritto(matricola, codice)) {
					resp.setStatus(405);
					return;
				} else {
					
					JSONObject json= new JSONObject();
					try {
						json.put("matricola", studente.getMatricola());
						json.put("nome", studente.getNome());
						json.put("cognome", studente.getCognome());
						json.put("path", studente.getProfileImagePath());
						json.put("email", studente.getEmail());
						json.put("codicefiscale", studente.getCodicefiscale());
						json.put("docente", u.getMatricola());
						json.put("currentUserMatr", currentUser.getMatricola());
						json.put("currentCourseDoc", corsoDao.findByPrimaryKey(codice).getDocente());
						json.put("loop", corsoDao.getStudentiIscritti(codice).size()+1);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resp.setContentType("application/json");
					resp.getWriter().print(json);
					//req.setAttribute("matricola", studente.getMatricola());
					//req.setAttribute("nome", studente.getNome());
					//req.setAttribute("cognome", studente.getCognome());
					//req.setAttribute("email", studente.getEmail());
					//req.setAttribute("codicefiscale", studente.getCodicefiscale());
					//req.setAttribute("docente",u.getMatricola());
					udao.iscriviStudente(matricola, codice);

					for (int i = 0; i < listaLezioni.size(); i++) {
						calendarioPersonaleDao.saveEvent(matricola, listaLezioni.get(i));
					}

				}
			} else {
				resp.setStatus(403);
				return;
			}
		} else if (request.equals("cancellazioneM")) {
			lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao, matricola);
			udao.eliminaIscrizioneStudente(matricola, codice);

			req.getRequestDispatcher("home").forward(req, resp);
		} else {
			if (u.getPassword().equals(typedPassword)) {
				if (request.equals("iscrizione")) {
					if (udao.iscritto(matricola, codice)) {
						resp.setStatus(405);
						return;
					} else {
						udao.iscriviStudente(matricola, codice);

						for (int i = 0; i < listaLezioni.size(); i++) {
							for (int j = 0; j < listaEventiCal.size(); j++) {
								if (listaLezioni.get(i).getId() == listaEventiCal.get(j).getId()) {
									resp.setStatus(403);
									break;
								}
							}
						}

						for (int i = 0; i < listaLezioni.size(); i++) {
							calendarioPersonaleDao.saveEvent(matricola, listaLezioni.get(i));
						}

					}
				}
				if (request.equals("cancellazione")) {
					lezioneDao.eliminaLezioniDalCalendario(listaEventiCal, listaLezioni, calendarioPersonaleDao,
							matricola);
					udao.eliminaIscrizioneStudente(matricola, codice);

					req.getRequestDispatcher("home").forward(req, resp);

				}
			} else {
				resp.setStatus(401);
			}
		}
	}

}
