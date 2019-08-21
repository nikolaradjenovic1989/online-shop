package com.onlineshop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="artikal")
public class Artikal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true, name="naziv") // name cannot be null and must be unique
	private String naziv;
	
	@Column(name="opis")
	private String opis;
	
	@Column(nullable=false, name="cena")
	private Double cena;
	
	@Column
	private String imageUrl;
	
	@Column
	private Integer popust;
	
	@Column
	private Double cenaSaPopustom;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Kategorija kategorija;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "artikli_u_korpi",
			joinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "korpa_id", referencedColumnName = "id"))
    private Set<Korpa> korpe = new HashSet<Korpa>();
    
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "ranije_kupljeni",
			joinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "kupac_id", referencedColumnName = "id"))
	private Set<Kupac> ranijeKupljeni = new HashSet<Kupac>();
	
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "omiljeni_artikli",
    		joinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(name = "kupac_id", referencedColumnName = "id"))
    private Set<Kupac> kupciVoleArtikal = new HashSet<Kupac>();
    
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="u_korpi_kupac",
				joinColumns = @JoinColumn(name="artikal_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn(name="kupac_id", referencedColumnName="id"))
    private Set<Kupac> uKorpiKodKupca = new HashSet<Kupac>();

	public Artikal() {}
	
	public Artikal(String naziv, String opis, Double cena, Kategorija kategorija) {
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.kategorija = kategorija;
	}

	public Artikal(Integer id, String naziv, String opis, Double cena, Kategorija kategorija) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.kategorija = kategorija;
	}
	
	public Double vratiCenuSaPopustom() {
		if(popust == null) {
			return this.cena; // if there is no discount return regular price
		}
		this.cenaSaPopustom = this.cena * (1 - (this.popust / 100.0));
		return Math.round(this.cenaSaPopustom * 100.0) / 100.0; // round on 2 decimals max
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
		if(!kategorija.getArtikli().contains(this)){
			kategorija.getArtikli().add(this);
		}
	}

	public Set<Korpa> getKorpe() {
		return korpe;
	}

	public void setKorpe(Set<Korpa> korpe) {
		this.korpe = korpe;
	}

	public Set<Kupac> getKupciVoleArtikal() {
		return kupciVoleArtikal;
	}

	public void setKupciVoleArtikal(Set<Kupac> kupciVoleArtikal) {
		this.kupciVoleArtikal = kupciVoleArtikal;
	}

	public Integer getPopust() {
		return popust;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	public Set<Kupac> getuKorpiKodKupca() {
		return uKorpiKodKupca;
	}

	public void setuKorpiKodKupca(Set<Kupac> uKorpiKodKupca) {
		this.uKorpiKodKupca = uKorpiKodKupca;
	}
	
	public Double getCenaSaPopustom() {
		return vratiCenuSaPopustom(); // call our custom method
	}

	public void setCenaSaPopustom(Double cenaSaPopustom) {
		this.cenaSaPopustom = cenaSaPopustom;
	}

	public Set<Kupac> getRanijeKupljeni() {
		return ranijeKupljeni;
	}

	public void setRanijeKupljeni(Set<Kupac> ranijeKupljeni) {
		this.ranijeKupljeni = ranijeKupljeni;
	}

	// for debugging
	@Override
	public String toString() {
		return "Artikal [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + ", imageUrl=" + imageUrl
				+ ", kategorija=" + kategorija + "]";
	}
	
	// two way data binding for many to many table
	
	// korpe
    public void addKorpa(Korpa korpa) {
        this.korpe.add(korpa);
        korpa.getKupljeniArtikli().add(this);
    }
 
    public void removeKorpa(Korpa korpa) {
    	this.korpe.remove(korpa);
    	korpa.getKupljeniArtikli().remove(this);
    }
    
    // ranije kupljeni
    public void addRanijeKupljeni(Kupac kupac) {
        this.ranijeKupljeni.add(kupac);
        kupac.getRanijeKupljeniArtikli().add(this);
    }
 
    public void removeRanijeKupljeni(Kupac kupac) {
    	this.ranijeKupljeni.remove(kupac);
    	kupac.getRanijeKupljeniArtikli().remove(this);
    }
    
    // kupci vole artikal
    public void addKupciVoleArtikal(Kupac kupac) {
        this.kupciVoleArtikal.add(kupac);
        kupac.getOmiljeniArtikli().add(this);
    }
 
    public void removeKupciVoleArtikal(Kupac kupac) {
    	this.kupciVoleArtikal.remove(kupac);
    	kupac.getOmiljeniArtikli().remove(this);
    }
    
    // u korpi kod kupca
    public void addUKorpiKodKupca(Kupac kupac) {
        this.uKorpiKodKupca.add(kupac);
        kupac.getArtikliUKorpi().add(this);
    }
 
    public void removeUKorpiKodKupca(Kupac kupac) {
    	this.uKorpiKodKupca.remove(kupac);
    	kupac.getArtikliUKorpi().remove(this);
    }
}
