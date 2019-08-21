package com.onlineshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="kategorija")
public class Kategorija implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String naziv;
	
	@OneToMany(mappedBy="kategorija", cascade=CascadeType.ALL)
	private List<Artikal> artikli = new ArrayList<Artikal>();

	public Kategorija() {}
	
	public Kategorija(String naziv) {
		this.naziv = naziv;
	}

	public Kategorija(Integer id, String naziv) {
		this.id = id;
		this.naziv = naziv;
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

	public List<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}
	
	public void addArtikal(Artikal artikal){
		this.artikli.add(artikal);
		if(!this.equals(artikal.getKategorija())){
			artikal.setKategorija(this);
		}
	}
	
}
