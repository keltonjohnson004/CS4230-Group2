package edu.weber.group2.cms.contact;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ContactRepository()
	{
		//this.jdbcTemplate = jdbcTemplate;
	}
	
	public Contact getContactById(String id)
	{
		return new Contact();
	}
}
