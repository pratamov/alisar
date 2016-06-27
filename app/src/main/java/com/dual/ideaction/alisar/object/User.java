package com.dual.ideaction.alisar.object;

import java.util.HashMap;

public class User {

    private static User instance;
    private String userId;
    private HashMap<String, Terminal> terminals;

    public static User getInstance(){
        if (instance == null)
            instance = new User();
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public HashMap<String, Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(HashMap<String, Terminal> terminals) {
        this.terminals = terminals;
    }
}
