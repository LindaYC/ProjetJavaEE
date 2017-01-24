/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import Manager.WriteManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.Categorie;
import model.Restaurant;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="restaurantManagedBean")
@SessionScoped
public class RestaurantManagedBean extends ParentManagedBean implements Serializable{
    private String user;
    private Restaurant restaurant;
    private List<String> allCategorie;
    private UploadedFile packageFile;
    
    @Autowired
    private WriteManager writeManager;
    
    @Autowired
    private ReadManager readManager;
    
    
    public RestaurantManagedBean(){
        restaurant=new Restaurant();
        Categorie cat=new Categorie();
        restaurant.setCategorie(cat);
        restaurant.setCapacite(50);
        HttpSession session = getHttpSession();
        user=(String)session.getAttribute("USER");
        
    }

    public UploadedFile getPackageFile() {
        return packageFile;
    }

    public void setPackageFile(UploadedFile packageFile) {
        this.packageFile = packageFile;
    }
    
    
   
    public void addRestaurant(){
        
        try {
            if(packageFile!=null){
                //ByteArrayOutputStream output=new ByteArrayOutputStream();
                try {
                    byte[] bytes= new byte[4096];
                    packageFile.getInputstream().read(bytes);
                    // System.out.println("Taille photo : "+bytes.length);
                    System.out.println("Taille photo 2 : "+packageFile.getSize());
                    //FileInputStream fis = new FileInputStream(new FileInputStream)
                    
                    //output.write(bytes);
                    restaurant.setTaillePhoto(packageFile.getSize());
                    restaurant.setPackageBlob((FileInputStream)packageFile.getInputstream());
                    
                } catch (IOException ex) {
                    Logger.getLogger(RestaurantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            int res=writeManager.addRestaurant(restaurant,user);
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RestaurantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public List<String> getAllCategorie() {
        if(allCategorie==null || allCategorie.size()==0){
            allCategorie=new ArrayList<String>();
            for(Categorie cat : readManager.loadAllCategorie()){
                System.out.println("Liste des categorie :"+cat.getNom());
                allCategorie.add(cat.getNom());
            }
        }
        
         return allCategorie;
    }

    public void setAllCategorie(List<String> allCategorie) {
        this.allCategorie = allCategorie;
    }
    
    /**
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant the restaurant to set
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
}
