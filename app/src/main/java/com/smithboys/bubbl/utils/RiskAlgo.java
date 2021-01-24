package com.smithboys.bubbl.utils;

import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.database.GlobalUsers;
import com.smithboys.bubbl.models.Bubble;
import com.smithboys.bubbl.models.User;

public class RiskAlgo {

    public static void updateRisk() {

        double risk = (CurrentUser.currentUser.getRiskLevel() - 1) / 4.0;
        System.out.println("risk: " + risk);

        for(int id1 : CurrentUser.currentUser.getBubbles()) {
            Bubble b1 = GlobalBubbles.queryByID(id1);
            double freqModifier1 = CurrentUser.currentUser.getBubbleFrequency(id1) / 3.0;
            System.out.println("freq: " + freqModifier1);
            for(int uid1 : b1.getUsers()) {
                if (uid1 != CurrentUser.currentUser.getId()) {
                    User u1 = GlobalUsers.queryByID(uid1);
                    u1.setRiskLevel((int) Math.round(risk * freqModifier1 * 4) + 1);
                    System.out.println("Set " + u1.getFirstName() + " to " + u1.getRiskLevel());
                    double risk1 = (u1.getRiskLevel() - 1) / 4.0;
                    for(int id2 : u1.getBubbles()) {
                        if(id2 != id1) {
                            Bubble b2 = GlobalBubbles.queryByID(id2);
                            double freqModifier2 = u1.getBubbleFrequency(id1) / 3.0;
                            for(int uid2 : b2.getUsers()) {
                                User u2 = GlobalUsers.queryByID(uid2);
                                u2.setRiskLevel((int) Math.round(risk1 * freqModifier2 * 4) + 1);
                            }
                        }
                    }
                }
            }
        }


        updateBubbleRisks();


    }

    public static void updateBubbleRisks() {
        for(int i = 0; i < GlobalBubbles.getBubbleCount(); i++) {
            Bubble bubble = GlobalBubbles.queryByID(i);
            int userCount = bubble.getUsers().size();
            int totalRisk = 0;
            for (int id : bubble.getUsers()) {
                User user = GlobalUsers.queryByID(id);
                totalRisk = totalRisk + user.getRiskLevel() - 1;
            }
            int averageRisk = (int) Math.round((double) totalRisk / userCount);
            bubble.setRiskLevel(averageRisk + 1);
        }
    }

}
