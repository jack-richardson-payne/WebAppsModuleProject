package com.jor22.webapps2020.entity;

import com.jor22.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-17T10:19:56")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, Double> amount;
    public static volatile SingularAttribute<Transactions, SystemUser> receiver;
    public static volatile SingularAttribute<Transactions, SystemUser> sender;
    public static volatile SingularAttribute<Transactions, Long> id;

}