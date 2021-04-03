/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.user;

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
public class UserDAO implements Serializable {

    public boolean createAccount(UserDTO dto) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement preStm = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUser(email, password, fullname, role, status) "
                        + "VALUES(?, ?, ?, ?, ?)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, dto.getEmail());
                preStm.setString(2, dto.getPassword());
                preStm.setString(3, dto.getFullname());
                preStm.setString(4, dto.getRole());
                preStm.setString(5, dto.getStatus());
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

    public boolean checkDupAccount(String email) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT email "
                        + "FROM tblUser "
                        + "WHERE email = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    return false;
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
        return true;
    }

    public UserDTO checkAccount(String email, String password) throws SQLException, NamingException {
        UserDTO dto = null;
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.myConnection();
            if (conn != null) {
                String sql = "SELECT email, password, fullname, role, status "
                        + "FROM tblUser "
                        + "WHERE email = ? AND password = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, email);
                preStm.setString(2, password);
                rs = preStm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    dto = new UserDTO(email, password, role, status, fullname);
                    return dto;
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
        return null;
    }
    
    
    
    
    
    
    
    
}
