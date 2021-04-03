/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.examdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khoapc.ultities.DBHelpers;

/**
 *
 * @author Khoa Pham
 */
public class ExamDetailDAO implements Serializable {

    public void insertExamDetail(ExamDetailDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        try {
            con = DBHelpers.myConnection();
            if (con != null) {
                String sql = "INSERT INTO tblExamDetail(examDetailID,examID, questionID, answerCorrect, answer, status) "
                        + "VALUES (?,?,?,?,?,?)";
                preStm = con.prepareStatement(sql);
                preStm.setString(1, dto.getExamDetailID());
                preStm.setString(2, dto.getExamID());
                preStm.setString(3, dto.getQuestionID());
                preStm.setString(4, dto.getAnswerCorrect());
                preStm.setString(5, dto.getAnswer());
                preStm.setBoolean(6, dto.isStatus());
                preStm.executeUpdate();
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int getTotalDeltailID() throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(examDetailID) "
                        + "FROM tblExamDetail ";
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
}
