/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.exam;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import khoapc.ultities.DBHelpers;

/**
 *
 * @author Khoa Pham
 */
public class ExamDAO implements Serializable {

    // GET PAGING HISTORY
    public List<ExamDTO> getPagingListAllExam(int index, String email) throws NamingException, SQLException {
        List<ExamDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT examID, email, date, subject_id, number_correct, point "
                        + "FROM tblExam "
                        + "WHERE email LIKE ? "
                        + "ORDER BY examID "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                preStm.setInt(2, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String examID = rs.getString("examID").trim();
                    Date date = rs.getDate("date");
                    String subject_id = rs.getString("subject_id").trim();
                    int number_correct = rs.getInt("number_correct");
                    float point = rs.getFloat("point");
                    ExamDTO dto = new ExamDTO(examID, email, date, subject_id, number_correct, point);
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    // COUNT EXAM
    public int countExam(String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(examID) "
                        + "FROM tblExam "
                        + "WHERE email LIKE ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    return total;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public int getEndPage(int countQuest) {
        int endPage = 0;
        int pageSize = 3;
        endPage = countQuest / pageSize;
        if (countQuest % pageSize != 0) {
            endPage++;
        }
        return endPage;
    }

    // SEARCH HISTORY BY SUBJECT
    public List<ExamDTO> getPagingListExam_Subject(int index, String email, String subject) throws NamingException, SQLException {
        List<ExamDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT examID, email, date, subject_id, number_correct, point "
                        + "FROM tblExam "
                        + "WHERE email LIKE ? "
                        + "AND subject_id = ? "
                        + "ORDER BY examID "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                preStm.setString(2, subject);
                preStm.setInt(3, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String examID = rs.getString("examID").trim();
                    Date date = rs.getDate("date");
                    String subject_id = rs.getString("subject_id").trim();
                    int number_correct = rs.getInt("number_correct");
                    float point = rs.getFloat("point");
                    ExamDTO dto = new ExamDTO(examID, email, date, subject_id, number_correct, point);
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    //COUNT EXAM BY SUBJECT
    public int countExam_Subject(String email, String subject) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(examID) "
                        + "FROM tblExam "
                        + "WHERE email LIKE ? "
                        + "AND subject_id = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                preStm.setString(2, subject);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    return total;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    //INSERT RESULT TO EXAM
    public boolean createExamResult(ExamDTO dto) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement preStm = null;

        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblExam (examID, email, date, subject_id) "
                        + "VALUES (?,?,?,?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getExamID());
                preStm.setString(2, dto.getEmail());
                preStm.setDate(3, new java.sql.Date(dto.getDate().getTime()));
                preStm.setString(4, dto.getSubjectID());
                int row = preStm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public void updateExamResult(float mark, int numberCorrect, String examID) throws NamingException, SQLException{
        Connection conn = null;
        PreparedStatement preStm = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "UPDATE tblExam "
                        + "SET point = ? , number_correct = ? "
                        + "WHERE examID = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setFloat(1, mark);
                preStm.setInt(2, numberCorrect);
                preStm.setString(3, examID);
                preStm.executeUpdate();
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getExamID() throws NamingException, SQLException {
        String examID = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT TOP(1) examID "
                        + "FROM tblExam "
                        + "ORDER BY examID DESC ";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    examID = rs.getString("examID");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return examID;
    }
}
