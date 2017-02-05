package com.datasoft.dao;

import com.datasoft.model.Contact;

import java.util.List;

/**
 * Created by sbsatter on 2/5/17.
 */
public interface ContactDAO {
	void saveOrUpdate(Contact contact);
	
	void delete(int contactId);
	
	List<Contact> list();
	
	Contact get(int contactId);
}
