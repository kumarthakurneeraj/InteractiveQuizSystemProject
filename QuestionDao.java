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
import techquizapp.pojo.Question;
import techquizapp.pojo.QuestionStore;

/**
 *
 * @author kumar
 */
public class QuestionDao {
    public static void addQuestions(QuestionStore qStore) throws SQLException
    {
        String qry="Insert into questions values(?,?,?,?,?,?,?,?,?)";
        ArrayList<Question> questionList = qStore.getAllQuestion();
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        for(Question q:questionList)
        {
            ps.setString(1, q.getExamid());
            ps.setInt(2, q.getQno());
            ps.setString(3, q.getQuestion());
            ps.setString(4, q.getAnswer1());
            ps.setString(5, q.getAnswer2());
            ps.setString(6, q.getAnswer3());
            ps.setString(7, q.getAnswer4());
            ps.setString(8, q.getCorrectAnswer());
            System.out.println("In addQuestions dao "+q.getCorrectAnswer());
            ps.setString(9, q.getLanguage());
            ps.executeUpdate();
        }
    }
    public static ArrayList<Question>  getQuestionByExamId(String examId) throws SQLException
    {
        ArrayList<Question> questionList=new ArrayList<>();
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from questions where examid=? order by qno");
        ps.setString(1, examId);
        ResultSet rs=ps.executeQuery();
        System.out.println("Exam id in dao:"+examId);
        while(rs.next())
        {
            System.out.println("hello");
            int qno=rs.getInt(2);
            String question=rs.getString(3);
            String option1=rs.getString(4);
            String option2=rs.getString(5);
            String option3=rs.getString(6);
            String option4=rs.getString(7);
            String correctAnswer=rs.getString(8);
            String language=rs.getString(9);
            Question obj=new Question(examId,qno,language,option1, option2, option3, option4, correctAnswer,question);
            questionList.add(obj);
            
        }
        System.out.println("Total Question in dao:"+questionList.size());
        return questionList;
    }
    public static void updateQuestions( QuestionStore qStore) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update questions set question=?, answer1=?, answer2=?,answer3=?,answer4=?,correctanswer=? where examid=? and qno=?");
        ArrayList<Question> questionList=qStore.getAllQuestion();
        for(Question obj:questionList)
        {
            ps.setString(1, obj.getQuestion());
            ps.setString(2, obj.getAnswer1());
            ps.setString(3, obj.getAnswer2());
            ps.setString(4, obj.getAnswer3());
            ps.setString(5, obj.getAnswer4());
            ps.setString(6, obj.getCorrectAnswer());
            ps.setString(7,obj.getExamid());
            ps.setInt(8,obj.getQno());
            ps.executeUpdate();
        }
    }
}
