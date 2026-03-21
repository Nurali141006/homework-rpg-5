package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {

    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: invalid inputs";
        }

        return "Preparation complete:\n" +
                "Hero: " + hero.getName() + " (HP: " + hero.getHealth() + ")\n" +
                "Boss: " + boss.getName() + " (HP: " + boss.getHealth() + ")\n" +
                "Attack: " + action.getActionName() + "\n" +
                "Effects: " + action.getEffectSummary();
    }
}