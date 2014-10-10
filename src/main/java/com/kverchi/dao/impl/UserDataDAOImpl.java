package com.kverchi.dao.impl;

import org.springframework.stereotype.Repository;

import com.kverchi.dao.GenericDAO;
import com.kverchi.dao.UserDataDAO;
import com.kverchi.domain.UserData;

@Repository
public class UserDataDAOImpl extends GenericDAOImpl<UserData> implements UserDataDAO {

}
