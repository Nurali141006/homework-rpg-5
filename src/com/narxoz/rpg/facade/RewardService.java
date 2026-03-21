package com.narxoz.rpg.facade;

public class RewardService {

    public String determineReward(AdventureResult battleResult) {

        if (battleResult == null) {
            return "No reward";
        }

        String winner = battleResult.getWinner();

        if ("Draw".equals(winner)) {
            return "Reward: 20 gold (draw)";
        }
        if (!winner.toLowerCase().contains("boss")) {
            return "Reward: 100 gold + rare item";
        }
        return "No reward (defeat)";
    }
}