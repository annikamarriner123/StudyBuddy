/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;

/**
 *
 * @author willi
 */    

@Entity
@Table(name = "Tutor_Table")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorId;
    private String email;
    private Collection<String> subjects;
    private String username;
    private String password;
    private String avalability;
   // TODO: private something profile picture   
    
    //Default constructor
    public Tutor(){}
    
    public Tutor(Integer tutorId, String email, Collection<String> subjects, String username, String password, String avalability){
        this.tutorId = tutorId;
        this.email = email;
        this.subjects = subjects;
        this.username = username;
        this.password = password;
        this.avalability = avalability;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<String> subjects) {
        this.subjects = subjects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvalability() {
        return avalability;
    }

    public void setAvalability(String avalability) {
        this.avalability = avalability;
    }

    @Override
    public String toString() {
        return "Tutor{" + "tutorId=" + tutorId + ", email=" + email + ", subjects=" + subjects + ", username=" + username + ", password=" + password + ", avalability=" + avalability + '}';
    }
    
    
    
}


