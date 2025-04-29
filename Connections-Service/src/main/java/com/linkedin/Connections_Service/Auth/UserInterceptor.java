package com.linkedin.Connections_Service.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
It will intercept all the incoming request and that requests has a header. So, we can ge the header
from the request and that header contains the User ID.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    // Extracting the User Id from the header and set that user id to contextHolder
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userID = request.getHeader("X-User-Id");
        if(userID != null){
            UserContextHolder.setCurrentUserId(Long.valueOf(userID));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // Clearing the context holder
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.clearCurrentUser();
    }
}
