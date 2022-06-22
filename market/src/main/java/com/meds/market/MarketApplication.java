package com.meds.market;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.meds.market.model.*;
import com.meds.market.repository.*;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
}

@Profile("!test")
@Component
class DBLoader implements CommandLineRunner {

	@Autowired CartRepository cartRepository;
	
	@Autowired ClientRepository clientRepository;
	
	@Autowired DeliveryRepository deliveryRepository;
	
	@Autowired PharmacyRepository pharmacyRepository;
	
	@Autowired ProductRepository productRepository;
	
	@Autowired PurchaseRepository purchaseRepository;

	@Autowired CoordinatesRepository coordinatesRepository;

	public void run(String... args) throws Exception {

		//if (clientService.getAllClients().isEmpty()) {
		Coordinates c1Coordinates = new Coordinates(40.642493, -8.646368);
		
		Coordinates c2Coordinates = new Coordinates(40.642493, -8.626368);
		
		Coordinates c3Coordinates = new Coordinates(40.632493, -8.646368);
			
		coordinatesRepository.save(c1Coordinates);
		coordinatesRepository.save(c2Coordinates);
		coordinatesRepository.save(c3Coordinates);

		Client c1 = new Client(
				"Zeca Carvalho",
				"user",
				"zeca@ua.pt",
				"123145847585",
				"R. Dr. Mário Sacramento  ",
				981233123,
				c1Coordinates);
				
		Client c2 = new Client(
				"Beatriz Silva",
				"bia12",
				"silva@ua.pt",
				"123145847585",
				"R. Dr. Mário Sacramento ",
				901321123,
				c2Coordinates);
				
		Client c3 = new Client(
					"Carlota Rosa",
				"rosa",
				"rosa@ua.pt",
				"123145847585",
				"R. Dr. Mário Sacramento ",
				991239123,
				c3Coordinates);

		clientRepository.saveAndFlush(c1);
		clientRepository.saveAndFlush(c2);
		clientRepository.saveAndFlush(c3);

		//}
		
		Coordinates storeCoordinates = new Coordinates(40.642493, -8.646368);
		coordinatesRepository.saveAndFlush(storeCoordinates);
		
		//if (pharmacyRepository.saveAndFlush().isEmpty()) {
		Pharmacy pharmacy = new Pharmacy(
				"Farmacia Central",
				" Rua dos Mercadores 26 28, 3800-225 Aveiro",
				storeCoordinates);
		pharmacyRepository.saveAndFlush(pharmacy);
		//}
		coordinatesRepository.flush();

		//if (productRepository.saveAndFlush().isEmpty()) {
		Product product1 = new Product(
				"Xeratop",
				"Correcao intensiva da pele seca, deslipidada e irritada",
				"../static/img/products/assets/products/xeratop.png",
				21.56f,
				"Medinfar");
		Product product2 = new Product(
				"Zitrex",
				"O Zinco contribui para a proteção as células contra as oxidações indesejáveis e para a manutenção de uma pele, cabelos e unhas normais",
				"../static/img/products/assets/products/Zitrex.jpg",
				15.50f,
				"Medinfar");
		Product product3 = new Product(
				"Trifeduo",
				"Alívio temporário de dores ligeiras a moderadas associadas a enxaquecas, dores de cabeça.",
				"../static/img/products/assets/products/trifeduo.jpeg",
				6.1f,
				"Trifene");
		Product product4 = new Product(
				"Proton, 20 mg",
				"Utilizado em adultos para o tratamento de curto prazo dos sintomas de refluxo (por exemplo, azia e regurgitação ácida).",
				"../static/img/products/assets/products/proton.jpg",
				8.20f,
				"MNSRM");
		Product product5 = new Product(
				"LIDOFON",
				"Está indicado para o tratamento dos sintomas da garganta inflamada.",
				"../static/img/products/assets/products/lidofon.jgg",
				10.40f,
				"Medinfar");
		Product product6 = new Product(
				"Niacide",
				"Coadjuvante do Tratamento da Acne",
				"../static/img/products/assets/products/nadiclox.png",
				5.40f,
				"MNSRM");
		Product product7 = new Product(
				"Paramolan",
				"Tratamento de dores ligeiras a moderadas, dores de garganta",
				"../static/img/products/assets/products/paramolan.png",
				7.40f,
				"Medinfar");
		Product product8 = new Product(
				"Sterispray",
				"Desinfetante das mãos para utilização frequente, garantindo a higiene e suavidade, sem agredir a pele.",
				"../static/img/products/assets/products/sterispray.png",
				3.5f,
				"Medinfar");

		Product product9 = new Product(
				"Cicloviral",
				"Tratamento do herpes labial enquanto lesão resultante da infeção por vírus Herpes Simplex tipo.",
				"../static/img/products/assets/products/cicloviral.png",
				10.5f,
				"Medinfar");
		Product product10 = new Product(
				"Maxilase",
				"Tratamento eficaz da dor de ganta",
				"../static/img/products/assets/products/maxilase.jpg",
				7.2f,
				"Medinfar");

		productRepository.saveAndFlush(product1);
		productRepository.saveAndFlush(product2);
		productRepository.saveAndFlush(product3);
		productRepository.saveAndFlush(product4);
		productRepository.saveAndFlush(product5);
		productRepository.saveAndFlush(product6);
		productRepository.saveAndFlush(product7);
		productRepository.saveAndFlush(product8);
		productRepository.saveAndFlush(product9);
		productRepository.saveAndFlush(product10);
		//}
	}
}