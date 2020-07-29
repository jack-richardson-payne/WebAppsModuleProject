package com.jor22.webapps2020.ejb;

import com.jor22.webapps2020.entity.Request;
import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.entity.Transactions;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class databaseListBean implements databaseList {

    @PersistenceContext
    EntityManager em;

    public databaseListBean() {
        
    }

    @Override
    public synchronized List<SystemUser> getUserList() {
        return em.createNamedQuery("findAllUsers").getResultList();
    }
    
    @Override
    public synchronized List<SystemUser> getUsernames() {
        return em.createNamedQuery("getUsernames").getResultList();
    }
    
    @Override
    public synchronized List<Transactions> getTransAll() {
        return em.createNamedQuery("getTransactionsAll").getResultList();
    }

    @Override 
    public synchronized List<Request> getUserRequests(){
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser senderSession = (SystemUser) context.getExternalContext().getSessionMap().get("user");
        return em.createNamedQuery("getUserRequests").setParameter("username", senderSession.getUsername()).getResultList();
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("UserStore: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("UserStore: PreDestroy");
    }

    @Override
    public List<Transactions> userTransRec() {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser senderSession = (SystemUser) context.getExternalContext().getSessionMap().get("user");
        return em.createNamedQuery("userTransRec").setParameter("username", senderSession.getUsername()).getResultList();
    }

    @Override
    public List<Transactions> userTransSend() {
        FacesContext context = FacesContext.getCurrentInstance();
        SystemUser senderSession = (SystemUser) context.getExternalContext().getSessionMap().get("user");
        return em.createNamedQuery("userTransSend").setParameter("username", senderSession.getUsername()).getResultList();
    }
}
