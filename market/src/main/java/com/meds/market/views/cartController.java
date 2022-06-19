package com.meds.market.views;



import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Cart;

import com.meds.market.model.Client;
import com.meds.market.services.ClientService;


@Controller
public class cartController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;

    @GetMapping("/cart")
    public ModelAndView cart(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email_client"))); //not sure qual é o nome do ciusi
      Client client = clientsv.getClientByEmail(clientmail);
      Cart cart = client.getCart();
  
      //prouct list
      

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("cart", cart);
      modelAndView.setViewName("shopping_cart");
      return modelAndView;
    }
    
}