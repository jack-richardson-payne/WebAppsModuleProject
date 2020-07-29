
package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.rest.WebPageConverter;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

// class used to convert values using REST services
@Named("ConverterBean")
@RequestScoped
public class paymentBean {
    
    public static WebPageConverter conv = new WebPageConverter();

    public String convert(String currency, double val){
        System.out.println(currency);
        System.out.println(val);
        return conv.convertPound(currency, val);
        
    }
    
    public paymentBean() {
    }
    
    
    
}
