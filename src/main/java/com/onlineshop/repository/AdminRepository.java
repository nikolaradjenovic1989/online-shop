package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
