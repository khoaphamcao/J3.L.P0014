/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoapc.question.QuestionDTO;

/**
 *
 * @author Khoa Pham
 */
@WebServlet(name = "NextQuestionServlet", urlPatterns = {"/NextQuestionServlet"})
public class NextQuestionServlet extends HttpServlet {

    private static final String EXAM_PAGE = "exam.jsp";
    private static final String SUBMIT_SERVLET = "SubmitServlet";
    
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
        String url = EXAM_PAGE;
        
        String txtAnswer = request.getParameter("answer");
        String txtCurrentQuestion = request.getParameter("currentQuestion");
        String btAction = request.getParameter("btAction");
        
        String questNum = request.getParameter("questNum");
        String questCount = request.getParameter("questionNumber");
        
        String time = request.getParameter("time");
        
        
        
        try {
            int questionNumber = 0;
            int currentQuestion = 0;
            int timeRemain = 0;
            String answer = null;

            HttpSession session = request.getSession();
            Map<Integer, String> listStudentAnswer = (Map<Integer, String>) session.getAttribute("STUDENT_ANSWER_LIST");
            Map<Integer, QuestionDTO> questionBank = (Map<Integer, QuestionDTO>) session.getAttribute("QUESTION_BANK");
            
            if(txtAnswer != null){
                answer = txtAnswer;
                currentQuestion = Integer.parseInt(txtCurrentQuestion);
                listStudentAnswer.put(currentQuestion, answer);
            }
            if(btAction != null){
                url = SUBMIT_SERVLET;
            }else{
                if(questNum != null){
                    questionNumber = Integer.parseInt(questNum) - 1;
                }else if(questCount != null){
                    questionNumber = Integer.parseInt(questCount) - 1;
                }
                
                if(time != null){
                    timeRemain = Integer.parseInt(time);
                    session.setAttribute("TIME", timeRemain);
                }
                QuestionDTO question = questionBank.get(questionNumber);
                session.setAttribute("QUESTION", question);
                request.setAttribute("CURRENT_QUESTION", questionNumber);
            }
            session.setAttribute("STUDENT_ANSWER_LIST", listStudentAnswer);
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
