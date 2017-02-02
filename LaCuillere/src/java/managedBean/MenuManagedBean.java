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
    private List<String> heures;
    private String heure;
    private String date;
    private int nbPersonne;
    private int capacite;
    private Menu menu;
    

    public MenuManagedBean(){
        heures=new ArrayList<String>();
        heures.add(" 08h:00 ");
        heures.add(" 09h:00 ");
        heures.add(" 12h:00 ");
        heures.add(" 14h:00 ");
        heures.add(" 19h:00 ");
        heures.add(" 21h:00 ");
        menu=new Menu();
    }
    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    
    public int getRestaurantSelected() {
        return restaurantSelected;
    }

    public List<String> getHeures() {
        return heures;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    public void setHeures(List<String> heures) {
        this.heures = heures;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
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
    public void updateInformation(){
        // verifiez s'il existe encore des places pour ce créneau
    }
    public void reserve(){
        try {
        HttpSession session = getHttpSession();
        String user =(String)session.getAttribute("USER");
        if(user==null || user.length()==0){
            addError("Vous devez être connectez pour résérver");
            
           // redirect("connectClient.xhtml");
            return;
        }
        if(capacite-nbPersonne<0){
            addError("Il ne reste plus de place veuillez modifiz votre créneau");
            
           // redirect("connectClient.xhtml");
            return;
        }
      //  System.out.println("Debut de Résérvation ");
        
        System.err.println("infos : idRestaurant "+restaurantSelected+" user : "+user+" time : "+heure+" date : "+date+" nbPersonne :"
                +nbPersonne+" capacite : "+capacite);
        Time time=convertStringToTime(heure);
        writeManager.reserve(restaurantSelected,user,time,convertDate(date),nbPersonne,capacite);
   
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Time convertStringToTime(String heure) {
        Time time= null;
        switch(heure){
            case " 08h:00 " : time = new Time(8,0,0); break;
            case " 09h:00 " : time = new Time(9,0,0); break;
            case " 12h:00 " : time = new Time(12,0,0); break;
            case " 14h:00 " : time = new Time(14,0,0); break;
            case " 19h:00 " : time = new Time(19,0,0); break;
            case " 21h:00 " : time = new Time(21,0,0); break;
            default : time = new Time(19,0,0); break;
        };
        
        return time;
    }

    private Date convertDate(String date) {
        Date result=null;
        String[] dates=date.split("-");
        result=new Date(Integer.valueOf(dates[2]),Integer.valueOf(dates[1]), Integer.valueOf(dates[0]));
        return result;
    }
    
    
    public void ajouterMenu(int idRestaurant){
        try {
            restaurantSelected=idRestaurant;
            redirect("ajoutMenu.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMenu(){
        try {
            writeManager.addMenuToRestaurant(restaurantSelected,menu);
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
