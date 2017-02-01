/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class UserDao {
    
    private DataSource dataSource;
    
    private final String SELECT_ALL_USER="SELECT * FROM T_USER";
    private final String INSERT_USER="INSERT INTO T_USER(NOM,PRENOM,MAIL,PASSWORD,TELEPHONE,PROFIL,BONUS) VALUES(?,?,?,?,?,?,?)";
    private final String ADD_RESTAURANT_TO_USER="INSERT INTO T_USER_RESTAURANT(MAIL,ID_RESTAURANT) VALUES(?,?)";
    private final String SELECT_USER_BY_EMAIL="SELECT * FROM T_USER WHERE MAIL=?";
    private final String SELECT_CONNECTED_USER="SELECT * FROM T_USER WHERE MAIL=? AND PASSWORD=?";
    private final String UPDATE_USER="UPDATE T_USER SET NOM=?, PRENOM=? , PASSWORD=? , TELEPHONE=?, PROFIL=?   WHERE MAIL=?";
    private final String ADD_RESERVATION="INSERT INTO T_USER_RESERVATION (MAIL,ID_RES) VALUES (?,?)";

    
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }
    
    public List<User> loadAll(){
        List<User> liste= new ArrayList<User>();
        Connection con = null;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setNom(rs.getString("NOM"));
                user.setPrenom(rs.getString("PRENOM"));
                user.setEmail(rs.getString("MAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setTelephone(rs.getString("TELEPHONE"));
                if(rs.getInt("PROFIL")==1){
                    user.setProfil(true);
                }else
                    user.setProfil(false);
                
                user.setBonus(rs.getInt("BONUS"));
                //  rajouter les autres....
                System.out.println("nom : "+user.getNom());
                liste.add(user);
            }
			
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return liste;
    }
    
        public int addUser(User user){
            Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_USER);
            int i=1;
            ps.setString(i++, user.getNom());
            ps.setString(i++, user.getPrenom());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getTelephone());
            if(user.getProfil()){
                ps.setInt(i++,1);
            }else
                ps.setInt(i++,0);
            
            ps.setInt(i, user.getBonus());
            System.out.println("USER : "+user.toString());
            rs = ps.executeUpdate();
            System.out.println("Resultat insertion : "+rs);
            
            
            
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

    public boolean existsUser(String email) {
        Connection con = null;
        boolean result=false;
        
        try {
            // ouvrir la connexion
            con = dataSource.getConnection();
            // preparer la requete
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_EMAIL);
            // remplacer les parametres de la requete
            ps.setString(1, email);
            // executer la requete
            ResultSet rs = ps.executeQuery();
            
            // recuperer le resultat
            result=rs.next();
            System.out.println("USER EXISTS : "+result);
			
            rs.close();
            ps.close();
            
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
    
    public boolean isConnected(User u){
     Connection con = null;
        boolean result=false;
        
        try {
            // ouvrir la connexion
            con = dataSource.getConnection();
            // preparer la requete
            PreparedStatement ps = con.prepareStatement(SELECT_CONNECTED_USER);
            // remplacer les parametres de la requete
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            // executer la requete
            ResultSet rs = ps.executeQuery();
            
            // recuperer le resultat
            result=rs.next();
            System.out.println("USER EXISTS : "+result);
			
            rs.close();
            ps.close();
            
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

    public void addRestaurant(String user, int id_rest) {
        Connection con = null;
        int rs=0;
        
        try {
            // ouvrir la connexion
            con = dataSource.getConnection();
            // preparer la requete
            PreparedStatement ps = con.prepareStatement(ADD_RESTAURANT_TO_USER);
            // remplacer les parametres de la requete
            ps.setString(1, user);
            ps.setInt(2, id_rest);
            // executer la requete
             rs = ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
         
    }

    public User getByMail(String email) {
        Connection con = null;
        User user=null;
        try {
            // ouvrir la connexion
            con = dataSource.getConnection();
            // preparer la requete
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_EMAIL);
            // remplacer les parametres de la requete
            ps.setString(1, email);
            // executer la requete
            ResultSet rs = ps.executeQuery();
            
            // recuperer le resultat
           
             if(rs.next()){
                 user = new User();
                user.setNom(rs.getString("NOM"));
                user.setPrenom(rs.getString("PRENOM"));
                user.setEmail(rs.getString("MAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setTelephone(rs.getString("TELEPHONE"));
                if(rs.getInt("PROFIL")==1){
                    user.setProfil(true);
                }else
                    user.setProfil(false);
                
                user.setBonus(rs.getInt("BONUS"));
                //  rajouter les autres....
                System.out.println("nom : "+user.getNom());
                
            }
             
            
			
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return user;
    }

    public int updateUser(User user) {
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_USER);
            int i=1;
            ps.setString(i++, user.getNom());
            ps.setString(i++, user.getPrenom());
            
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getTelephone());
            if(user.getProfil()){
                ps.setInt(i++,1);
            }else
                ps.setInt(i++,0);
            
            ps.setString(i++, user.getEmail());
            
            rs = ps.executeUpdate();
            
            
            
            
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

    public void addReservation(String user, int idRes) {
         Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(ADD_RESERVATION);
            int i=1;
            ps.setString(i++, user);
           ps.setInt(i++, idRes);
            
            rs = ps.executeUpdate();
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
       
    }
}
