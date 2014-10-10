package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;

@Repository
public class CountryDAOImpl extends GenericDAOImpl<Country> implements CountryDAO {

	
}
