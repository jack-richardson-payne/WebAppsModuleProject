
package com.jor22.webapps2020.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name="getTransactionsAll", query="SELECT t FROM Transactions t")
@NamedQuery(name="userTransRec", query="SELECT t FROM Transactions t WHERE t.receiver.username = :username")
@NamedQuery(name="userTransSend", query="SELECT t FROM Transactions t WHERE t.sender.username = :username")
@Entity
public class Transactions implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    SystemUser sender;
    @ManyToOne
    SystemUser receiver;
    double amount;

    public Transactions() {
    }

    public Transactions(SystemUser sender, SystemUser receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public SystemUser getSender() {
        return sender;
    }

    public void setSender(SystemUser sender) {
        this.sender = sender;
    }

    public SystemUser getReceiver() {
        return receiver;
    }

    public void setReceiver(SystemUser receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
