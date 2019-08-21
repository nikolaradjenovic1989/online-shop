package com.onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("2")
public class Dostavljac extends Korisnik {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "dostavljac", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Korpa> listaPorudzbina = new ArrayList<Korpa>();

	public Dostavljac() {}

	public Dostavljac(Integer id, String korisnickoIme, String lozinka, String ime, String prezime, String uloga,
			String telefon, String email, String adresa) {
		super(id, korisnickoIme, lozinka, ime, prezime, uloga, telefon, email, adresa);
	}

	public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, String uloga, String telefon,
			String email, String adresa) {
		super(korisnickoIme, lozinka, ime, prezime, uloga, telefon, email, adresa);
	}

	public List<Korpa> getListaPorudzbina() {
		return listaPorudzbina;
	}

	public void setListaPorudzbina(List<Korpa> listaPorudzbina) {
		this.listaPorudzbina = listaPorudzbina;
	}
	
	public void addKorpa(Korpa korpa){
		this.listaPorudzbina.add(korpa);
		if(!this.equals(korpa.getDostavljac())){
			korpa.setDostavljac(this);
		}
	}

}
