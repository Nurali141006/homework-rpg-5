package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }
    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {

    AdventureResult result = new AdventureResult();

    int heroHp = hero.getHealth();
    int bossHp = boss.getHealth();

    int rounds = 0;
    int maxRounds = 10;

    result.addLine("Battle started: " + hero.getName() + " vs " + boss.getName());
    result.addLine("Attack used: " + action.getActionName());
    result.addLine("Effects: " + action.getEffectSummary());

    while (heroHp > 0 && bossHp > 0 && rounds < maxRounds) {
        rounds++;

        int heroDamage = action.getDamage();

        if (random.nextInt(100) < 20) {
            heroDamage *= 2;
            result.addLine("Critical hit!");
        }

        bossHp -= heroDamage;
        result.addLine("Round " + rounds + ": Hero deals " + heroDamage + " dmg (Boss HP: " + bossHp + ")");

        if (bossHp <= 0) break;

        int bossDamage = boss.getAttackPower();
        heroHp -= bossDamage;

        result.addLine("Boss deals " + bossDamage + " dmg (Hero HP: " + heroHp + ")");
    }

    String winner;
    if (heroHp > 0 && bossHp <= 0) {
        winner = hero.getName();
    } else if (bossHp > 0 && heroHp <= 0) {
        winner = boss.getName();
    } else {
        winner = "Draw";
    }

    result.setWinner(winner);
    result.setRounds(rounds);

    result.addLine("Battle finished. Winner: " + winner);

    return result;
}
}

