/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoapc.subject;

import java.io.Serializable;

/**
 *
 * @author Khoa Pham
 */
public class SubjectDTO implements Serializable{
    private String idSubject;
    private String subjectName;

    public SubjectDTO(String idSubject, String subjectName) {
        this.idSubject = idSubject;
        this.subjectName = subjectName;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
