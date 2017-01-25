/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.CategorieDao;
import Dao.RestaurantDao;
import Dao.UserDao;
import java.io.Serializable;
import java.util.List;
import model.Categorie;
import model.Restaurant;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class ReadManager implements Serializable{
    
    private UserDao userDao;
    
    private CategorieDao categorieDao;
    
    private RestaurantDao restaurantDao;

    public CategorieDao getCategorieDao() {
        return categorieDao;
    }

    public void setCategorieDao(CategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }

    public RestaurantDao getRestaurantDao() {
        return restaurantDao;
    }

    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }
    
    
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
        System.out.println("Injection de dependance userDao");
    }
    
    public List<User> getAllUser(){
        return userDao.loadAll();
    }

    public boolean existsUser(String email) {
        return userDao.existsUser(email);
    }
    
    public boolean isConnected(User u){
        
        return userDao.isConnected(u);
    }

    public List<Categorie> loadAllCategorie() {
       
        return categorieDao.loadAll();
    }

    public List<Restaurant> search(String nom, String ville, String categorieSelected) {
       
        String categorie="";
        List<Categorie> categories = categorieDao.loadAll();
        
        for(Categorie cat : categories){
            
            
            if(cat.getNom().toUpperCase().equals(categorieSelected.toUpperCase())){
                categorie=cat.getNom().toUpperCase();
            }
    }

        return restaurantDao.search(nom,ville,categorie);
    }

    public User getUserByMail(String email) {
        return userDao.getByMail(email);
    }

    public byte[] getImageRestaurantById(Integer valueOf) {
        return restaurantDao.getImageById(valueOf);
    }
    
}
