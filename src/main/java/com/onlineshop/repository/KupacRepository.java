package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Kupac;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Integer> {

	Kupac findByKorisnickoIme (String korisnickoIme);
}
