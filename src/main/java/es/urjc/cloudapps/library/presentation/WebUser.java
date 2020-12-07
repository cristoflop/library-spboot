package es.urjc.cloudapps.library.presentation;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class WebUser {

    private boolean anonymous;
    private String userName;

    public WebUser() {
        this.anonymous = true;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.anonymous = false;
        this.userName = userName;
    }

}
