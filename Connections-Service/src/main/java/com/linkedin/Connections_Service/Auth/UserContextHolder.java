package com.linkedin.Connections_Service.Auth;

// This is the custom context holder.
public class UserContextHolder {


    /*
    Thread Local -> It hold the information about the user until the thread is available.
    It doesn't share the information between the threads.
     */

    public static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    static void setCurrentUserId(Long userId) {
        currentUserId.set(userId);
    }

    public static Long getCurrentUserId() {
        return currentUserId.get();
    }

    static void clearCurrentUser() {
        currentUserId.remove();
    }

}
