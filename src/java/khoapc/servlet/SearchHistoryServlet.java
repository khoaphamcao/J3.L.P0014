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
import javax.servlet.http.HttpSession;
import khoapc.exam.ExamDAO;
import khoapc.exam.ExamDTO;
import khoapc.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchHistoryServlet.class);

    private static final String HISTORY_PAGE = "search_history.jsp";
    private static final String HISTORY_SERVLET = "HistoryServlet";

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
        String subject = request.getParameter("subject");
        String txtIndex = request.getParameter("indexHistory");

        int index = 0;
        String url = HISTORY_PAGE;
        try {
            if (txtIndex == null) {
                index = 1;
            } else {
                index = Integer.parseInt(txtIndex);
            }
            int countQuest = 0;
            int endPage = 0;
            ExamDAO dao = new ExamDAO();
            UserDTO dto = null;
            HttpSession session = request.getSession();
            dto = (UserDTO) session.getAttribute("ACCOUNT");
            if (subject.equals("All")) {
                url = HISTORY_SERVLET;
            } else {
                countQuest = dao.countExam_Subject(dto.getEmail(), subject);
                endPage = dao.getEndPage(countQuest);
                if (endPage != 0) {
                    request.setAttribute("END_PAGE", endPage);
                    List<ExamDTO> list = dao.getPagingListExam_Subject(index, dto.getEmail(), subject);
                    request.setAttribute("LIST_EXAM", list);
                    url = HISTORY_PAGE;
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
