package com.meds.market.views;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.meds.market.exception.ResourceNotFoundException;

import com.meds.market.model.Cart;
import com.meds.market.model.CartStock;
import com.meds.market.model.Client;
import com.meds.market.model.Product;
import com.meds.market.services.ClientService;
import com.meds.market.services.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CartController {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired 
    ClientService clientsv;

    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public ModelAndView cart(Model model) throws NumberFormatException, ResourceNotFoundException {

      HttpSession session = httpSessionFactory.getObject();
      String clientmail= (String.valueOf(session.getAttribute("email"))); 
      Client client = clientsv.getClientByEmail(clientmail);
      Cart cart = client.getCart(); 

      log.info("CART: " + cart);

      ModelAndView modelAndView = new ModelAndView();
      session.setAttribute("cart", cart);
      modelAndView.setViewName("cart");
      return modelAndView;
    }
    
    /* @PostMapping("/cart")
    public void cartPost(@RequestParam(value = "productName", required = false) String productName, @RequestParam(value = "productAmount", required = false) int productAmount) {
      HttpSession session = httpSessionFactory.getObject();
      if (productName != null) {
        Product product = productService.getProductByName(productName);
        Cart cart = (Cart) session.getAttribute("cart");
        CartStock cartStock = new CartStock(product, productAmount);
        List<CartStock> stocks = new ArrayList<>();
        stocks.add(cartStock);
        cart.setCartStocks(stocks);
        log.info("is this okay? " + cart);
      }
    }  */
    
}