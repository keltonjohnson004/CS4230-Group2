package edu.weber.group2.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.weber.group2.cms.contact.ContactRepository;
import edu.weber.group2.cms.contact.ContactService;

@Configuration
@Scope
public class WebConfig 
{
	@Bean
	public ContactService contactService(ContactRepository contactRepository)
	{
		ContactService service = new  ContactService(contactRepository);
		return service;
	}
	
	@Bean
	public ContactRepository contactRepository()
	{
		return new ContactRepository();
	}
}
