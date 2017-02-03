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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
   
    private int nbPlaceDispo;
    
    

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

    public int getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
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
        nbPlaceDispo=cap;
        listMenu=readManager.getListMenu(idRestaurant);
        try {
            redirect("menuRestaurant.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateInformation(){
       Time time =null;
        if(heure!=null && !heure.equals("") && date!=null && !date.equals("")){
            time= convertStringToTime(heure);
            nbPlaceDispo=readManager.getPlaceDispo(restaurantSelected,time,convertDate(date));
            if(nbPlaceDispo<0) nbPlaceDispo=capacite;
        }
        if(nbPlaceDispo<nbPersonne && nbPlaceDispo>=0){
            addError("Attention il ne reste plus que :"+nbPlaceDispo+" places");
        }
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
        
         if(nbPersonne<=0){
            addError("Vous devez Selectionner le nombre de personne");
            
           // redirect("connectClient.xhtml");
            return;
        }
        if(nbPlaceDispo-nbPersonne<0){
            addError("Il ne reste plus de place veuillez modifiz votre créneau");
            
           // redirect("connectClient.xhtml");
            return;
        }
      //  System.out.println("Debut de Résérvation ");
        
        System.err.println("infos : idRestaurant "+restaurantSelected+" user : "+user+" time : "+heure+" date : "+date+" nbPersonne :"
                +nbPersonne+" capacite : "+capacite);
        Time time=convertStringToTime(heure);
        
      writeManager.reserve(restaurantSelected,user,time,convertDate(date),nbPersonne,nbPlaceDispo);
   
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dateTime=null;
        try {
            dateTime = formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(MenuManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return dateTime;
    }
    
    
    public void ajouterMenu(int idRestaurant,int cap){
        try {
            capacite=cap;
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
