package com.smithboys.bubbl.database;

import com.smithboys.bubbl.models.Bubble;

import java.util.HashMap;
import java.util.Map;

public class GlobalBubbles {
    // replace with calls to server database in production

    private static Map<Integer, Bubble> globalBubbles = new HashMap<>(); // All bubbles in database, organized as (id, bubble)
    private static Integer bubbleCount; // Number of bubbles in database
    private static int lastBubbleClicked; // id of last bubble clicked

    // Dummy data
    public static void initializeBubbles() {
        bubbleCount = 0;
        globalBubbles.put(0, new Bubble("Georgia Tech", 0));
        GlobalUsers.queryByID(0).joinBubble(0);
        GlobalUsers.queryByID(1).joinBubble(0);
        GlobalUsers.queryByID(2).joinBubble(0);
        GlobalUsers.queryByID(3).joinBubble(0);
        GlobalUsers.queryByID(4).joinBubble(0);
        GlobalUsers.queryByID(5).joinBubble(0);
    }

    public static Bubble queryByID(Integer id) {
        return globalBubbles.get(id);
    }

    public static void addBubble(Bubble bubble) {
        globalBubbles.put(bubble.getId(), bubble);
        bubbleCount++;
    }

    public static Integer getBubbleCount() {
        return bubbleCount;
    }

    public static void setLastBubbleClicked(int id) {
        lastBubbleClicked = id;
    }
    public static int getLastBubbleClicked() {
        return lastBubbleClicked;
    }
}