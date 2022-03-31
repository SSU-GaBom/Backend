package com.example.bom.gabom.etc;

import com.example.bom.gabom.model.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Component
public class SessionCookie {

    public static final String SESSION_COOKIE_NAME = "mySessionId";

    public void createSession(User user, HttpSession session, String sessionId){
        session.setAttribute(sessionId, user);
    }
    public void expireSession(User user, HttpSession session, String sessionId){
        session.removeAttribute(sessionId);
    }
    public void createCookie(HttpServletResponse httpServletResponse, String sessionId){
        httpServletResponse.addCookie(new Cookie(SESSION_COOKIE_NAME, sessionId));
    }
    public Cookie findCookie(HttpServletRequest httpServletRequest, String cookieName){
        if(httpServletRequest.getCookies() == null){
            return null;
        }
        return Arrays.stream(httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
