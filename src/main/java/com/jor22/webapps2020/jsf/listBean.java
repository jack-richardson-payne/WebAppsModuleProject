package com.jor22.webapps2020.jsf;

import com.jor22.webapps2020.ejb.databaseList;
import com.jor22.webapps2020.entity.SystemUser;
import com.jor22.webapps2020.ejb.databaseListBean;
import com.jor22.webapps2020.entity.Request;
import com.jor22.webapps2020.entity.Transactions;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

// class used to get lists of elements e.g. payments/requests
@Named("listGetter")
@RequestScoped
public class listBean {
     
    @EJB
    databaseList store;
    
    public listBean() {
    }
    
    public List<SystemUser> getUserList() {
        return store.getUserList();
    }
    
    public List<Transactions>  getTransList(){
        return store.getTransAll();
    }
    
    public List<Transactions>  userTransRec(){
        return store.userTransRec();
    }
    
    public List<Transactions>  userTransSend(){
        return store.userTransSend();
    }
    
    public List<Request> userRequests(){
        return store.getUserRequests();
    }
}

