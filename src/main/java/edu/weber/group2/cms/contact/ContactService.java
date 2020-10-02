package edu.weber.group2.cms.contact;

import org.springframework.stereotype.Service;

@Service
public class ContactService {

	private ContactRepository contactRepo;
	
	public ContactService(ContactRepository contactRepo)
	{
		this.contactRepo = contactRepo;
	}
	
	public Contact getContactById(String id)
	{
		return new Contact();
	}
}
