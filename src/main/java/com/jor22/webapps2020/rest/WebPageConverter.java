
package com.jor22.webapps2020.rest;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class WebPageConverter {

    public WebPageConverter() {
    }
    
    public String convertPound(String currency, double amount){
        String amountString = Double.toString(amount);
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:10000/webapps2020/conversion").path("GBP").path(currency).path(amountString).request(MediaType.TEXT_XML).get(Response.class);
   
        return response.readEntity(String.class);
    }
    
    public String toPound(String currency, double amount){
        String amountString = Double.toString(amount);
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:10000/webapps2020/conversion").path(currency).path("GBP").path(amountString).request(MediaType.TEXT_XML).get(Response.class);
   
        return response.readEntity(String.class);
    }
    
}
