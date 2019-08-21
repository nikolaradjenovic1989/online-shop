package com.onlineshop.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="korpa")
public class Korpa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date datumIVreme;
	
    @ManyToMany(mappedBy = "korpe")
	private Set<Artikal> kupljeniArtikli = new HashSet<Artikal>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Kupac kupac;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Dostavljac dostavljac;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Korpa() {}

	public Korpa(Date datumIVreme, Set<Artikal> kupljeniArtikli, Kupac kupac, Dostavljac dostavljac, Status status) {
		super();
		this.datumIVreme = datumIVreme;
		this.kupljeniArtikli = kupljeniArtikli;
		this.kupac = kupac;
		this.dostavljac = dostavljac;
		this.status = status;
	}

	public Korpa(Integer id, Date datumIVreme, Set<Artikal> kupljeniArtikli, Kupac kupac, Dostavljac dostavljac,
			Status status) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.kupljeniArtikli = kupljeniArtikli;
		this.kupac = kupac;
		this.dostavljac = dostavljac;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(Date datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Set<Artikal> getKupljeniArtikli() {
		return kupljeniArtikli;
	}

	public void setKupljeniArtikli(Set<Artikal> kupljeniArtikli) {
		this.kupljeniArtikli = kupljeniArtikli;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
		if(!kupac.getKorpe().contains(this)){
			kupac.getKorpe().add(this);
		}
	}

	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	// two way data binding for many to many table
    public void addKupljeniArtikal(Artikal artikal) {
        this.kupljeniArtikli.add(artikal);
        artikal.getKorpe().add(this);
    }
 
    public void removeKupljeniArtikal(Artikal artikal) {
    	this.kupljeniArtikli.remove(artikal);
    	artikal.getKorpe().remove(this);
    }
	
}
