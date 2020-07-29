
package com.jor22.webapps2020.ejb;

import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.entity.Transactions;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.faces.context.FacesContext;
import javax.jms.TransactionRolledBackException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(REQUIRED)
@RolesAllowed("users")
public class TransactionExecutor {

    @PersistenceContext
    EntityManager em;
    @EJB
    private UserService userService;
    
    public String execute(String receiver, double amount) {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser senderSession = (SystemUser) context.getExternalContext().getSessionMap().get("user");
        // check for errors on input
        if(senderSession.getUsername().equals(receiver)){
            return "ERROR: Cannot send to yourself";
        }
        else if(senderSession.getBalance() < amount){
            return "ERROR: Cannot send that amount";
        }
        else if (amount < 0){
            return "ERROR: Cannot send negative amount";
        }
        // check if user exists
       
        try {
            
            SystemUser getId = userService.find(receiver);
            SystemUser rec = em.find(SystemUser.class, getId.getId());
            if( "Admin".equals(rec.getName())){
                return "ERROR: Cannot send to Admin";
            }
            SystemUser sender = em.find(SystemUser.class, senderSession.getId());
            rec.setBalance(rec.getBalance()+amount);
            sender.setBalance(sender.getBalance()-amount);
            Transactions trans = new Transactions(sender, rec, amount);
            //update both sender and receiver values 
            em.persist(rec);
            em.persist(sender);
            em.persist(trans);
            context.getExternalContext().getSessionMap().remove("user");
            context.getExternalContext().getSessionMap().put("user", sender);
        } catch (EJBTransactionRolledbackException f){
            return "ERROR: No such user";
        }
       
        return "ACCEPTED"; 
    }
    
}
