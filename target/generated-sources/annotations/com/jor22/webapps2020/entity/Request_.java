package com.jor22.webapps2020.entity;

import com.jor22.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-17T10:19:56")
@StaticMetamodel(Request.class)
public class Request_ { 

    public static volatile SingularAttribute<Request, SystemUser> requestedFrom;
    public static volatile SingularAttribute<Request, Double> amountGBP;
    public static volatile SingularAttribute<Request, SystemUser> sendTo;
    public static volatile SingularAttribute<Request, Long> id;

}