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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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

    public void setUser(User user) {
        this.user = user;
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
}
