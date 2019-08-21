package com.onlineshop.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlineshop.model.Korisnik;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	String ROLE_PREFIX = "ROLE_";
	private Korisnik korisnik;
	
	public UserDetailsImpl(Korisnik korisnik) {
		super();
		this.korisnik = korisnik;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singleton(new SimpleGrantedAuthority(ROLE_PREFIX + korisnik.getUloga()));
	}

	@Override
	public String getPassword() {
		return korisnik.getLozinka();
	}

	@Override
	public String getUsername() {
		return korisnik.getKorisnickoIme();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
