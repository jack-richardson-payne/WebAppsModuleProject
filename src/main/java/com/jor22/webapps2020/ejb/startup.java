
package com.jor22.webapps2020.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class startup {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    private UserService userService;
    
    @PostConstruct
    public void onStartup() {
        // create new admin on startup
        userService.registerAdmin("admin1", "admin1");
    }
}
