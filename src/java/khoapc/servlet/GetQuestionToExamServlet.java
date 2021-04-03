/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoapc.question.QuestionDAO;
import khoapc.question.QuestionDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "GetQuestionToExamServlet", urlPatterns = {"/GetQuestionToExamServlet"})
public class GetQuestionToExamServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetQuestionToExamServlet.class);

    private static final String NEXT_QUESTION_SERVLET = "NextQuestionServlet";

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
        String id_subject = request.getParameter("subject").trim();
        String url = NEXT_QUESTION_SERVLET;

        try {
            int questionRemain = 0;
            int time = 0;
            int key = 0;

            Map<Integer, QuestionDTO> questionBank = new HashMap<>();
            Map<Integer, String> listAnswer = new HashMap<>();

            HttpSession session = request.getSession();

            if ("PRJ311".equals(id_subject)) {
                questionRemain = 40;
                time = 10;
                for (int i = 0; i < 40; i++) {
                    listAnswer.put(i, null);
                }
            } else {
                questionRemain = 50;
                time = 80 * 60;
                for (int i = 0; i < 50; i++) {
                    listAnswer.put(i, null);
                }
            }
            List<QuestionDTO> questionList = new ArrayList<>(questionRemain);
            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> listQuest = dao.getListQuestionToRanDom(id_subject);
            if (listQuest != null) {
                for (int i = 0; i < listAnswer.size(); i++) {
                    Random rand = new Random();
                    QuestionDTO randQuest = listQuest.get(rand.nextInt(listQuest.size()));
                    questionList.add(randQuest);
                }

                do {
                    for (int i = 0; i < questionList.size(); i++) {
                        questionBank.put(key, questionList.get(i));
                        key++;
                    }
                    questionRemain -= questionList.size();
                } while (questionRemain != 0);

                session.setAttribute("QUESTION_BANK", questionBank);
                session.setAttribute("STUDENT_ANSWER_LIST", listAnswer);
                session.setAttribute("TIME", time);
                session.setAttribute("ID_SUBJECT", id_subject);
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
