package com.kverchi.dao;

import com.kverchi.domain.NemailAddresse;

public interface NemailAddresseDAO extends GenericDAO<NemailAddresse> {
	public NemailAddresse getEmailData(String address);
}
