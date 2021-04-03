/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.examdetail;

import java.io.Serializable;

/**
 *
 * @author Khoa Pham
 */
public class ExamDetailDTO implements Serializable{
    private String examDetailID;
    private String questionID;
    private String examID;
    private String answerCorrect;
    private String answer;
    private boolean status;

    public ExamDetailDTO() {
    }

    public ExamDetailDTO(String examDetailID, String questionID, String examID, String answerCorrect, String answer, boolean status) {
        this.examDetailID = examDetailID;
        this.questionID = questionID;
        this.examID = examID;
        this.answerCorrect = answerCorrect;
        this.answer = answer;
        this.status = status;
    }

    public String getExamDetailID() {
        return examDetailID;
    }

    public void setExamDetailID(String examDetailID) {
        this.examDetailID = examDetailID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
