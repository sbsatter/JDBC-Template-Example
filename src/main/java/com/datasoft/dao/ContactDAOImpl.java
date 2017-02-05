package com.datasoft.dao;

import com.datasoft.model.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sbsatter on 2/5/17.
 */
public class ContactDAOImpl implements ContactDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveOrUpdate(Contact contact){
		if (contact.getId() > 0) {
			String query = "Update Contacts set name=?, email=?, address=?, phone=? where id=?";
			jdbcTemplate.update(query, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getPhone(), contact.getId());
		}else{
			String query = "Insert into Contacts( name, email, address, phone) values(?,?,?,?)";
			jdbcTemplate.update(query, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getPhone());
		}
	}
	@Override
	public void delete(int contactId){
		String query = "Delete from contacts where id = ?";
		jdbcTemplate.update(query, contactId);
	}
	@Override
	public List<Contact> list(){
		String query = "Select * from Contacts";
		List<Contact> contactsList = jdbcTemplate.query(query, new RowMapper<Contact>() {
			@Override
			public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
				Contact contact = new Contact();
				contact.setName(resultSet.getString("name"));
				contact.setAddress(resultSet.getString("address"));
				contact.setEmail(resultSet.getString("Email"));
				contact.setPhone(resultSet.getString("phone"));
				contact.setId(resultSet.getInt("id"));
				return contact;
			}
		});
		return contactsList;
	}
	@Override
	public Contact get(int contactId){
		String query = "Select * from Contacts where id = ?";
		return jdbcTemplate.query(query, new ResultSetExtractor<Contact>() {
			
			@Override
			public Contact extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				if (resultSet.next()){
					Contact contact = new Contact();
					contact.setId(resultSet.getInt("id"));
					contact.setName(resultSet.getString("name"));
					contact.setEmail(resultSet.getString("email"));
					contact.setAddress(resultSet.getString("address"));
					contact.setPhone(resultSet.getString("phone"));
					return contact;
				}
				return null;
			}
		});
	}
}