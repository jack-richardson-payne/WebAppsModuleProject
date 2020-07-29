
package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.ejb.TransactionExecutor;
import com.jor22.webapps2020.ejb.requestService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("processRequest")
@RequestScoped
public class processRequestBean {
    
    @EJB 
    requestService req;
    String result;
    

    public processRequestBean() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String remove(Long id){
        // delete request
        result = req.removeRequest(id);
        return "userRequests.xhtml";
    }
    
    public String accept(Long id){
        // fulfill request
        result = req.completeRequest(id);
        return "userRequests.xhtml";
    }

    
    
    
}
