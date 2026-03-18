package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }
@Override
public String getActionName() {
    return super.getActionName() + " [Critical]";
}

@Override
public int getDamage() {
    return (int)(super.getDamage() * 2.5);
}

@Override
public String getEffectSummary() {
    return super.getEffectSummary() + ", critical strike (2.5x dmg)";
}
}
