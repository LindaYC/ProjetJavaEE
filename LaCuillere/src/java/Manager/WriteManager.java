/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.UserDao;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class WriteManager {

    private UserDao userDao;
    
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
    
}
