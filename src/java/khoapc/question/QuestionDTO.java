/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.question;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Khoa Pham
 */
public class QuestionDTO implements Serializable{
    private String id_Question;
    private String id_Subject;
    private String question_Content;
    private String answer_Content_1;
    private String answer_Content_2;
    private String answer_Content_3;
    private String answer_Content_4;
    private String answer_Correct;
    private String status;
    private Date createDate;

    public QuestionDTO() {
    }

    public QuestionDTO(String id_Question, String answer_Correct) {
        this.id_Question = id_Question;
        this.answer_Correct = answer_Correct;
    }
    
    

    public QuestionDTO(String id_Question, String id_Subject, String question_Content, String answer_Content_1, String answer_Content_2, String answer_Content_3, String answer_Content_4, String answer_Correct) {
        this.id_Question = id_Question;
        this.id_Subject = id_Subject;
        this.question_Content = question_Content;
        this.answer_Content_1 = answer_Content_1;
        this.answer_Content_2 = answer_Content_2;
        this.answer_Content_3 = answer_Content_3;
        this.answer_Content_4 = answer_Content_4;
        this.answer_Correct = answer_Correct;
    }

    

    
    
    
    
    public QuestionDTO(String id_Question, String id_Subject, String question_Content, String answer_Content_1, String answer_Content_2, String answer_Content_3, String answer_Content_4, String answer_Correct, String status) {
        this.id_Question = id_Question;
        this.id_Subject = id_Subject;
        this.question_Content = question_Content;
        this.answer_Content_1 = answer_Content_1;
        this.answer_Content_2 = answer_Content_2;
        this.answer_Content_3 = answer_Content_3;
        this.answer_Content_4 = answer_Content_4;
        this.answer_Correct = answer_Correct;
        this.status = status;
    }
    
    
    

    public QuestionDTO(String id_Question, String id_Subject, String question_Content, String answer_Content_1, String answer_Content_2, String answer_Content_3, String answer_Content_4, String answer_Correct, String status, Date createDate) {
        this.id_Question = id_Question;
        this.id_Subject = id_Subject;
        this.question_Content = question_Content;
        this.answer_Content_1 = answer_Content_1;
        this.answer_Content_2 = answer_Content_2;
        this.answer_Content_3 = answer_Content_3;
        this.answer_Content_4 = answer_Content_4;
        this.answer_Correct = answer_Correct;
        this.status = status;
        this.createDate = createDate;
    }

    

    public String getId_Question() {
        return id_Question;
    }

    public void setId_Question(String id_Question) {
        this.id_Question = id_Question;
    }

    public String getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(String id_Subject) {
        this.id_Subject = id_Subject;
    }

    public String getQuestion_Content() {
        return question_Content;
    }

    public void setQuestion_Content(String question_Content) {
        this.question_Content = question_Content;
    }

    public String getAnswer_Content_1() {
        return answer_Content_1;
    }

    public void setAnswer_Content_1(String answer_Content_1) {
        this.answer_Content_1 = answer_Content_1;
    }

    public String getAnswer_Content_2() {
        return answer_Content_2;
    }

    public void setAnswer_Content_2(String answer_Content_2) {
        this.answer_Content_2 = answer_Content_2;
    }

    public String getAnswer_Content_3() {
        return answer_Content_3;
    }

    public void setAnswer_Content_3(String answer_Content_3) {
        this.answer_Content_3 = answer_Content_3;
    }

    public String getAnswer_Content_4() {
        return answer_Content_4;
    }

    public void setAnswer_Content_4(String answer_Content_4) {
        this.answer_Content_4 = answer_Content_4;
    }

    public String getAnswer_Correct() {
        return answer_Correct;
    }

    public void setAnswer_Correct(String answer_Correct) {
        this.answer_Correct = answer_Correct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
    
}
