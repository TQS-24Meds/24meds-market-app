package com.meds.market.views;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Client;
import com.meds.market.services.ClientService;


@Controller
public class ProfController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;

    @GetMapping("/profile")
    public ModelAndView profile(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email")));
      Client client = clientsv.getClientByEmail(clientmail);
  

        model.addAttribute("email", client.getEmail()); 
        model.addAttribute("address", client.getAddress()); 
        model.addAttribute("phone", client.getPhone()); 
        model.addAttribute("name", client.getName()); 
        model.addAttribute("username", client.getUsername()); 
   

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("profile");
      return modelAndView;
    }
    
}