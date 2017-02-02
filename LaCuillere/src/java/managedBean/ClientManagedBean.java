/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import Manager.WriteManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.Reservation;
import model.Restaurant;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="userManagedBean")
@SessionScoped
public class ClientManagedBean extends ParentManagedBean implements Serializable{
    
    private User user;
    private String ancien_mail;
    private boolean connected=false;
    private boolean reservationEnable=true;
    private boolean restaurantEnable=false;
    private List<Restaurant> myRestaurants;
    private List<Reservation> myReservations;
    
    
    
    
    @Autowired
    private WriteManager writeManager;
    
    @Autowired
    private ReadManager readManager;
    
    public ClientManagedBean(){
        user=new User();
        HttpSession session = getHttpSession();
        ancien_mail=(String)session.getAttribute("USER");
        
    }
    
    
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    public User getUser() {
        return user;
    }

    public List<Reservation> getMyReservations() {
        if(reservationEnable){
            myReservations=readManager.getReservationByUser(user.getEmail());
        }
        return myReservations;
    }

    public void setMyReservations(List<Reservation> myReservations) {
        this.myReservations = myReservations;
    }

    
    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReservationEnable() {
        return reservationEnable;
    }

    public void setReservationEnable(boolean reservationEnable) {
        this.reservationEnable = reservationEnable;
    }

    public List<Restaurant> getMyRestaurants() {
        if(isRestaurantEnable()){
           
            myRestaurants=readManager.getRestaurantByUser(user.getEmail());
        }
        return myRestaurants;
    }

    public void setMyRestaurants(List<Restaurant> myRestaurants) {
        this.myRestaurants = myRestaurants;
    }

    
    public boolean isRestaurantEnable() {
        return restaurantEnable && user.getProfil();
    }

    public void setRestaurantEnable(boolean restaurantEnable) {
        this.restaurantEnable = restaurantEnable;
    }
    
    
    public boolean validateEmail(){
        // on verifie que l'adresse mail n'est pas utilise
        return !readManager.existsUser(user.getEmail());
    }
    
    public void updateClient(){
         boolean validation=true;
         
        if(!user.getEmail().equals(ancien_mail) && !validateEmail()) {
            validation=false;
            addError("L'adresse mail que vous avez saisie est liée à un autre profil");
            
        }
        
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            validation=false;
            addError("Les deux mots de passe saisis ne sont pas identiques");
        }
        int res=-1;
        if(validation)
        res=writeManager.updateUser(user);
        
        
        HttpSession session = getHttpSession();
        session.setAttribute("USER", user.getEmail());
        session.setAttribute("PROFIL", user.getProfil());
        if(user.getProfil()){
            reservationEnable=false;
            restaurantEnable=true;
        }else{
            reservationEnable=true;
            restaurantEnable=false;
        }
        try {
        if(res==1){
           
                redirect("espaceClient.xhtml");
            
        }else
            addError("erreur de mise à jour de compte");
         
        } catch (IOException ex) {
                Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void addClient(){
        // validation
        boolean validation=true;
        if(!validateEmail()) {
            validation=false;
            addError("L'adresse mail que vous avez saisie est liée à un autre profil");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            validation=false;
            addError("Les deux mots de passe saisis ne sont pas identiques");
        }
        if(user.getProfil()){
            reservationEnable=false;
            restaurantEnable=true;
        }else{
            reservationEnable=true;
            restaurantEnable=false;
        }
        
        int res=-1;
        if(validation)
        res=writeManager.addUser(user);
        ancien_mail=user.getEmail();
        
        HttpSession session = getHttpSession();
        session.setAttribute("USER", user.getEmail());
        session.setAttribute("PROFIL", user.getProfil());
        connected=true;
        try {
        if(res==1){
           
                redirect("espaceClient.xhtml");
            
        }else
            addError("erreur d'insertion");
         
        } catch (IOException ex) {
                Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void connexion(){
        boolean isConnected=readManager.isConnected(user);
        try { 
        if(isConnected){
            connected=true;
                HttpSession session = getHttpSession();
                session.setAttribute("USER", user.getEmail());
                user=readManager.getUserByMail(user.getEmail());
                session.setAttribute("USER", user.getEmail());
                session.setAttribute("PROFIL", user.getProfil());
                if(user.getProfil()){
                    reservationEnable=false;
                    restaurantEnable=true;
                }else{
                    reservationEnable=true;
                    restaurantEnable=false;
                }
                ancien_mail=user.getEmail();
                redirect("espaceClient.xhtml");
            
        }else
        {
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Veuillez verifier vos identifiants"));
                 addError("Veuillez verifier vos identifiants");
               //  redirect("connectClient.xhtml");   
        }
        } catch (IOException ex) {
                Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void deconnexion(){
         HttpSession session = getHttpSession();
         session.invalidate();
        try {
            redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMyRestaurants(){
        try {
            reservationEnable=false;
            restaurantEnable=true;
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mesInformations(){
        try {
            redirect("mesInformations.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMyReservations(){
       reservationEnable=true;
        restaurantEnable=false;
        try {
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mesReservations(){
        try {
            redirect("mesInformations.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajoutRestaurant(){
        try {
            redirect("ajoutRestaurant.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
