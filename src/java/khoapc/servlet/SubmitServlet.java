/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
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
import khoapc.examdetail.ExamDetailDAO;
import khoapc.examdetail.ExamDetailDTO;
import khoapc.question.QuestionDTO;
import khoapc.user.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "SubmitServlet", urlPatterns = {"/SubmitServlet"})
public class SubmitServlet extends HttpServlet {

    private static final String RESULT_PAGE = "result.jsp";
    private static final Logger LOGGER = Logger.getLogger(SubmitServlet.class);

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
        String url = RESULT_PAGE;
        try {
            int correctAnswer = 0;
            Date date = new Date();
            String email = null;
            String examID = null;

            HttpSession session = request.getSession();
            String id_subject = (String) session.getAttribute("ID_SUBJECT");
            UserDTO user = (UserDTO) session.getAttribute("ACCOUNT");
            Map<Integer, String> listAnswer = (Map<Integer, String>) session.getAttribute("STUDENT_ANSWER_LIST");
            Map<Integer, QuestionDTO> questionBank = (Map<Integer, QuestionDTO>) session.getAttribute("QUESTION_BANK");
            if (user != null) {
                email = user.getEmail();
            }

            ExamDAO examDAO = new ExamDAO();
            String oldExamID = examDAO.getExamID();
            if (oldExamID == null) {
                examID = "E-1";
            } else {
                String[] arr = oldExamID.split("-");
                int index = Integer.parseInt(arr[1].trim()) + 1;
                examID = "E-" + index;
            }
            ExamDTO examDTO = new ExamDTO(examID, email, date, id_subject);
            boolean check = examDAO.createExamResult(examDTO);
            if (check) {
                ExamDetailDAO examDetailDAO = new ExamDetailDAO();
                int index = 0;
                for (int i = 0; i < listAnswer.size(); i++) {
                    index = examDetailDAO.getTotalDeltailID();
                    String detailID = Integer.toString(index + 1);
                    if (questionBank.get(i).getAnswer_Correct().equals(listAnswer.get(i))) {
                        ExamDetailDTO examDetailDTO = new ExamDetailDTO(detailID, questionBank.get(i).getId_Question(), examID, questionBank.get(i).getAnswer_Correct(), listAnswer.get(i), true);
                        examDetailDAO.insertExamDetail(examDetailDTO);
                        correctAnswer++;
                    } else {
                        ExamDetailDTO examDetailDTO = new ExamDetailDTO(detailID, questionBank.get(i).getId_Question(), examID, questionBank.get(i).getAnswer_Correct(), listAnswer.get(i), false);
                        examDetailDAO.insertExamDetail(examDetailDTO);
                    }
                }
                float marks = (float) (Math.round((10.0 / listAnswer.size() * correctAnswer) * 100.0) / 100.0);
                examDAO.updateExamResult(marks, correctAnswer, examID);
                request.setAttribute("CORRECT_ANSWERS", correctAnswer);
                request.setAttribute("MARKS", marks);
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
