package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Supermen", 150);
        BossEnemy boss = new BossEnemy("Batman", 150, 45);

        AttackAction basic = new BasicAttack("Slash", 20);
        AttackAction enhanced = new CriticalFocusDecorator(
                                    new PoisonCoatingDecorator(
                                        new FireRuneDecorator(basic)
                                    )
                                );
        AttackAction fireCrit = new CriticalFocusDecorator(
                                    new FireRuneDecorator(basic)
                                );
        AttackAction poisonOnly = new PoisonCoatingDecorator(basic);

        System.out.println("--- Decorator Preview ---\n");

        printActionInfo("Base action", basic);
        printActionInfo("Enhanced action (Fire + Poison + Critical)", enhanced);
        printActionInfo("Fire + Critical", fireCrit);
        printActionInfo("Poison only", poisonOnly);

        System.out.println("\n--- Facade Preview ---\n");
        DungeonFacade facade = new DungeonFacade();
        AdventureResult result = facade.runAdventure(hero, boss, enhanced);

        System.out.println("=== Dungeon Run Result ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("\n--- Battle Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }
        System.out.println("\n=== Demo Complete ===");
    }
    private static void printActionInfo(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name: " + action.getActionName());
        System.out.println("  Damage: " + action.getDamage());
        System.out.println("  Effects: " + action.getEffectSummary());
        System.out.println();
    }
}