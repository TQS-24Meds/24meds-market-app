package com.meds.market.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Client;
import com.meds.market.model.LoginCredentials;
import com.meds.market.services.ClientService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class loginController {

  @Autowired
  ObjectFactory<HttpSession> httpSessionFactory;

  @Autowired
  ClientService clientsv;


  
  @ModelAttribute("loginCredentials")
  public LoginCredentials loginCredentialsAttribute() {
    return new LoginCredentials();

  }

  @GetMapping("/")
  @ResponseBody
  public RedirectView login(HttpServletRequest request, Model model) {
    if (request.getSession().getAttribute("id_client") == null) {
      return new RedirectView("login");
    }
    return new RedirectView("index");
  }

  @GetMapping("/login")
  public ModelAndView viewLogin(Model model) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return modelAndView;
  }

  @PostMapping("/")
  public RedirectView loginChecking(@ModelAttribute LoginCredentials loginCredentials, Model model)
      throws NumberFormatException, ResourceNotFoundException {
    HttpSession session = httpSessionFactory.getObject();
    log.info("SESSION : "+  session);
    String email = loginCredentials.getEmail();
    log.info("email " + email);
    String password = loginCredentials.getPassword();
    Client client = clientsv.getClientByEmail(email);
    log.info("ID::::::::::" + client.getId());
    log.info("Client::::::::::" + client.getEmail());
    log.info("Client::::::::::" + client.getPassword());
    log.info("password introduzida::::::::::" + password);
    log.info("são iguais?"  + password.equals(client.getPassword()));


    if (client.getPassword().equals(password)) {
      session.setAttribute("email", email);
      session.setAttribute("id_client", client.getId());
      System.out.println("ratisse");
      return new RedirectView("index");
    }
    else{
      return new RedirectView("login");
    }

  }
}
