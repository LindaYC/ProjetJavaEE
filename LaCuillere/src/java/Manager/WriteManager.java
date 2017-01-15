/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.CategorieDao;
import Dao.RestaurantDao;
import Dao.UserDao;
import java.util.List;
import model.Categorie;
import model.Restaurant;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class WriteManager {

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
    
    
    
    public int addUser(User user) {
        
       return userDao.addUser(user);
        
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addRestaurant(Restaurant restaurant) {
        List<Categorie> categories = categorieDao.loadAll();
        boolean findCategorie=false;
        for(Categorie cat : categories){
            
            System.out.println("CATEGORIE en BASE : "+cat.getNom());
            if(cat.getNom().toUpperCase().equals(restaurant.getCategorie().getNom().toUpperCase())){
                
                findCategorie=true;
                restaurant.getCategorie().setNom(cat.getNom());
            }
            
        }
        if(!findCategorie){
                categorieDao.insertCategorie(restaurant.getCategorie().getNom().toUpperCase());
            }
        
            return restaurantDao.createRestaurant(restaurant);
    }
    
}
