package com.datasoft.controller;

import com.datasoft.dao.ContactDAO;
import com.datasoft.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public  class HomeController {
	
	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping(value="/")
	public ModelAndView getContactsList(ModelAndView model) {
		List<Contact> contactsList = contactDAO.list();
		model.addObject(contactsList);
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value="/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model){
		Contact contact = new Contact();
		model.addObject(contact);
		model.setViewName("ContactForm");
		
		return model;
	}
	
	@RequestMapping(value="/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactDAO.saveOrUpdate(contact);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/deleteContact", method = RequestMethod.POST)
	public ModelAndView deleteContact(HttpServletRequest request){
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject(contact);
		
		return model;
	}
}