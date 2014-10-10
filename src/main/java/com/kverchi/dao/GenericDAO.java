package com.kverchi.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	void create(final T t);
    T getById(final Serializable id);
    void update(final T t);   
    void delete(final T t);
    List<T> getAllRecords();
}
