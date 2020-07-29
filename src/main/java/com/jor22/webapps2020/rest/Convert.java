
package com.jor22.webapps2020.rest;

import com.jor22.webapps2020.entity.SystemUser;
import java.io.Serializable;
import javax.ejb.Singleton;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Singleton
public class Convert extends Application{

    @GET
    @Path("/GBP/{currency2}/{amount_of_currency1}")
    @Produces(MediaType.TEXT_XML)
    public Response convertcurrency(@PathParam("currency2") String cur2, @PathParam("amount_of_currency1") String amount){
        // all values stored in db as pounds
        // so all being converted from pounds to selected currency
        String convertCurrency = cur2;
        double convertAmount = Double.parseDouble(amount);
        
        if("USD".equals(convertCurrency)){
            convertAmount = convertAmount*1.23;
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else if("EUR".equals(convertCurrency)){
            convertAmount = convertAmount*1.14;
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else if("GBP".equals(convertCurrency)){
            // no conversion necessary
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else {
            //unsupported
            String output = "ERROR: unsupported currency";
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
    }
    
    @GET
    @Path("/{currency1}/GBP/{amount_of_currency1}")
    @Produces(MediaType.TEXT_XML)
    public Response convertcurrencyToPounds(@PathParam("currency1") String cur1, @PathParam("amount_of_currency1") String amount){
        // all values stored in db as pounds
        // so all being converted to pounds to store
        String convertCurrency = cur1;
        double convertAmount = Double.parseDouble(amount);
        
        if("USD".equals(convertCurrency)){
            convertAmount = convertAmount*0.81;
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else if("EUR".equals(convertCurrency)){
            convertAmount = convertAmount*0.88;
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else if("GBP".equals(convertCurrency)){
            // no conversion necessary
            String output = Double.toString(convertAmount);
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
        else {
            //unsupported
            String output = "ERROR: unsupported currency";
            return Response.ok(output, MediaType.TEXT_XML).build();
        }
    }
}
