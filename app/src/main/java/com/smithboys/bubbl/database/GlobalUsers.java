package com.smithboys.bubbl.database;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.models.User;

import java.util.HashMap;
import java.util.Map;

public class GlobalUsers {
    // replace with calls to server database in production

    private static Map<Integer, User> globalUsers = new HashMap<>(); // All users in database, organized as (id, user)
    private static Integer userCount; // Number of users in database

    // Dummy data
    public static void initializeUsers() {
        userCount = 0;
        globalUsers.put(0, new User("georgepburdell@gatech.edu", "George", "Burdell", "testing"));
        globalUsers.put(1, new User("jvitko3@gatech.edu", "Joseph", "Vitko", "testing"));
        globalUsers.put(2, new User("gpeng8@gatech.edu", "Gary", "Peng", "testing"));
        globalUsers.put(3, new User("jgann8@gatech.edu", "Jeremy", "Gann", "testing"));
        globalUsers.put(4, new User("nhulston@gatech.edu", "Nick", "Hulston", "testing"));
        globalUsers.put(5, new User("daniel.lam@gatech.edu", "Dan", "Lam", "testing"));
        globalUsers.put(6, new User("", "George", "Burdell", ""));
        globalUsers.get(0).setProfilePic(R.drawable.george);
        globalUsers.get(1).setProfilePic(R.drawable.joe);
        globalUsers.get(2).setProfilePic(R.drawable.gary);
        globalUsers.get(3).setProfilePic(R.drawable.jeremy);
        globalUsers.get(4).setProfilePic(R.drawable.nick);
        globalUsers.get(5).setProfilePic(R.drawable.dan);
        globalUsers.get(6).setProfilePic(R.drawable.george);


    }

    public static User queryByID(Integer id) {
        return globalUsers.get(id);
    }

    public static User queryByEmail(String email) {
        for (User u : globalUsers.values()) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        globalUsers.put(user.getId(), user);
        userCount++;
    }

    public static Integer getUserCount() {
        return userCount;
    }
}
