package edu.ncucst.fritemmarket.web.tools;

import java.sql.*;

/**
 * @author shkstart
 * @create 2023--05-10:34
 */

public class JDBCTools {
    /**
     * @Description:
    加载驱动，并建立数据库连接
     * @return: java.sql.Connection
     * @author: chen
     * @date: 2023/1/5 10:43
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class clazz=Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruit";
        String username="root";
        String password="123123";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    /**
     * @Description:
    适合于添、删、改等操作后资源释放
     * @return: void
     * @author: chen
     * @date: 2023/1/5 10:43
     */
    public static void release(Connection conn, Statement stmt){
        if (stmt!=null){
            try {
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            stmt=null;
        }
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            conn=null;
        }
    }
    /**
     * @Description:
查询后资源释放
     * @return:
     * @author: chen
     * @date: 2023/1/5 10:47
     */
    public static void release(Connection conn, Statement stmt, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs=null;
        }
        release(conn,stmt);
    }
}
