package com.meds.market.views;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Client;
import com.meds.market.model.Product;
import com.meds.market.services.ClientService;
import com.meds.market.services.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class IndexController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;
      
    @Autowired 
    ProductService productService;


    @GetMapping("/index")
    public ModelAndView index(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email"))); //not sure qual é o nome do ciusi
      Client client = clientsv.getClientByEmail(clientmail);

      model.addAttribute("id_client", client.getId());

      List<Product> products = productService.getAllProducts();
      
      model.addAttribute("id_client", client.getId());
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("products", products);
      session.setAttribute("products", products);
      modelAndView.setViewName("index");
      return modelAndView;
    }
    
}