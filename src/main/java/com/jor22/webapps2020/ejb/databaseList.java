
package com.jor22.webapps2020.ejb;

import com.jor22.webapps2020.entity.Request;
import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.entity.Transactions;

import java.util.List;
import javax.annotation.security.RolesAllowed;

public interface databaseList {
    
     @RolesAllowed("admins")
     public List<SystemUser> getUserList();
     
     public List<SystemUser> getUsernames();
    
     @RolesAllowed("admins")
     public List<Transactions> getTransAll();

     @RolesAllowed("users")
     public List<Transactions> userTransRec();

     @RolesAllowed("users")
     public List<Transactions> userTransSend();

     @RolesAllowed("users")
     public List<Request> getUserRequests();
}
