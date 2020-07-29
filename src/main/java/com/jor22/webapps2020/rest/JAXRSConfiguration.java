package com.jor22.webapps2020.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/conversion/")
public class JAXRSConfiguration extends Application {
     @Override    
     public Set<Class<?>> getClasses() {        
         final Set<Class<?>> classes = new HashSet<Class<?>>();
          classes.add(Convert.class);        
          return classes;    
     }       
}
