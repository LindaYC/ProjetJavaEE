/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import Manager.WriteManager;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
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
    
    @Autowired
    private WriteManager writeManager;
    
    private int restaurantSelected;
    private List<Menu> listMenu;
    private List<Time> heures;
    private Time heure;
    private Date date;
    private int nbPersonne;
    private int capacite;

    public MenuManagedBean(){
        heures=new ArrayList<Time>();
        heures.add(new Time(8,00,00));
        heures.add(new Time(9,00,00));
        heures.add(new Time(12,00,00));
        heures.add(new Time(14,00,00));
        heures.add(new Time(19,00,00));
        heures.add(new Time(21,00,00));
    }
    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }
    
    
    public int getRestaurantSelected() {
        return restaurantSelected;
    }

    public List<Time> getHeures() {
        return heures;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    public void setHeures(List<Time> heures) {
        this.heures = heures;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }
    
    public void setRestaurantSelected(int restaurantSelected) {
        this.restaurantSelected = restaurantSelected;
    }
    
    public List<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu = listMenu;
    }
    
    
    
    public void listMenu(int idRestaurant,int cap){
        restaurantSelected=idRestaurant;
        capacite=cap;
        listMenu=readManager.getListMenu(idRestaurant);
        try {
            redirect("menuRestaurant.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reserve(){
        HttpSession session = getHttpSession();
        String user =(String)session.getAttribute("USER");
        System.out.println("Debut de Résérvation ");
        writeManager.reserve(restaurantSelected,user,heure,date,nbPersonne,capacite);
        try {
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
