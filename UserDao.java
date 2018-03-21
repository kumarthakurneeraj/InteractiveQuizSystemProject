/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techquizapp.dbutil.DbConnection;
import techquizapp.pojo.User;

/**
 *
 * @author kumar
 */
public class UserDao {
    public static boolean validateUser(User user) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid=? and password=? and usertype=?");
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getUserType());
        if(rs.next()){
            
            return true;
        }
        else
            return false;
        
    }
     public static boolean registerStudent(User e) throws SQLException
    {
        boolean status=true;
        Connection conn=DbConnection.getConnection();
        PreparedStatement pst=conn.prepareStatement("select * from users where userid=?");
        pst.setString(1, e.getUserName());
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            status =false;
        }
        else
        {
        pst=conn.prepareStatement("insert into users values(?,?,?)");
        pst.setString(1, e.getUserName());
        pst.setString(2, e.getPassword());
        pst.setString(3, e.getUserType());
        pst.executeUpdate();
        }
        return status;
        
    }
     public static boolean changePassword(User user,String password) throws SQLException
     {
         Connection conn=DbConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("update users set password=? where userId=? and password=? and usertype=?");
         ps.setString(1, user.getPassword());
         ps.setString(2, user.getUserName());
         ps.setString(3, password);
         ps.setString(4, user.getUserType());
         ResultSet rs=ps.executeQuery();
         if(rs.next())
             return true;
         return
                 false;
     }
}
