/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoapc.question.QuestionDAO;
import khoapc.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateServlet.class);
    private static final String UPDATE_PAGE = "update.jsp";

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
        String url = UPDATE_PAGE;
        String idQuestion = request.getParameter("id_question");
        String questionContent = request.getParameter("txtQuestionContent").trim();
        String txtAnswer1 = request.getParameter("txtAnswer1").trim();
        String txtAnswer2 = request.getParameter("txtAnswer2").trim();
        String txtAnswer3 = request.getParameter("txtAnswer3").trim();
        String txtAnswer4 = request.getParameter("txtAnswer4").trim();
        String correctAnswer = request.getParameter("cbTrueAnswer");
        String txtIDSubject = request.getParameter("cbSubject");
        String status = request.getParameter("cbStatus");
        
        String[] split = txtIDSubject.split("-");
        String idSubject = split[0].trim();
        try {
            QuestionDTO dto = new QuestionDTO(idQuestion, idSubject, questionContent, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4, correctAnswer, status);
            QuestionDAO dao = new QuestionDAO();
            boolean check = dao.updateQuestion(dto);
            if (check){
                url = UPDATE_PAGE; 
                request.setAttribute("UPDATE_NOTI", "Update Question Successful");
            }else{
                request.setAttribute("UPDATE_NOTI", "Update Question Fail");
            }
        } catch (NamingException ex) {
            LOGGER.error("NamingErr " + ex);
        } catch (SQLException ex) {
            LOGGER.error("SQLErr " + ex);
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
