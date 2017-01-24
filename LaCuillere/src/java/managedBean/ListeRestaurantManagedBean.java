/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import model.Categorie;
import model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="listeRestaurantManagedBean")
@SessionScoped
public class ListeRestaurantManagedBean extends ParentManagedBean implements Serializable{
    private String categorieSelected;
    private String nom;
    private String ville;
    private List<String> categories;
    private List<Restaurant> listeRestaurantSearch;
    
    @Autowired
    private ReadManager readManager;

    public String getCategorieSelected() {
        return categorieSelected;
    }

    public void setCategorieSelected(String categorieSelected) {
        this.categorieSelected = categorieSelected;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<String> getCategories() {
        if(categories==null){
             categories=new ArrayList<String>();
            for(Categorie cat : readManager.loadAllCategorie()){
                System.out.println("Liste des categorie :"+cat.getNom());
                categories.add(cat.getNom());
            }
        }
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    
    public void search(){
        listeRestaurantSearch=readManager.search(nom,ville,categorieSelected);
        
        try {
            redirect("listRestaurant.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ListeRestaurantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
