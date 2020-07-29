
package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.ejb.TransactionExecutor;
import com.jor22.webapps2020.ejb.requestService;
import com.jor22.webapps2020.entity.SystemUser;
import static com.jor22.webapps2020.jsf.TransactionBean.conv;
import com.jor22.webapps2020.rest.WebPageConverter;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Lenovo
 */
@Named(value = "requestBean")
@RequestScoped
public class RequestBean {
    
    @EJB 
    requestService req;
    double amount;
    String username; 
    String result;
    // used to get session variables
    FacesContext context = FacesContext.getCurrentInstance();
    SystemUser use = (SystemUser) context.getExternalContext().getSessionMap().get("user");
    // Rest service
    public static WebPageConverter conv = new WebPageConverter();

    public RequestBean() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String request(){
        // convert amount to pounds 
        amount = Double.valueOf(conv.toPound(use.getCurrency(), amount));
        // call request ejb
        result = req.makeRequest(username, amount);
        amount = 0.0;
        username = "";
        
        return "requestPayment.xhtml";
    }
}
