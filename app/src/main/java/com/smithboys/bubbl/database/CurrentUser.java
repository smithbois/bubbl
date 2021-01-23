package com.smithboys.bubbl.database;

import com.smithboys.bubbl.models.User;

public class CurrentUser {
    public static User currentUser = null;

    // methods to store this so user does not have to log in again after app is closed
}