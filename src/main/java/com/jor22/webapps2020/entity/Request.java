
package com.jor22.webapps2020.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

// query gets all requests for specifc username
@NamedQuery(name="getUserRequests", query="SELECT r FROM Request r WHERE r.requestedFrom.username = :username")
@Entity
public class Request implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    SystemUser requestedFrom;
    @ManyToOne
    SystemUser sendTo;
    double amountGBP;

    public Request(SystemUser requestedFrom, SystemUser sendTo, double amountGBP) {
        this.requestedFrom = requestedFrom;
        this.sendTo = sendTo;
        this.amountGBP = amountGBP;
    }

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(SystemUser requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public double getAmountGBP() {
        return amountGBP;
    }

    public void setAmountGBP(double amountGBP) {
        this.amountGBP = amountGBP;
    }

    public SystemUser getSendTo() {
        return sendTo;
    }

    public void setSendTo(SystemUser sendTo) {
        this.sendTo = sendTo;
    }
    
    
}
