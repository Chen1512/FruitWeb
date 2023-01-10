package edu.ncucst.fritemmarket.web.dao;

import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.beans.User;
import edu.ncucst.fritemmarket.web.tools.JDBCTools;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author shkstart
 * @create 2023--06-14:45
 */
public class UserDao {
    public String login(String name,String password){
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        String sql="select * from user where username=? and password=?";
        //存在Mysql注入攻击风险
        //Statement pstmt=null;
        //String sql="select * from user where username='"+name+"' and password='"+password+"'";
        try {
            conn= JDBCTools.getConnection();
            //pstmt=conn.createStatement();
            //rs=pstmt.executeQuery(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return "1";
            }else {
                return "2";
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return "3";
        } catch (SQLException e) {
            e.printStackTrace();
            return "4";
        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
    }

    public void updateUser(User user){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn= JDBCTools.getConnection();
            String sql="update user set password=? where username=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,user.getPassword());
            pstmt.setString(2,user.getUsername());
            int num = pstmt.executeUpdate();
            if(num>0) {
                System.out.println("修改数据成功！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt);
        }
    }


    public void addUser(User user){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn= JDBCTools.getConnection();
            String sql="insert into user(username,password) values(?,?)";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            int num = pstmt.executeUpdate();
            if(num>0) {
                System.out.println("添加数据成功！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt);
        }
    }
    /**
     * @Description:
查询所有用户
     * @return: java.util.ArrayList<edu.ncucst.fritemmarket.web.beans.User>
     * @author: chen
     * @date: 2023/1/8 16:43
     */
    public ArrayList<User> queryAllUser(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<User> users=new ArrayList<User>();
        try {
            conn= JDBCTools.getConnection();
            String sql="select * from user";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery(sql);
            while(rs.next()){
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
        return null;
    }
}
