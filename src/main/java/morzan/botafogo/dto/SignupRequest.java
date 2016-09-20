/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.dto;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class SignupRequest {
    
    private String name;
    private String nameUser;
    private String passw;
    private List<String> likes;
    private List<String> dislikes;

    public SignupRequest() {
    }

    public SignupRequest(String name, String nameUser, String passw, List<String> likes, List<String> dislikes) {
        this.name = name;
        this.nameUser = nameUser;
        this.passw = passw;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }
    
    
    
}
