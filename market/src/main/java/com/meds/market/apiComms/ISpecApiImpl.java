package com.meds.market.apiComms;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.meds.market.exception.ResourceNotFoundException;
import java.util.Objects;

import com.meds.market.model.Delivery;
import com.meds.market.model.ServerPurchaseModel;



@Log4j2
@Component
public class ISpecApiImpl implements ISpecApi {

    @Autowired
    public RestTemplate restTemplate() { return new RestTemplateBuilder().build(); }

    private RestTemplate restTemplate;

    private static final String HOST = "http://localhost:8080/store";
    private static final String TOKEN = "";

    private HttpHeaders headers;

    public ISpecApiImpl() {

        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", "Bearer " + TOKEN);
    }

    @Override
    public Long newOrder(Delivery purchase) {

        StringBuilder url = new StringBuilder().append(HOST)
                .append("/order");

        ResponseEntity<ServerPurchaseModel> response = restTemplate.exchange(
                url.toString(), HttpMethod.POST, new HttpEntity<>(purchase, headers),
                ServerPurchaseModel.class);
        
        try {
            return Objects.requireNonNull(response.getBody()).getOrderId();

        } catch (NullPointerException e) {

            log.error("ISpecApiImpl: Null serverOrderID ");
            throw new ResourceNotFoundException("Null serverOrderID");

        }
    
    }
}
