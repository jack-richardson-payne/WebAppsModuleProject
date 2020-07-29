
package com.jor22.webapps2020.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

// query uses name to identigy admins, all admins have name Admin, could also have joined to group table
@NamedQuery(name="findAllUsers", query="SELECT u FROM SystemUser u WHERE u.name NOT LIKE 'Admin'")
@NamedQuery(name="getUsernames", query="SELECT u FROM SystemUser u")
@NamedQuery(name="findUser", query="SELECT u FROM SystemUser u WHERE u.username = :username")
@Entity
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    String currency;
    String username;
    String password;
    double balance;

    public SystemUser(String username, String password, String name, String currency, double balance) {
        this.name = name;
        this.currency = currency;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
   
    public SystemUser() {
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   
}
