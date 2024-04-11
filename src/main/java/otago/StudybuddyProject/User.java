/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudybuddyProject;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.*;
/**
 *
 * @author willi
 */
@Entity
@Table
public class User<T> {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    private int userId;
    private String username;
    private String password;
    private String email;
    private String[] papers;
    private String major;
    private Collection<T> favourites;
    //TODO: private "something" profilePicture
    private String[] intrests;
    
    //Default Consturctor
    public User(){}

    public User(int userId, String username, String password, String email, String[] papers, String major, Collection<T> favourites, String[] intrests) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.papers = papers;
        this.major = major;
        this.favourites = favourites;
        this.intrests = intrests;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPapers() {
        return papers;
    }

    public void setPapers(String[] papers) {
        this.papers = papers;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Collection<T> getFavourites() {
        return favourites;
    }

    public void setFavourites(Collection<T> favourites) {
        this.favourites = favourites;
    }

    public String[] getIntrests() {
        return intrests;
    }

    public void setIntrests(String[] intrests) {
        this.intrests = intrests;
    }
    
    
    
    
}
