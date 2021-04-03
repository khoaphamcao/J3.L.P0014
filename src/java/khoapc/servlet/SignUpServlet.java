/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoapc.ultities.EncryptSHA256;
import khoapc.user.UserDAO;
import khoapc.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private static final String SIGN_UP = "signup.jsp";
    private static final Logger LOGGER = Logger.getLogger(SignUpServlet.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("txtEmail").trim();
        String password = request.getParameter("txtPassword").trim();
        String fullname = request.getParameter("txtFullname").trim();
        String repeatPassword = request.getParameter("txtRepeatPassword");
        String role = "student";
        String status = "New";

        String url = SIGN_UP;
        try {
            String notification = null;
            UserDTO dto = new UserDTO(email, EncryptSHA256.toHexString(EncryptSHA256.getSHA(password)), role, status, fullname);
            UserDAO dao = new UserDAO();
            boolean checkDupID = dao.checkDupAccount(email);
            if (checkDupID) {
                if (password.equals(repeatPassword)) {
                    boolean check = dao.createAccount(dto);
                    if (check) {
                        notification = "Create Account Successful!";
                        request.setAttribute("NOTIFICATION", notification);
                    } else {
                        notification = "Create Account Fail";
                        request.setAttribute("NOTIFICATION", notification);
                    }
                }else{
                    notification = "Your repeat pasword is not correct";
                    request.setAttribute("NOTIFICATION", notification);
                    url = SIGN_UP;
                }
            } else {
                notification = "This Email have been used! Please try another email";
                request.setAttribute("NOTIFICATION", notification);
            }
        } catch (SQLException ex) {
            LOGGER.error("SQLErr " + ex);
        } catch (NamingException ex) {
            LOGGER.error("NamingErr " + ex);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("NoSuchAlgorithmException " + ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
