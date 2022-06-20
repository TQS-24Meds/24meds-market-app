package com.meds.market.views;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Client;
import com.meds.market.model.LoginCredentials;
import com.meds.market.services.ClientService;


@Controller
public class loginController {

  @ModelAttribute("loginCredentials")
  public LoginCredentials loginCredentialsAttribute() {
    return new LoginCredentials();
  }
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;
    

    @GetMapping("/login")
    public ModelAndView login(Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email_client"))); //not sure qual é o nome do ciusi
  

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("login");
      return modelAndView;
    }

  @PostMapping("/")
  public RedirectView loginChecking(@ModelAttribute LoginCredentials loginCredentials, Model model) throws NumberFormatException, ResourceNotFoundException {
    HttpSession session = httpSessionFactory.getObject();
    String clientmail = loginCredentials.getclientmail();
    String password = loginCredentials.getPassword();
    Client client = clientsv.getClientByEmail(clientmail);

    if (client.getPassword().equals(password)) {
    session.setAttribute("email_client", clientmail);
    session.setAttribute("id_client",client.getId());
    System.out.println("ratisse");
    return new RedirectView("index");
  }
  return new RedirectView("login");
    

}
}








