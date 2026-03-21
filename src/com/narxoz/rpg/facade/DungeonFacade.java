package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {

    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }
    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        String prepSummary = preparationService.prepare(hero, boss, action);
        result.addLine(prepSummary);
        AdventureResult battleResult = battleService.battle(hero, boss, action);
        result.getLog().addAll(battleResult.getLog());
        result.setWinner(battleResult.getWinner());
        result.setRounds(battleResult.getRounds());
        String rewardStr = rewardService.determineReward(result);
        result.setReward(rewardStr);
        result.addLine("Reward: " + rewardStr);
        return result;
    }
}