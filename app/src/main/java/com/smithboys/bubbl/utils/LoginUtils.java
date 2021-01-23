package com.smithboys.bubbl.utils;

import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalUsers;
import com.smithboys.bubbl.models.User;

public class LoginUtils {
    public static boolean validateLogin(String email, String password) {
        User user = GlobalUsers.queryByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                CurrentUser.currentUser = user;
                return true;
            }
        }
        return false;
    }
}
