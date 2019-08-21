package com.onlineshop.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class KorpaDTO {

	private Integer id;
	private Date datumIVreme;
	private Integer kupacId;
	private Integer dostavljacId;
	private String status;
	
	private String nazivKupca;
	private String adresaKupca;
	
	private Double ukupnaCena;
	
	private List<Integer> artikliUKorpiId = new ArrayList<Integer>();
	
	
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
	public Integer getKupacId() {
		return kupacId;
	}
	public void setKupacId(Integer kupacId) {
		this.kupacId = kupacId;
	}
	public Integer getDostavljacId() {
		return dostavljacId;
	}
	public void setDostavljacId(Integer dostavljacId) {
		this.dostavljacId = dostavljacId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Integer> getArtikliUKorpiId() {
		return artikliUKorpiId;
	}
	public void setArtikliUKorpiId(List<Integer> artikliUKorpiId) {
		this.artikliUKorpiId = artikliUKorpiId;
	}
	public String getNazivKupca() {
		return nazivKupca;
	}
	public void setNazivKupca(String nazivKupca) {
		this.nazivKupca = nazivKupca;
	}
	public String getAdresaKupca() {
		return adresaKupca;
	}
	public void setAdresaKupca(String adresaKupca) {
		this.adresaKupca = adresaKupca;
	}
	public Double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(Double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

}
