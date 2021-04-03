/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.subject;

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
public class SubjectDAO implements Serializable{
    public List<SubjectDTO> getSubjectID() throws NamingException, SQLException{
        List<SubjectDTO> list = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT id_subject, subject_name " +
                                "FROM tblSubject " +
                                "ORDER BY id_subject ";
                preStm = conn.prepareStatement(sql);
                rs = preStm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String idSubject = rs.getString("id_subject");
                    String subjectName = rs.getString("subject_name");
                    SubjectDTO dto = new SubjectDTO(idSubject, subjectName);
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null){
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
