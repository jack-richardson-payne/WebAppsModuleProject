
package com.jor22.webapps2020.jsf;

import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import com.jor22.webapps2020.ejb.TransactionExecutor;
import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.rest.WebPageConverter;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;


@Named(value = "TransactionBean")
@ConversationScoped
public class TransactionBean implements Serializable{
    
    @EJB
    TransactionExecutor trans;
    double amount;
    String receiver; 
    String result;
    // used to get session variables
    FacesContext context = FacesContext.getCurrentInstance();
    SystemUser use = (SystemUser) context.getExternalContext().getSessionMap().get("user");
    // Rest service
    public static WebPageConverter conv = new WebPageConverter();

    public TransactionBean() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String makePayment(){
        //convert all to pounds so we can use
        amount = Double.valueOf(conv.toPound(use.getCurrency(), amount));
        // call trans ejb
        result = trans.execute(receiver, amount);
        amount = 0.0;
        receiver = "";
        
        return "makePayment.xhtml";
    }
}
