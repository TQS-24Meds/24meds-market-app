package com.meds.market;

import com.meds.market.enums.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;
import com.meds.market.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	// paulo
	@Autowired
	CartService cartService;

	@Autowired
	ClientService clientService;

	@Autowired
	DeliveryService deliveryService;

	// mariana
	@Autowired
	PharmacyService pharmacyService;

	@Autowired
	ProductService productService;

	@Autowired
	PurchaseService purchaseService;

	@Override
	public void run(String... args) throws Exception {
		// asd
		if (clientService.getAllClients().isEmpty()) {
			Client c1 = new Client(
					"Zeca Carvalho",
					"user",
					"zeca@ua.pt",
					"123",
					"R. Dr. Mário Sacramento  ",
					981233123,
					new Coordinates(40.642493, -8.646368));
			clientService.registerClient(c1);
			Client c2 = new Client(
					"Beatriz Silva",
					"bia12",
					"silva@ua.pt",
					"12",
					"R. Dr. Mário Sacramento ",
					901321123,
					new Coordinates(40.642493, -8.626368));
			clientService.registerClient(c2);
			Client c3 = new Client(
					"Carlota Rosa",
					"rosa",
					"rosa@ua.pt",
					"1",
					"R. Dr. Mário Sacramento ",
					991239123,
					new Coordinates(40.632493, -8.646368));
			clientService.registerClient(c3);
		}

		if (pharmacyService.getAllPharmacys().isEmpty()) {
			Pharmacy pharmacy = new Pharmacy(
					"Farmacia Central",
					" Rua dos Mercadores 26 28, 3800-225 Aveiro",
					new Coordinates(40.642493, -8.646368));
			pharmacyService.registerPharmacy(pharmacy);
		}

		if (productService.getAllProducts().isEmpty()) {
			Product product1 = new Product(
					"Xeratop, 500 ml",
					"Correcaoo intensiva da pele seca, deslipidada e irritada",
					"../resources/static/assets/products/xeratop.png",
					21.56f,
					"Medinfar");
			Product product2 = new Product(
					"Zitrex, 60 caps",
					"O Zinco contribui para a proteção as células contra as oxidações indesejáveis e para a manutenção de uma pele, cabelos e unhas normais",
					"../resources/static/assets/products/Zitrex.jpg",
					15.50f,
					"Medinfar");
			Product product3 = new Product(
					"Trifeduo, 500mg",
					"Alívio temporário de dores ligeiras a moderadas associadas a enxaquecas, dores de cabeça, lombalgias, dores menstruais, dores de dentes e dores musculares, sintomas de constipação e gripe, dores de garganta e febre, em adultos com idade igual ou superior a 18 anos.",
					"../resources/static/assets/products/trifeduo.jpeg",
					6.1f,
					"Trifene");
			Product product4 = new Product(
					"Proton, 20 mg",
					"Utilizado em adultos para o tratamento de curto prazo dos sintomas de refluxo (por exemplo, azia e regurgitação ácida).",
					"../resources/static/assets/products/proton.jpg",
					8.20f,
					"MNSRM");
			Product product5 = new Product(
					"LIDOFON,, 20mg",
					"Está indicado para o tratamento dos sintomas da garganta inflamada, garganta irritada, distúrbios da faringe e boca acompanhados por irritação, tais como gengivas inflamadas, mucosas inflamadas e aftas.",
					"../resources/static/assets/products/lidofon.jgg",
					10.40f,
					"Medinfar");
			Product product6 = new Product(
					"Niacide, 20mg",
					"Coadjuvante do Tratamento da Acne",
					"../resources/static/assets/products/nadiclox.png",
					5.40f,
					"MNSRM");
			Product product7 = new Product(
					"Paramolan, 24mg",
					"Tratamento de dores ligeiras a moderadas, dores de garganta (excluindo" +
							"amigdalites), cefaleias (dores de cabeça) ligeiras a moderadas. Está" +
							"igualmente indicado no tratamento da febre de duração não superior a 3" +
							"dias, e no tratamento sintomático de síndromes gripais e constipações.",
					"../resources/static/assets/products/paramolan.png",
					7.40f,
					"Medinfar");
			Product product8 = new Product(
					"Sterispray",
					"Desinfetante das mãos para utilização frequente, garantindo a higiene e suavidade, sem agredir a pele.",
					"../resources/static/assets/products/sterispray.png",
					3.5f,
					"Medinfar");

			Product product9 = new Product(
					"Cicloviral",
					"Tratamento do herpes labial enquanto lesão resultante da infeção por vírus Herpes Simplex tipo.",
					"../resources/static/assets/products/cicloviral.png",
					10.5f,
					"Medinfar");
			Product product10 = new Product(
					"Maxilase",
					"Tratamento eficaz da dor de ganta",
					"../resources/static/assets/products/maxilase.jpg",
					7.2f,
					"Medinfar");

			productService.registerProduct(product1);
			productService.registerProduct(product2);
			productService.registerProduct(product3);
			productService.registerProduct(product4);
			productService.registerProduct(product5);
			productService.registerProduct(product6);
			productService.registerProduct(product7);
			productService.registerProduct(product8);
			productService.registerProduct(product9);
			productService.registerProduct(product10);

		}
	}
}