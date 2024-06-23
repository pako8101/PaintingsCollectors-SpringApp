package com.paintingscollectors.config;

import com.paintingscollectors.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoggedUser {

    private String username;

    private long id;

    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
    public boolean isLoggedIn(){
        return  id != 0;
    }

    public LoggedUser() {
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getId() {
        return id;
    }

    public LoggedUser setId(long id) {
        this.id = id;
        return this;
    }

    public void logout() {
        this.username= null;
        this.id = 0;
    }
}
