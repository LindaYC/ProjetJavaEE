/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerBean;

import javax.faces.bean.ViewScoped;
import model.User;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="client")
@ViewScoped
public class ClientManagedBean extends ManagedBean{
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
