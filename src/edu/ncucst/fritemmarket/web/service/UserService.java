package edu.ncucst.fritemmarket.web.service;

import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.beans.User;
import edu.ncucst.fritemmarket.web.dao.FruitDao;
import edu.ncucst.fritemmarket.web.dao.UserDao;
import edu.ncucst.fritemmarket.web.tools.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author shkstart
 * @create 2023--06-14:45
 */
/**
 * @Description:
过滤
 * @return:
 * @author: chen
 * @date: 2023/1/6 15:09
 */
public class UserService {
    private UserDao dao=new UserDao();
    public String login(String name,String password){
        UserDao dao=new UserDao();
        if (name!=null&&name.length()>0){
            return dao.login(name,password);
        }else {
            return "5";
        }
    }
    public ArrayList<User> queryAllUser(){
        ArrayList<User> users=dao.queryAllUser();
        return users;
    }
    public boolean updateUser(User user) {
        dao.updateUser(user);
        return true;
    }
    public boolean addUser(User user){
        ArrayList<User> users=queryAllUser();
        for(User data:users){
            if(user.getUsername().equals(data.getUsername()))
                return false;
        }
        dao.addUser(user);
        return true;
    }

}
