/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {

    private static final String CREATE_PAGE = "create_question.jsp";

    private static final Logger LOGGER = Logger.getLogger(CreateServlet.class);

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
        String txtSubject = request.getParameter("cbSubject").trim();
        String txtQuestionContent = request.getParameter("txtQuestionContent").trim();
        String txtAnswer1 = request.getParameter("txtAnswer1").trim();
        String txtAnswer2 = request.getParameter("txtAnswer2").trim();
        String txtAnswer3 = request.getParameter("txtAnswer3").trim();
        String txtAnswer4 = request.getParameter("txtAnswer4").trim();
        String trueAnswer = request.getParameter("cbTrueAnswer").trim();

        Date createDate = new Date();
        String status = "Active";

        String url = CREATE_PAGE;

        String[] array = txtSubject.split("-");
        String idSubject = array[0].trim();
        try {
            QuestionDAO questDAO = new QuestionDAO();
            int totalQuest = questDAO.countQuestionGetProject();
            String idQuest = "Q-" + totalQuest++;
            QuestionDTO dto = new QuestionDTO(idQuest, idSubject, txtQuestionContent, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4, trueAnswer, status, createDate);
            boolean check = questDAO.createQuestion(dto);
            if (check) {
                request.setAttribute("NOTI", "Create Question Success");
            } else {
                request.setAttribute("NOTI", "Create Question Fail");
            }
        } catch (SQLException ex) {
            LOGGER.error("SQLErr " + ex);
        } catch (NamingException ex) {
            LOGGER.error("NamingErr " + ex);
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
