package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.ejb.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class adminRegBean {

    @EJB
    UserService usrSrv;
    
    String username;
    String userpassword;
    String result;
    

    public adminRegBean() {

    }

    
    
    public String register() {
        if(usrSrv.registerAdmin(username, userpassword)){
            // successful 
            return "home.xhtml";
        }
        else{
            // username taken
            result = "Error: Username Taken";
            return "registrationAdmin";
        }
        
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
    public UserService getUsrSrv() {
        return usrSrv;
    }

    public void setUsrSrv(UserService usrSrv) {
        this.usrSrv = usrSrv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
 
}

