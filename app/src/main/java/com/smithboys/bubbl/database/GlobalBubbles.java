package com.smithboys.bubbl.database;

import com.smithboys.bubbl.models.Bubble;
import com.smithboys.bubbl.utils.RiskAlgo;

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
        globalBubbles.put(0, new Bubble("Emory", 0));
        globalBubbles.put(1, new Bubble("Hack COVID", 1));
        globalBubbles.put(2, new Bubble("RoboJackets", 0));
        GlobalUsers.queryByID(0).joinBubble(1);
        GlobalUsers.queryByID(1).joinBubble(1);
        GlobalUsers.queryByID(2).joinBubble(1);
        GlobalUsers.queryByID(3).joinBubble(1);
        GlobalUsers.queryByID(4).joinBubble(1);
        GlobalUsers.queryByID(5).joinBubble(1);

        GlobalUsers.queryByID(0).joinBubble(0);
        GlobalUsers.queryByID(6).joinBubble(0);
        GlobalUsers.queryByID(7).joinBubble(0);

        GlobalUsers.queryByID(0).joinBubble(2);
        GlobalUsers.queryByID(8).joinBubble(2);
        GlobalUsers.queryByID(9).joinBubble(2);
        GlobalUsers.queryByID(10).joinBubble(2);
        //queryByID(0).setRiskLevel(4);
        RiskAlgo.updateBubbleRisks();
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