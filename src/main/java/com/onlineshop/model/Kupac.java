package com.onlineshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("1")
public class Kupac extends Korisnik {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "ranijeKupljeni")
	private Set<Artikal> ranijeKupljeniArtikli = new HashSet<Artikal>();
	
	@ManyToMany(mappedBy = "kupciVoleArtikal")
	private Set<Artikal> omiljeniArtikli = new HashSet<Artikal>();
	
	@ManyToMany(mappedBy = "uKorpiKodKupca")
	private Set<Artikal> artikliUKorpi = new HashSet<Artikal>();
	
    @OneToMany(mappedBy = "kupac", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Korpa> korpe = new HashSet<Korpa>();

	public Kupac() {
		super();
	}

	public Kupac(Integer id, String korisnickoIme, String lozinka, String ime, String prezime, String uloga,
			String telefon, String email, String adresa) {
		super(id, korisnickoIme, lozinka, ime, prezime, uloga, telefon, email, adresa);
	}

	public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String uloga, String telefon,
			String email, String adresa) {
		super(korisnickoIme, lozinka, ime, prezime, uloga, telefon, email, adresa);
	}

	public Set<Artikal> getRanijeKupljeniArtikli() {
		return ranijeKupljeniArtikli;
	}

	public void setRanijeKupljeniArtikli(Set<Artikal> ranijeKupljeniArtikli) {
		this.ranijeKupljeniArtikli = ranijeKupljeniArtikli;
	}

	public Set<Artikal> getOmiljeniArtikli() {
		return omiljeniArtikli;
	}

	public void setOmiljeniArtikli(Set<Artikal> omiljeniArtikli) {
		this.omiljeniArtikli = omiljeniArtikli;
	}

	public Set<Artikal> getArtikliUKorpi() {
		return artikliUKorpi;
	}

	public void setArtikliUKorpi(Set<Artikal> artikliUKorpi) {
		this.artikliUKorpi = artikliUKorpi;
	}

	public Set<Korpa> getKorpe() {
		return korpe;
	}

	public void setKorpe(Set<Korpa> korpe) {
		this.korpe = korpe;
	}
	
	public void addKorpa(Korpa korpa){
		this.korpe.add(korpa);
		if(!this.equals(korpa.getKupac())){
			korpa.setKupac(this);
		}
	}
	
	// connections for many to many tables
	
	// ranije kupljeni
    public void addRanijeKupljeniArtikal(Artikal artikal) {
        this.ranijeKupljeniArtikli.add(artikal);
        artikal.getRanijeKupljeni().add(this);
    }
 
    public void removeRanijeKupljeniArtikal(Artikal artikal) {
    	this.ranijeKupljeniArtikli.remove(artikal);
    	artikal.getRanijeKupljeni().remove(this);
    }
    
    // omiljeni artikli
    public void addOmiljeniArtikal(Artikal artikal) {
        this.omiljeniArtikli.add(artikal);
        artikal.getKupciVoleArtikal().add(this);
    }
 
    public void removeOmiljeniArtikal(Artikal artikal) {
    	this.omiljeniArtikli.remove(artikal);
    	artikal.getKupciVoleArtikal().remove(this);
    }
    
    // artikli u korpi
    public void addArtikalUKorpi(Artikal artikal) {
        this.artikliUKorpi.add(artikal);
        artikal.getuKorpiKodKupca().add(this);
    }
 
    public void removeArtikalUKorpi(Artikal artikal) {
    	this.artikliUKorpi.remove(artikal);
    	artikal.getuKorpiKodKupca().remove(this);
    }
}
