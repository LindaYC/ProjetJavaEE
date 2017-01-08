/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author MLBaiche
 */
public class ParentManagedBean extends ManagedBean {
    
    
    
    public ParentManagedBean() {
   
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                getServletContext());

    }
}
