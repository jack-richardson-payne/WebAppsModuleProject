package com.jor22.webapps2020.ejb;

import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.entity.SystemUserGroup;
import com.jor22.webapps2020.ejb.databaseList;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    databaseList store;

    public UserService() {
    }

    public boolean registerUser(String username, String userpassword, String name, String currency) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String paswdToStoreInDB = sb.toString();

            for(int i=0; i <= store.getUsernames().size()-1; i++){
                if(store.getUsernames().get(i).getUsername().equals(username)){
                    // username taken
                    return false;
                }
            }
            
            sys_user = new SystemUser(username, paswdToStoreInDB, name, currency, 1000);
            sys_user_group = new SystemUserGroup(username, "users");

            System.out.println(name);
            
            em.persist(sys_user);
            em.persist(sys_user_group);
            return true;

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean registerAdmin(String username, String userpassword){
        try {
                SystemUser sys_user;
                SystemUserGroup sys_user_group;

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                String passwd = userpassword;
                md.update(passwd.getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }
                String paswdToStoreInDB = sb.toString();

                for(int i=0; i <= store.getUsernames().size()-1; i++){
                    if(store.getUsernames().get(i).getUsername().equals(username)){
                        // username taken
                        return false;
                    }
                }

                sys_user = new SystemUser(username, paswdToStoreInDB, "Admin", "GDP", 1000);
                sys_user_group = new SystemUserGroup(username, "admins");

                em.persist(sys_user);
                em.persist(sys_user_group);
                return true;

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // see if username exists 
    public SystemUser find(String username){
      return (SystemUser) em.createNamedQuery("findUser").setParameter("username", username).getResultList().get(0);
    }
}
