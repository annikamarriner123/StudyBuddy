/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.*;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "User_Table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String surname;
    private String username;
    private String password;
    private String role; // possible values, USER, ADMIN or USER,ADMIN for both
    private String email;
    private String major;

    @ManyToMany
    @JoinTable(
            name = "chatroom_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chatroom_id")
    )
    private Set<ChatRoom> chatRooms = new HashSet<>();

    
    private Collection<String> interests;

    private String papers;
    
    private List<String> userPapers = new ArrayList<>();

    //Default Consturctor
    public User() {
    }

    public User(Integer userId, String firstName, String surname, String username, String password, String email, String papers, String major, Collection<String> interests, Set<ChatRoom> chatRooms) {
        this.userId = userId;
        this.firstName = firstName;
        this.chatRooms = chatRooms;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;

        this.major = major;
//        this.favourites = favourites;
        this.interests = interests;

    }

    public List<String> getUserPapers() {
        return userPapers;
    }

    public void setUserPapers(List<String> userPapers) {
        this.userPapers = userPapers;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

//    public Collection<T> getFavourites() {
//        return favourites;
//    }
//
//    public void setFavourites(Collection<T> favourites) {
//        this.favourites = favourites;
//    }
    public Collection<String> getInterests() {
        return interests;
    }

    public void setInterests(Collection<String> interests) {
        this.interests = interests;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstName=" + firstName + ", surname=" + surname + ", username=" + username + ", password=" + password + ", email=" + email + ", major=" + major + ", joinedChatRooms=" + chatRooms + ", interests=" + interests + ", papers=" + papers + '}';
    }

}
