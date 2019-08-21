package com.onlineshop.dto;

public class ArtikalDTO {

	private Integer id;
	private String naziv;
	private String opis;
	private Double cena;
	private String imageUrl;
	private Integer popust;
	private Integer kategorijaId;
	private Double cenaSaPopustom;
	
	private String nazivKategorije;
	
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
	public Integer getKategorijaId() {
		return kategorijaId;
	}
	public void setKategorijaId(Integer kategorijaId) {
		this.kategorijaId = kategorijaId;
	}
	public String getNazivKategorije() {
		return nazivKategorije;
	}
	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
	}
	public Integer getPopust() {
		return popust;
	}
	public void setPopust(Integer popust) {
		this.popust = popust;
	}
	public Double getCenaSaPopustom() {
		return cenaSaPopustom;
	}
	public void setCenaSaPopustom(Double cenaSaPopustom) {
		this.cenaSaPopustom = cenaSaPopustom;
	}
	
}
