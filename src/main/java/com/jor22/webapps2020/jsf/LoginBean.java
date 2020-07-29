package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.ejb.UserService;
import com.jor22.webapps2020.entity.SystemUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    
    @EJB
    private UserService userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        try {
            //login with the details 
            request.login(this.username, this.password);
            
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            return "error";
        }
        System.out.println(request.getRequestURI());
        // check what role user is 
        boolean userRole = context.getExternalContext().isUserInRole("users");
        boolean adminRole = context.getExternalContext().isUserInRole("admins");
        if(userRole == true){
            //user home page
            SystemUser user = userService.find(username);
            // add user to session variables
            context.getExternalContext().getSessionMap().put("user", user);
            return "/users/home.xhtml?faces-redirect=true";
        }
        else if(adminRole == true){
            // admin home page
            return "/admins/home.xhtml?faces-redirect=true";
        }
        else{
            return null;
        }
    }

    public String logout(){
        // end session and redirect to index
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
