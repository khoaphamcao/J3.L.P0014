/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoapc.ultities.DBHelpers;

/**
 *
 * @author Khoa Pham
 */
public class QuestionDAO implements Serializable {

    //CREATE
    public int countQuestionGetProject() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion ";
                preStm = conn.prepareStatement(sql);
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

    public boolean createQuestion(QuestionDTO dto) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblQuestion(id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, createDate, status)  "
                        + "VALUES  (?,?,?,?,?,?,?,?,?,?) ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getId_Question());
                preStm.setString(2, dto.getId_Subject());
                preStm.setString(3, dto.getQuestion_Content());
                preStm.setString(4, dto.getAnswer_Content_1());
                preStm.setString(5, dto.getAnswer_Content_2());
                preStm.setString(6, dto.getAnswer_Content_3());
                preStm.setString(7, dto.getAnswer_Content_4());
                preStm.setString(8, dto.getAnswer_Correct());
                preStm.setDate(9, new java.sql.Date(dto.getCreateDate().getTime()));
                preStm.setString(10, dto.getStatus());
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

    //SEARCH  
    //GET ALL QUESTTION
    public List<QuestionDTO> getPagingListAllQuestion(int index) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct");
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    public int countQuest() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion ";
                preStm = conn.prepareStatement(sql);
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

    //SEARCH ALL BY SEARCHVALUE
    public List<QuestionDTO> getPagingListAllQuestion_SearchValue(int index, String questionName) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, '%' + questionName + '%');
                preStm.setInt(2, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct").trim();
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    //COUNT QUEST by status and subject
    public int countAllQuest_SearchValue(String searchValue) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
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

    //SEARCH BY STATUS AND SUBJECT
    public List<QuestionDTO> getPagingListQuestionStatus_Subject(int index, String questionName, String subject, String statusQuest) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND status = ? "
                        + "AND id_subject = ? "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, '%' + questionName + '%');
                preStm.setString(2, statusQuest);
                preStm.setString(3, subject);
                preStm.setInt(4, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct");
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    //COUNT QUEST by status and subject
    public int countQuest_Status_Subject(String searchValue, String status, String subject) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND status = ? "
                        + "AND id_subject = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
                preStm.setString(2, status);
                preStm.setString(3, subject);
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

    //SEARCH BY STATUS AND SEARCH VALUE
    public List<QuestionDTO> getPagingListQuestionStatus_SearchVal(int index, String searchVal, String statusQuest) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND status = ? "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, '%' + searchVal + '%');
                preStm.setString(2, statusQuest);
                preStm.setInt(3, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct");
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    public int countQuest_Status_SearchVal(String searchValue, String status) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND status = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
                preStm.setString(2, status);
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

    //SEARCH BY SUBJECT AND SEARCH VALUE
    public List<QuestionDTO> getPagingListQuestionSubject_SearchVal(int index, String searchVal, String subject) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND id_subject = ? "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, '%' + searchVal + '%');
                preStm.setString(2, subject);
                preStm.setInt(3, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct");
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    public int countQuest_Subject_SearchVal(String searchValue, String subject) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND id_subject = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
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

    //SEARCH BY STATUS/SUBJECT/SEARCHVAL
    public List<QuestionDTO> getPagingListQuestionSearchVal_Subject_Status(int index, String searchVal, String subject, String statusQuest) throws NamingException, SQLException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, id_subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND id_subject = ? "
                        + "AND status = ? "
                        + "ORDER BY question_content "
                        + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, '%' + searchVal + '%');
                preStm.setString(2, subject);
                preStm.setString(3, statusQuest);
                preStm.setInt(4, (index * 3 - 3));
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id_Question = rs.getString("id_question").trim();
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct");
                    String status = rs.getString("status").trim();
                    QuestionDTO dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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

    public int countQuest_SearchVal_Subject_Status(String searchValue, String subject, String status) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(id_question) "
                        + "FROM tblQuestion "
                        + "WHERE question_content LIKE ? "
                        + "AND id_subject = ? "
                        + "AND status = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + searchValue + "%");
                preStm.setString(2, subject);
                preStm.setString(3, status);
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

    //DELETE
    public boolean deleteQuestion(String id_question) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement preStm = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "UPDATE tblQuestion "
                        + "SET status = 'deActive' "
                        + "WHERE id_question = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, id_question);
                int row = preStm.executeUpdate();
                if (row > 0) {
                    check = true;
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
        return check;
    }

    //GET QUESTION BY ID
    public QuestionDTO getQuestionByID(String id_Question) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        QuestionDTO dto = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT question_content, id_subject, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status "
                        + "FROM tblQuestion "
                        + "WHERE id_question = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, id_Question);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    String id_Subject = rs.getString("id_subject").trim();
                    String question_content = rs.getString("question_content").trim();
                    String answer_content_1 = rs.getString("answer_content_1").trim();
                    String answer_content_2 = rs.getString("answer_content_2").trim();
                    String answer_content_3 = rs.getString("answer_content_3").trim();
                    String answer_content_4 = rs.getString("answer_content_4").trim();
                    String answer_correct = rs.getString("answer_correct").trim();
                    String status = rs.getString("status").trim();
                    dto = new QuestionDTO(id_Question, id_Subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct, status);
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
        return dto;
    }

    public boolean updateQuestion(QuestionDTO dto) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement preStm = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "UPDATE tblQuestion "
                        + "SET question_content = ? , answer_content_1 = ?, answer_content_2 = ?, answer_content_3 = ?, answer_content_4 = ?, answer_correct = ?, status = ?, id_subject = ? "
                        + "WHERE id_question = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getQuestion_Content().trim());
                preStm.setString(2, dto.getAnswer_Content_1().trim());
                preStm.setString(3, dto.getAnswer_Content_2().trim());
                preStm.setString(4, dto.getAnswer_Content_3().trim());
                preStm.setString(5, dto.getAnswer_Content_4().trim());
                preStm.setString(6, dto.getAnswer_Correct().trim());
                preStm.setString(7, dto.getStatus().trim());
                preStm.setString(8, dto.getId_Subject().trim());
                preStm.setString(9, dto.getId_Question().trim());
                int row = preStm.executeUpdate();
                if (row > 0) {
                    check = true;
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
        return check;
    }

    //GET LIST QUESTION BASE ON SUBJECT TO RANDOM 
    public List<QuestionDTO> getListQuestionToRanDom(String subject) throws SQLException, NamingException {
        List<QuestionDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_question, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct "
                        + "FROM tblQuestion "
                        + "WHERE id_subject = ? "
                        + "AND status = 'Active'";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, subject);
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String question_id = rs.getString("id_question");
                    String question_content = rs.getString("question_content");
                    String answer_content_1 = rs.getString("answer_content_1");
                    String answer_content_2 = rs.getString("answer_content_2");
                    String answer_content_3 = rs.getString("answer_content_3");
                    String answer_content_4 = rs.getString("answer_content_4");
                    String answer_correct = rs.getString("answer_correct");
                    QuestionDTO dto = new QuestionDTO(question_id, subject, question_content, answer_content_1, answer_content_2, answer_content_3, answer_content_4, answer_correct);
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

}
