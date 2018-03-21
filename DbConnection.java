/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kumar
 */
public class DbConnection {
    private static Connection conn;
    static
    {
     try
     {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn=DriverManager.getConnection("jdbc:oracle:thin:@//Niraj:1521/xe", "project","java");
         JOptionPane.showMessageDialog(null,"Connected Successfully","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
     }
     catch(ClassNotFoundException ex)
     {
         JOptionPane.showMessageDialog(null, "Error Connecting To The Database"+ex, "ERROR!",JOptionPane.ERROR_MESSAGE);
         System.out.println("Exception is:"+ex);
     }
     catch(SQLException ex)
     {
        JOptionPane.showMessageDialog(null, "Error Connecting To The Database"+ex, "ERROR!",JOptionPane.ERROR_MESSAGE);
         System.out.println("Exception is:"+ex);
     }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
    if(conn==null)
    {
        JOptionPane.showMessageDialog(null,"Connection failed","ERROR!",JOptionPane.ERROR_MESSAGE);
        System.out.println("Connection Failed");
    }
    else
    {
        try{
        conn.close();
        }
        catch(SQLException ex)
        {
               JOptionPane.showMessageDialog(null,"Connection failed"+ex,"ERROR!",JOptionPane.ERROR_MESSAGE);
        System.out.println("Connection Failed"+ex); 
        }
    }
}
}
