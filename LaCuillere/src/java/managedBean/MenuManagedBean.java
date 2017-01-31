/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import model.Menu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="menuManagedBean")
@SessionScoped
public class MenuManagedBean extends ParentManagedBean implements Serializable{
    @Autowired
    private ReadManager readManager;
    private List<Menu> listMenu;

    public List<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu = listMenu;
    }
    
    
    
    public void listMenu(int idRestaurant){
        listMenu=readManager.getListMenu(idRestaurant);
        try {
            redirect("listMenu.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
