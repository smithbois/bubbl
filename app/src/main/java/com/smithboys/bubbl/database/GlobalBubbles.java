package com.smithboys.bubbl.database;

import com.smithboys.bubbl.models.Bubble;

import java.util.HashMap;
import java.util.Map;

public class GlobalBubbles {
    // replace with calls to server database in production

    private static Map<Integer, Bubble> globalBubbles = new HashMap<>(); // All bubbles in database, organized as (id, bubble)
    private static Integer bubbleCount; // Number of bubbles in database

    // Dummy data
    public static void initializeBubbles() {
        bubbleCount = 0;
        globalBubbles.put(0, new Bubble("Georgia Tech", 0));
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
}