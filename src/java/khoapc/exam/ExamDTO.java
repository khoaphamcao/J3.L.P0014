/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.exam;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Khoa Pham
 */
public class ExamDTO implements Serializable{
    private String examID;
    private String email;
    private Date date;
    private String subjectID;
    private int numberCorrect;
    private float point;

    public ExamDTO() {
    }

    public ExamDTO(String examID, String email, Date date, String subjectID) {
        this.examID = examID;
        this.email = email;
        this.date = date;
        this.subjectID = subjectID;
    }

    public ExamDTO(String examID, String email, Date date, String subjectID, int numberCorrect, float point) {
        this.examID = examID;
        this.email = email;
        this.date = date;
        this.subjectID = subjectID;
        this.numberCorrect = numberCorrect;
        this.point = point;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
}
