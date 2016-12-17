/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.UserDao;
import java.util.List;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class ReadManager {
    
    private UserDao userDao;
    
  
    
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
        System.out.println("Injection de dependance userDao");
    }
    
    public List<User> getAllUser(){
        return userDao.loadAll();
    }

    
}
