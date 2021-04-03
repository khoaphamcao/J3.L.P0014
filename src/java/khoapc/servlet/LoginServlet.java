/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoapc.subject.SubjectDAO;
import khoapc.subject.SubjectDTO;
import khoapc.ultities.EncryptSHA256;
import khoapc.user.UserDAO;
import khoapc.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private static final String SIGN_UP_PAGE = "signup.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    private static final String DEFAULT_SEARCH_SERVLET = "DefaultSearchServlet";
    private static final String STUDENT_PAGE = "user_home.jsp";

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
        String url = SIGN_UP_PAGE;

        String email = request.getParameter("txtEmail").trim();
        String password = request.getParameter("txtPassword").trim();
        try {
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkAccount(email, EncryptSHA256.toHexString(EncryptSHA256.getSHA(password)));
            if (dto != null) {
                HttpSession session = request.getSession();
                session.setAttribute("ACCOUNT", dto);
                SubjectDAO subDAO = new SubjectDAO();
                List<SubjectDTO> list = subDAO.getSubjectID();
                session.setAttribute("SUBJECT", list);
                String role = dto.getRole().trim();
                if (role.equals("admin")) {
                    url = DEFAULT_SEARCH_SERVLET;
                } else {
                    url = STUDENT_PAGE;
                }
            } else {
                String error = "Invalid name or password!";
                request.setAttribute("ERROR", error);
                url = LOGIN_PAGE;
            }

        } catch (NamingException ex) {
            LOGGER.error("NamingErr " + ex);
        } catch (SQLException ex) {
            LOGGER.error("SQLErr " + ex);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("NoSuchAlgorithmException" + ex);
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
