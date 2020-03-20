package com.plf.learn.thread.interceptor;

import com.plf.learn.thread.entity.User;

/**
 * @author Panlf
 * @date 2020/3/20
 */
public class UserContextHolder {
    private static final ThreadLocal<User> USER_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 填入User
     * @param user
     */
    public static void setUser(User user){
        USER_CONTEXT_HOLDER.set(user);
    }

    /**
     * 擦除User
     */
    public static void remove(){
        USER_CONTEXT_HOLDER.remove();
    }

    /**
     * 获取User
     * @return
     */
    public static User getUser(){
        return USER_CONTEXT_HOLDER.get();
    }
}
