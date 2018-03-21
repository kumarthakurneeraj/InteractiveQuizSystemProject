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
import java.sql.Statement;
import java.util.ArrayList;
import techquizapp.dbutil.DbConnection;
import techquizapp.pojo.Performance;
import techquizapp.pojo.StudentScore;

/**
 *
 * @author kumar
 */
public class PerformanceDao {
    public static ArrayList<String> getAllStudentId()throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select distinct userid from performance");
        ArrayList<String> studentList=new ArrayList<>();
        while(rs.next())
        {
           String id=rs.getString(1);
           studentList.add(id);
        }
        return studentList;
    }
    public static ArrayList<String> getAllExamId(String studentId)throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select examid from performance where userid=?");
        ArrayList<String> examIdlist=new ArrayList<>();
        ps.setString(1, studentId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            String id=rs.getString(1);
            examIdlist.add(id);
        }
        return examIdlist;
    }
    public static StudentScore getScore(String studentId,String examId)throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select language,per from performance where userid=? and examid=?");
       ps.setString(1, studentId);
       ps.setString(2, examId);
       ResultSet rs=ps.executeQuery();
       rs.next();
       StudentScore scoreObj=new StudentScore();
       scoreObj.setLanguage(rs.getString(1));
       scoreObj.setPer(rs.getDouble(2));
       return scoreObj;
    }
    public static ArrayList<Performance> getAllData()throws SQLException
    {
        ArrayList<Performance> performanceList=new ArrayList<Performance>();
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from performance");
        while(rs.next())
        {
            String userId=rs.getString(1);
            String examId=rs.getString(2);
            int right=rs.getInt(3);
            int wrong=rs.getInt(4);
            int unattempted=rs.getInt(5);
            double per=rs.getDouble(6);
            String language=rs.getString(7);
            Performance p=new Performance(userId,examId,right,wrong,unattempted,per,language);
            performanceList.add(p);
        }
        return performanceList;
    }
    public static ArrayList<String> getExamIdBySubject(String subjectName) throws SQLException
    {
         ArrayList<String> examIdlist=new ArrayList<>();
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select examid from exam where language=?");
        ps.setString(1, subjectName);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
             String id=rs.getString(1);
           examIdlist.add(id);
        }
        return examIdlist;
    }
    public static void addPerformance(Performance performance) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into performance values(?,?,?,?,?,?,?)");
        ps.setString(1, performance.getUserid());
        ps.setString(2, performance.getExamid());
        ps.setInt(3,performance.getRight());
        ps.setInt(4, performance.getWrong());
        ps.setInt(5, performance.getUnattempted());
        ps.setDouble(6, performance.getPer());
        ps.setString(7, performance.getLanguage());
        ps.executeUpdate();
    }
   
}
