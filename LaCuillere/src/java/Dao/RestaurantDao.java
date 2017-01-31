/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Categorie;
import model.Restaurant;

/**
 *
 * @author MLBaiche
 */
public class RestaurantDao {
    
    private static final String INSERT_RESTAURANT="INSERT INTO T_RESTAURANT(ID_RESTAURANT,NOM,ADRESSE,VILLE,NUM_PHONE,EMAIL,NB_PLACE_MAX,TIME_OUVERTURE,TIME_FERMETURE,CATEGORIE,PHOTO,PRIX_MOYEN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String GET__IMAGE_RESTAURANT_BY_ID="SELECT * FROM T_RESTAURANT WHERE ID_RESTAURANT=?";
    private static final String NEXT_VAL="SELECT NEXTVAL('SQ_ID_RESTAURANT')";
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public int createRestaurant(Restaurant restaurant) {
        
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_RESTAURANT);
            int i=1;
            ps.setInt(i++, restaurant.getIdRestaurant());
            ps.setString(i++, restaurant.getNom());
            ps.setString(i++, restaurant.getAdresse());
            ps.setString(i++, restaurant.getVille());
            ps.setString(i++, restaurant.getNumPhone());
            ps.setString(i++, restaurant.getEmail());
            ps.setInt(i++,restaurant.getCapacite());
            ps.setTime(i++, restaurant.getHeureOuverture());
            ps.setTime(i++, restaurant.getHeureFermeture());
            ps.setString(i++, restaurant.getCategorie().getNom());
            
            ps.setBinaryStream(i++, restaurant.getBlob(),restaurant.getTaillePhoto());
            ps.setInt(i++, restaurant.getPrixMoyen());
            
            rs = ps.executeUpdate();
            ResultSet resSet = ps.getGeneratedKeys();
            if(resSet.next()){
                rs=resSet.getInt(1);
            }
            
            System.out.println("Resultat insertion : "+restaurant.getIdRestaurant());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return rs;
            }

    public List<Restaurant> search(String nom, String ville, String categorie) {
        String SEARCH_RESTAURANT="SELECT * FROM T_RESTAURANT WHERE 1=1";
        Connection con = null;
        ResultSet rs=null;
        List<Restaurant> result=new ArrayList<Restaurant>();
        
        
        
         try {
            System.out.println("Filtres Saisies : [Nom : "+nom+" ,Ville : "+ville+" ,Categorie :"+categorie+"]");
            int i=1;
            if(nom!=null && nom.length()!=0){
            SEARCH_RESTAURANT+=" AND NOM=?";
           
            }
        
        if(ville!=null && ville.length()!=0){
            SEARCH_RESTAURANT+=" AND VILLE=?";
           
        }
            
        if(categorie!=null && categorie.length()!=0){
            SEARCH_RESTAURANT+=" AND CATEGORIE=?";
           
            }
             System.out.println("Requête Saisie : "+SEARCH_RESTAURANT);
        con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SEARCH_RESTAURANT);
            
           if(nom!=null && nom.length()!=0){
          
            ps.setString(i++,nom);
            }
        
        if(ville!=null && ville.length()!=0){
           
            ps.setString(i++, ville);
        }
            
        if(categorie!=null && categorie.length()!=0){
           
            ps.setString(i++, categorie);
            }
          
            rs = ps.executeQuery();
            
            while(rs.next()){
                Restaurant rest=new Restaurant();
                rest.setIdRestaurant(rs.getInt("ID_RESTAURANT"));
                rest.setNom(rs.getString("NOM"));
                rest.setAdresse(rs.getString("ADRESSE"));
                rest.setVille(rs.getString("VILLE"));
                rest.setCapacite (rs.getInt("NB_PLACE_MAX"));
                Categorie cat= new Categorie();
                cat.setNom(rs.getString("CATEGORIE"));
                rest.setCategorie(cat);
                result.add(rest);
                rest.setEmail(rs.getString("EMAIL"));
                rest.setNumPhone(rs.getString("NUM_PHONE"));
                rest.setPrixMoyen(rs.getInt("PRIX_MOYEN"));
                
                System.out.println("Restaurant trouves : "+rest.getNom());
            }
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
         
         return result;
        
    }

    public int nextVal() {
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(NEXT_VAL);
            ResultSet resSet = ps.executeQuery();
            
            if(resSet.next()){
                rs=resSet.getInt(1);
            }
            
            System.out.println("Clé primaire généré : "+rs);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return rs;
        
    }

    public byte[] getImageById(Integer idRestaurant) {
       Connection con = null;
        ResultSet rs=null;
        byte[] result=null;
        
        
        
         try {

        con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(GET__IMAGE_RESTAURANT_BY_ID);
            ps.setInt(1, idRestaurant);
          
            rs = ps.executeQuery();
            
            if(rs.next()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                
                byte[] buf = new byte[1024];
                InputStream in = rs.getBinaryStream("PHOTO");
                int n = 0;
                while ((n=in.read(buf))>=0)
                {
                   baos.write(buf, 0, n);
                }
                in.close();
               
                
                result=baos.toByteArray();
                
            }
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestaurantDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
         
         return result;
    }
    public List<Restaurant> getRestaurantByUser(/*@RequestParam("id")*/ String mail) throws IOException {
        Connection con = null;
        String SEARCH_RESTAURANT="SELECT * FROM T_RESTAURANT WHERE 1=1";
        ResultSet rs=null;
        List<Restaurant> result=new ArrayList<Restaurant>();
         try {              
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SEARCH_RESTAURANT+ mail+";");        
            rs = ps.executeQuery();
            while(rs.next()){
                 Restaurant rest=new Restaurant();
                rest.setIdRestaurant(rs.getInt("ID_RESTAURANT"));
                rest.setNom(rs.getString("NOM"));
                rest.setAdresse(rs.getString("ADRESSE"));
                rest.setVille(rs.getString("VILLE"));
                rest.setCapacite (rs.getInt("NB_PLACE_MAX"));
                Categorie cat= new Categorie();
                cat.setNom(rs.getString("CATEGORIE"));
                rest.setCategorie(cat);
                rest.setEmail(rs.getString("EMAIL"));
                rest.setNumPhone(rs.getString("NUM_PHONE"));
                rest.setPrixMoyen(rs.getInt("PRIX_MOYEN"));
               
            }
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return result;
        
    }
    
    public int getNbrOfRestaurantsByUser(String email) throws SQLException{
        Connection con = null;
        ResultSet rs = null;
        String SEARCH_RESTAURANT="SELECT * FROM T_RESTAURANT WHERE 1=1";
        int nbrOfRestaurants = 0;
        PreparedStatement ps = con.prepareStatement(SEARCH_RESTAURANT+email+";");
            if(email != null){
                rs = ps.executeQuery();
                nbrOfRestaurants = rs.getFetchSize();
            } else 
                System.out.println("0 restaurants trouvés !");
            
            return nbrOfRestaurants;
    }
}
