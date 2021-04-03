/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.user;

import java.io.Serializable;

/**
 *
 * @author Khoa Pham
 */
public class UserDTO implements Serializable{
    private String email;
    private String password;
    private String role;
    private String status;
    private String fullname;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String role, String status, String fullname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.fullname = fullname;
    }

    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.trim();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
