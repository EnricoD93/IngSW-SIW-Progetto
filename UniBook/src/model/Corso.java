package model;

import java.util.HashSet;
import java.util.Set;

public class Corso {
	private Long codice;
	private String nome;
	private Docente docente;
	private Set<Studente> studenti;
	private Set<Avviso> avvisi;
	private Set<Lezione> lezioni;
	public Long getCodice() {
		return codice;
	}
	public void setCodice(Long codice) {
		this.codice = codice;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<Studente> getStudenti() {
		return studenti;
	}
	public void setStudenti(Set<Studente> studenti) {
		this.studenti = studenti;
	}
	
	public void addStudente(Studente studente){
		if (this.studenti == null){
			this.studenti = new HashSet<Studente>();
		}
		this.studenti.add(studente);
	}
	
	public Set<Avviso> getAvvisi() {
		return avvisi;
	}
	public void setAvvisi(Set<Avviso> avvisi) {
		this.avvisi = avvisi;
	}
	public Set<Lezione> getLezioni() {
		return lezioni;
	}
	public void setLezioni(Set<Lezione> lezioni) {
		this.lezioni = lezioni;
	}
	public String toString() {
		StringBuffer str = new StringBuffer("Corso[");
		str.append(this.getCodice() + ", " + this.getNome());
		str.append(", {");
		for (Studente s : this.getStudenti()) {
			str.append(s.toString());
		}
		str.append("}\n");
		return str.toString();
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
}
