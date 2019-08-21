package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Dostavljac;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Integer> {

}
