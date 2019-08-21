package com.onlineshop.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.dto.DostavljacDTO;
import com.onlineshop.dto.KorisnikDTO;
import com.onlineshop.dto.KorpaDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Dostavljac;
import com.onlineshop.model.Korisnik;
import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.service.DostavljacService;
import com.onlineshop.service.KorisnikService;
import com.onlineshop.service.KorpaService;
import com.onlineshop.support.ArtikalDTOToArtikal;
import com.onlineshop.support.ArtikalToArtikalDTO;
import com.onlineshop.support.DostavljacDTOToDostavljac;
import com.onlineshop.support.DostavljacToDostavljacDTO;
import com.onlineshop.support.KorisnikToKorisnikDTO;
import com.onlineshop.support.KorpaToKorpaDTO;

@RestController
@RequestMapping(value="/api/admin")
public class AdminController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikToKorisnikDTO toKorisnikDTO;
	
	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private ArtikalToArtikalDTO toArtikalDTO;
	
	@Autowired
	private ArtikalDTOToArtikal toArtikal;
	
	@Autowired
	private DostavljacService dostavljacService;
	
	@Autowired
	private KorpaService korpaService;
	
	@Autowired
	private DostavljacDTOToDostavljac toDostavljac;
	
	@Autowired
	private DostavljacToDostavljacDTO toDostavljacDTO;
	
	@Autowired
	private KorpaToKorpaDTO toKorpaDTO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityManager entityManager;
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ArtikalDTO> obrisiArtikal(@PathVariable Integer id) {
		
		Artikal artikal = artikalService.findOne(id);
		
		if(artikal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		artikalService.delete(id);
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikal), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<ArtikalDTO> izmeniArtikal(
			@RequestBody ArtikalDTO artikalDTO,
			@PathVariable Integer id){
		
		if(!id.equals(artikalDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Artikal persisted = artikalService.save(toArtikal.convert(artikalDTO));
		
		return new ResponseEntity<>(toArtikalDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajArtikal", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ArtikalDTO> dodajArtikal(@RequestBody ArtikalDTO newArtikalDTO){

		Artikal saved = artikalService.save(toArtikal.convert(newArtikalDTO));

		return new ResponseEntity<>(toArtikalDTO.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/korisnici", method=RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnici() {
		
		List<Korisnik> korisnici = korisnikService.findAll();
		
		return new ResponseEntity<>(toKorisnikDTO.convert(korisnici), HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="/snimanjeUloge", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<Void> izmenaUloge(@RequestBody KorisnikDTO korisnikDTO){
		
		Korisnik korisnik = (Korisnik) korisnikService.findOneById(korisnikDTO.getId());
		
		Integer vrednost = null;
		if(korisnikDTO.getUloga().equals("KUPAC")) {
			vrednost = 1;
		} else if (korisnikDTO.getUloga().equals("DOSTAVLJAC")) {
			vrednost = 2;
		} else {
			vrednost = 3;
		}
		
		// moguće je promeniti discriminator value odnosno tip korisnika samo preko native query-ja
		entityManager.createNativeQuery("UPDATE KORISNIK SET TIP_KORISNIKA = ? WHERE ID = ?")
		.setParameter(1, vrednost)
		.setParameter(2, korisnik.getId())
		.executeUpdate();
	
		korisnik.setUloga(korisnikDTO.getUloga());
		korisnikService.save(korisnik);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajDostavljaca", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<DostavljacDTO> dodajDostavljaca(@RequestBody DostavljacDTO newDostavljacDTO){

		newDostavljacDTO.setLozinka(passwordEncoder.encode(newDostavljacDTO.getLozinka()));
		Dostavljac saved = dostavljacService.save(toDostavljac.convert(newDostavljacDTO));

		return new ResponseEntity<>(toDostavljacDTO.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/izmeniDostavljaca/{id}", consumes="application/json")
	public ResponseEntity<DostavljacDTO> izmeniDostavljaca(
			@RequestBody DostavljacDTO dostavljacDTO,
			@PathVariable Integer id){
		
		if(!id.equals(dostavljacDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		dostavljacDTO.setLozinka(passwordEncoder.encode(dostavljacDTO.getLozinka()));
		Dostavljac persisted = dostavljacService.save(toDostavljac.convert(dostavljacDTO));
		
		return new ResponseEntity<>(toDostavljacDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getDostavljac/{id}", method=RequestMethod.GET)
	public ResponseEntity<DostavljacDTO> getDostavljac(@PathVariable Integer id){
		Dostavljac dostavljac = dostavljacService.findOne(id);
		
		if(dostavljac==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDostavljacDTO.convert(dostavljac), HttpStatus.OK);
	}
	
	@RequestMapping(value="/dostavljac/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<DostavljacDTO> obrisiDostavljaca(@PathVariable Integer id) {
		
		Dostavljac dostavljac = dostavljacService.findOne(id);
		
		if(dostavljac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		/* ne mozemo izbrisati korpu zajedno sa dostavljačem, ako dostavljač više ne postoji onda
		 * korpa ostaje kao porudžbina bez dostavljača sa statusom kupljeno što znači da je slobodna za novo preuzimanje
		 */
		for(int i=0; i < dostavljac.getListaPorudzbina().size(); i++) {
			dostavljac.getListaPorudzbina().get(i).setDostavljac(null);
			dostavljac.getListaPorudzbina().get(i).setStatus(Status.KUPLJENO);
		}
		dostavljac.getListaPorudzbina().clear();
		
		dostavljacService.delete(id);
		
		return new ResponseEntity<>(toDostavljacDTO.convert(dostavljac), HttpStatus.OK);
	}
	
	@RequestMapping(value="/izvestajZaDan", method=RequestMethod.GET)
	public ResponseEntity<List<KorpaDTO>> kreirajIzvestaj(@RequestParam(required=true) String datum) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDatum = null;
		try {
			sqlDatum = new Date(sdf1.parse(datum).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Korpa> korpe = korpaService.findByDatumIVreme(sqlDatum);
		
		return new ResponseEntity<>(toKorpaDTO.convert(korpe), HttpStatus.OK);
	}
	
}
