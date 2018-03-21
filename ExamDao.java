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
import techquizapp.pojo.Exam;

/**
 *
 * @author kumar
 */
public class ExamDao {
    public static String getExamId() throws SQLException
    {
        int rowcount=0;
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select count(*) as totalrows from Exam");
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            rowcount=rs.getInt(1);
        String newId="EX-"+(rowcount+1);
        return newId;
    }
    public static void addExam(Exam newExam) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into exam values(?,?,?)");
        ps.setString(1, newExam.getExamId());
        ps.setString(2, newExam.getLanguage());
        ps.setInt(3, newExam.getTotalQuestion());
        ps.executeUpdate();
    }
    public static ArrayList<String> getExamIdBySubject(String subject) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from exam where language=?");
        ps.setString(1, subject);
        ArrayList<String> examList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            examList.add(rs.getString(1));  
        }
        return examList;
    }
    public static int getQuestionCountByExam(String examId) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select totalquestion from exam where examid=?");
        ps.setString(1, examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        int rowCount=rs.getInt(1);
        return rowCount;
    }
    
}
