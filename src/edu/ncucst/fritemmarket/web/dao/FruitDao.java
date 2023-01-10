package edu.ncucst.fritemmarket.web.dao;



import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.tools.JDBCTools;
import org.springframework.test.context.jdbc.Sql;

import java.net.CookieHandler;
import java.sql.*;
import java.util.ArrayList;

public class FruitDao {
    //查询所有数据
    public ArrayList<Fruit> queryAllFruit(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Fruit> fruits=new ArrayList<Fruit>();
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="select * from fruit";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Fruit fruit=new Fruit();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("name"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                fruits.add(fruit);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }

    //条件查询
    public ArrayList<Fruit> queryFruitByCond(Fruit fruit){
        //select * from fruit
        //select * from fruit where number='001'
        //select * from fruit where name like '%果%' and price='5'
        //select * from fruit where 1=1 and name like '%果%' and price='5'
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Fruit> fruits=new ArrayList<Fruit>();
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();

            String sql="select * from fruit where 1=1";
            if(fruit.getNumber()!=null&&fruit.getNumber().length()>0)
                sql=sql+" and number='"+fruit.getNumber()+"'";
            if(fruit.getName()!=null&&fruit.getName().length()>0)
                sql=sql+" and name like '%"+fruit.getName()+"%'";
            if(fruit.getPrice()!=0)
                sql=sql+" and price='"+fruit.getPrice()+"'";
            if(fruit.getUnit()!=null&&fruit.getUnit().length()>0)
                sql=sql+" and unit like '%"+fruit.getUnit()+"%'";

            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Fruit date=new Fruit();
                date.setNumber(rs.getString("number"));
                date.setName(rs.getString("name"));
                date.setPrice(rs.getDouble("price"));
                date.setUnit(rs.getString("unit"));
                fruits.add(date);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }


    // 按条件排序方法（默认升序）
    public ArrayList<Fruit> sortFruits(String sort){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        ArrayList<Fruit> fruits=new ArrayList<>();

        try {
            conn= JDBCTools.getConnection();

            String sql="select * from fruit order by " + sort;

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while(rs.next()){
                Fruit fruit=new Fruit();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("name"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                fruits.add(fruit);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
        return null;
    }



    // 按条件排序方法（降序）
    public ArrayList<Fruit> sortFruitsByDesc(String sort){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        ArrayList<Fruit> fruits=new ArrayList<>();

        try {
            conn= JDBCTools.getConnection();

            String sql="select * from fruit order by "+ sort +" desc ";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while(rs.next()){
                Fruit fruit=new Fruit();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("name"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                fruits.add(fruit);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
        return null;
    }

    //添加数据
    public void addFruit(Fruit fruit){
        //Statement stmt = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn= JDBCTools.getConnection();
            //stmt=conn.createStatement();
            //String sql="insert into fruit(number,name,price,unit) values('"+fruit.getNumber()+"','"+fruit.getName()
            //        +"','"+fruit.getPrice()+"','"+fruit.getUnit()+"')";
            //int num=stmt.executeUpdate(sql);
            String sql="insert into fruit(number,name,price,unit) values(?,?,?,?)";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,fruit.getNumber());
            pstmt.setString(2,fruit.getName());
            pstmt.setDouble(3,fruit.getPrice());
            pstmt.setString(4,fruit.getUnit());
            int num = pstmt.executeUpdate();
            if(num>0) {
                System.out.println("添加数据成功！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }
    }

    //修改数据
    public void updateFruit(Fruit fruit){
        //Statement stmt = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn= JDBCTools.getConnection();
            //stmt=conn.createStatement();
            //String sql="update fruit set name='"+fruit.getName()+"',price='"+fruit.getPrice()
            //        +"',unit='"+fruit.getUnit()+"' where number='"+fruit.getNumber()+"'";
            //int num=stmt.executeUpdate(sql);
            String sql="update fruit set name=?,price=?,unit=? where number=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,fruit.getName());
            pstmt.setDouble(2,fruit.getPrice());
            pstmt.setString(3,fruit.getUnit());
            pstmt.setString(4,fruit.getNumber());
            int num = pstmt.executeUpdate();
            if(num>0)
                System.out.println("修改数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }
    }

    //删除数据
    public void delFruit(String delNumber){
        //Statement stmt = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn= JDBCTools.getConnection();
            //stmt=conn.createStatement();
            //String sql="delete from fruit where number='"+delNumber+"'";
            //int num=stmt.executeUpdate(sql);
            String sql="delete from fruit where number=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,delNumber);
            int num = pstmt.executeUpdate();
            if(num>0)
                System.out.println("删除数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }

    }

}
