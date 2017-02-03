/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.AnnonceDao;
import Dao.CategorieDao;
import Dao.MenuDao;
import Dao.PlageDao;
import Dao.RestaurantDao;
import Dao.ReservationDAO;
import Dao.UserDao;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Annonce;
import model.Categorie;
import model.Menu;
import model.Restaurant;
import model.User;
import model.Reservation;

/**
 *
 * @author MLBaiche
 */
public class WriteManager {

    private UserDao userDao;
    private CategorieDao categorieDao;
    private RestaurantDao restaurantDao;
    private AnnonceDao annonceDao;
    private ReservationDAO reservationDao;
    private PlageDao plageDao;
    private MenuDao menuDao;
    
  

    public PlageDao getPlageDao() {
        return plageDao;
    }

    public void setPlageDao(PlageDao plageDao) {
        this.plageDao = plageDao;
    }
    
    
    public AnnonceDao getAnnonceDao() {
        return annonceDao;
    }

    public void setAnnonceDao(AnnonceDao annonceDao) {
        this.annonceDao = annonceDao;
    }

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    
    
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
    
     public int addReservation(Reservation reservation) {
        
       return reservationDao.createReservation(reservation);
        
    }
    
    public int addUser(User user) {
        
       return userDao.addUser(user);
        
    }
    
    public ReservationDAO getReservationDao() {
        return reservationDao;
    }

     public void setReservationDao(ReservationDAO resDao) {
        reservationDao=resDao;
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

    public int addRestaurant(Restaurant restaurant,String user) {
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
                restaurant.getCategorie().setNom(restaurant.getCategorie().getNom().toUpperCase());
            }
            
            // generation de l'id restaurant
            int id_rest=restaurantDao.nextVal();
            restaurant.setIdRestaurant(id_rest);
            int res= restaurantDao.createRestaurant(restaurant);
            userDao.addRestaurant(user,id_rest);
            Annonce annonce = new Annonce();
            annonce.setEmail(restaurant.getEmail());
            annonce.setIdRestaurant(id_rest);
            annonce.setNumPhone(restaurant.getNumPhone());
            int id_annonce=annonceDao.nextVal();
            annonce.setIdAnnonce(id_annonce);
            annonceDao.createAnnonce(annonce);
            
            return res;
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    public void reserve(int restaurantSelected, String user, Time heure, Date date, int nbPersonne,int nbPlaceRestante) {
        
        java.sql.Date sqlDate =new java.sql.Date(date.getTime());
        // reservation d'un créneau
        int idAnnonce = annonceDao.getAnnonceByIdRestaurant(restaurantSelected);
         
        if(annonceDao.existPlage(sqlDate,heure,idAnnonce)){
            // on fait juste un update
            if(annonceDao.hasPlaces(sqlDate,heure,idAnnonce,nbPersonne))
                 annonceDao.updatePlaceDispo(idAnnonce,heure,sqlDate,nbPlaceRestante-nbPersonne);
            else 
                return;
        }else{
            plageDao.addPlage(heure,sqlDate);
            annonceDao.createPlage(idAnnonce,heure,sqlDate,nbPlaceRestante-nbPersonne);
            
        }
        
        int idRes=reservationDao.nextVal();
        reservationDao.createReservation(idRes,idAnnonce,nbPersonne,sqlDate,heure);
        userDao.addReservation(user,idRes);
        
        
    }

    public void addMenuToRestaurant(int restaurantSelected, Menu menu) {
        int idMenu=menuDao.nextVal();
        menu.setIdMenu(idMenu);
        menuDao.createMenu(menu);
        restaurantDao.addMenu(restaurantSelected,idMenu);
        
    }

    public void deleteReservation(Reservation res) {
        // on change le flag de reservation a false
        reservationDao.deleteReservation(res.getIdReservation());
        // on libéère les places dans ANNONCE_PLAGE
        int nbPlaceDispo=annonceDao.getPlaceDispo(res.getAnnonce().getRestaurant().getIdRestaurant(), res.getPlage().getTimeDebut(), res.getPlage().getDate());
        annonceDao.updatePlaceDispo(res.getIdAnnonce(), res.getPlage().getTimeDebut(),res.getPlage().getDate(),nbPlaceDispo+res.getNbPersonne());
    }
    
}
