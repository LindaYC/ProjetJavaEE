/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.CategorieDao;
import Dao.MenuDao;
import Dao.PlageDao;
import Dao.ReservationDAO;
import Dao.RestaurantDao;
import Dao.UserDao;
import java.io.Serializable;
import java.util.List;
import model.Categorie;
import model.Menu;
import model.Restaurant;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class ReadManager implements Serializable{
    private ReservationDAO reservationDao;
    
    private UserDao userDao;
    
    private CategorieDao categorieDao;

    private MenuDao menuDao;
    private PlageDao plageDao;

    public PlageDao getPlageDao() {
        return plageDao;
    }

    public void setPlageDao(PlageDao plageDao) {
        this.plageDao = plageDao;
    }
    

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    
    
    public ReservationDAO getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }
    
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
        if(categorie.length()==0){
            categorie=null;
        }
        if(ville!=null && ville.length()==0){
            ville=null;
        }
        if(nom!=null && nom.length()==0){
            nom=null;
        }

        return restaurantDao.search(nom,ville,categorie);
    }

    public User getUserByMail(String email) {
        return userDao.getByMail(email);
    }

    public byte[] getImageRestaurantById(Integer valueOf) {
        return restaurantDao.getImageById(valueOf);
    }

    public List<Menu> getListMenu(int idRestaurant) {
        return menuDao.loadListMenuByRestaurant(idRestaurant);
    }
    
}
