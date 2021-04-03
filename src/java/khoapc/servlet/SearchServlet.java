/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class);

    private static final String SEARCH_PAGE = "search.jsp";

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
        String searchValue = request.getParameter("searchValue");
        String id_subject = request.getParameter("subject");
        String status = request.getParameter("status");
        String txtIndex = request.getParameter("index");
        String url = SEARCH_PAGE;

        int index = 0;
        try {
            if (txtIndex == null) {
                index = 1;
            } else {
                index = Integer.parseInt(txtIndex);
            }

            int countQuest = 0;
            int endPage = 0;

            QuestionDAO questDAO = new QuestionDAO();
            if (("All".equals(id_subject) && "All".equals(status)) || (id_subject == null && status == null)) {
                if (searchValue.trim() != null) {
                    countQuest = questDAO.countAllQuest_SearchValue(searchValue);
                    endPage = questDAO.getEndPage(countQuest);
                    request.setAttribute("END_PAGE", endPage);
                    List<QuestionDTO> list = questDAO.getPagingListAllQuestion_SearchValue(index, searchValue);
                    request.setAttribute("LIST", list);
                } else {
                    countQuest = questDAO.countQuest();
                    endPage = questDAO.getEndPage(countQuest);
                    request.setAttribute("END_PAGE", endPage);
                    List<QuestionDTO> list = questDAO.getPagingListAllQuestion(index);
                    request.setAttribute("LIST", list);
                }
            } else {
                if (searchValue.trim() != null) {
                    if ("All".equals(id_subject)) {
                        countQuest = questDAO.countQuest_Status_SearchVal(searchValue, status);
                        endPage = questDAO.getEndPage(countQuest);
                        request.setAttribute("END_PAGE", endPage);
                        List<QuestionDTO> list = questDAO.getPagingListQuestionStatus_SearchVal(index, searchValue, status);
                        request.setAttribute("LIST", list);
                    } else if ("All".equals(status)) {
                        countQuest = questDAO.countQuest_Subject_SearchVal(searchValue, id_subject);
                        endPage = questDAO.getEndPage(countQuest);
                        request.setAttribute("END_PAGE", endPage);
                        List<QuestionDTO> list = questDAO.getPagingListQuestionSubject_SearchVal(index, searchValue, id_subject);
                        request.setAttribute("LIST", list);
                    } else if (!("All".equals(id_subject)) && (!("All".equals(status)))) {
                        countQuest = questDAO.countQuest_SearchVal_Subject_Status(searchValue, id_subject, status);
                        endPage = questDAO.getEndPage(countQuest);
                        request.setAttribute("END_PAGE", endPage);
                        List<QuestionDTO> list = questDAO.getPagingListQuestionSearchVal_Subject_Status(index, searchValue, id_subject, status);
                        request.setAttribute("LIST", list);
                    }
                }
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
