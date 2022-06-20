package com.meds.market.views;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Product;
import com.meds.market.services.ProductService;

import com.meds.market.model.Client;
import com.meds.market.services.ClientService;

import com.meds.market.model.Cart;
import com.meds.market.services.CartService;


@Controller
public class ProductController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;


    @Autowired 
    ProductService productService;
    @Autowired 
    CartService cartsv;


    @GetMapping("/product/{productid}")
    public ModelAndView getProductById(@PathVariable(value="productid") int productid, Model model) throws NumberFormatException, ResourceNotFoundException {
      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email"))); //not sure qual é o nome do ciusi
      Client client = clientsv.getClientByEmail(clientmail);
      Product product = productService.getProduct(productid);
      int product_id = product.getId();
      System.out.println("Product");
      Cart cart = client.getCart();



      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("product", product);
      modelAndView.addObject("id", product_id);

      modelAndView.setViewName("product");
      return modelAndView;
    }

    @GetMapping("/product/{productid}/description")
    public ModelAndView getProductDescriptionById(@PathVariable(value="productId") int productid, Model model) throws NumberFormatException, ResourceNotFoundException {
        HttpSession session = httpSessionFactory.getObject();
        String clientmail= (String.valueOf(session.getAttribute("email_client"))); //not sure qual é o nome do ciusi
        Client client = clientsv.getClientByEmail(clientmail);
      Product product = productService.getProduct(productid);
      model.addAttribute("name",product.getName());
      model.addAttribute("brand",product.getBrand());
      model.addAttribute("description",product.getDescription());
      model.addAttribute("photo",product.getPhoto());
      model.addAttribute("price",product.getPrice());
      model.addAttribute("tags",product.getTags());
      
  
          Cart cart = client.getCart();
  
  
  
        ModelAndView modelAndView = new ModelAndView();
  
        modelAndView.setViewName("product_description");
        return modelAndView;
      }
  




      /*
       * Metodo q falei acima
       * 
       *   @GetMapping("/api/internados/{pessoacc}")
  public @ResponseBody Map<Integer,List<Object>>getInternamentosId(@PathVariable int pessoacc) throws ResourceNotFoundException {
    Paciente pac = pessoaService.getPessoaBycc(pessoacc).getPaciente();
    int pacid= pac.getId();

    Internamento inter = internamentoService.getInternamentoById(pacid);
    Paciente npac= inter.getPaciente();

    List<Object> a = new ArrayList<Object>(); 
    a.add(inter);
    a.add(npac);
    Map<Integer,List<Object>> b =new HashMap<Integer,List<Object>>();
    b.put(pessoacc,a);
    return b;
  }
       * 
       */
}