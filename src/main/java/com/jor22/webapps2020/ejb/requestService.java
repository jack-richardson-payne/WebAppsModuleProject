
package com.jor22.webapps2020.ejb;

import com.jor22.webapps2020.entity.Request;
import com.jor22.webapps2020.entity.SystemUser;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@RolesAllowed("users")
public class requestService {
    @PersistenceContext
    EntityManager em;
    @EJB
    private UserService userService;
    @EJB
    private TransactionExecutor exe;
    
    public String makeRequest(String username, double amount){
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser session = (SystemUser) context.getExternalContext().getSessionMap().get("user");
        // check input
        if(session.getUsername().equals(username)){
            return "ERROR: Cannot request from yourself";
        }
        else if (amount < 0){
            return "ERROR: Cannot request negative amount";
        }
        
        try {
            SystemUser from = userService.find(username);
            if("Admin".equals(from.getName())){
                return "ERROR: Cannot request from Admin";
            }
            Request R = new Request(from, session, amount);
            em.persist(R);
            return "accepted";
        } catch (EJBTransactionRolledbackException f){
            return "ERROR: No such user";
        }
    }

    public String removeRequest(Long id) {
        
        Request rec = em.find(Request.class, id);
        em.remove(rec);
        return "Removed";
    }

    public String completeRequest(Long id) {
        // pass request to trans ejb
        Request rec = em.find(Request.class, id);
        String ret = exe.execute(rec.getSendTo().getUsername(), rec.getAmountGBP());
        if(ret.equals("ACCEPTED")){
            em.remove(rec);
        }
        return ret;
        
    }
}
