package character;

import base.Agent;

public abstract class Sentinel extends Agent {

    public final int DEFAULT_HP = 7;
    public final int DEFAULT_SHOOTING_DAMAGE = 3;
    public final int DEFAULT_SHOOTING_DISTANCE = 2;

    public final int HEAL=5;

    public Sentinel(int row, int col, boolean isAttacker, String dIconPath, String aIconPath) {
        super(row, col, isAttacker, dIconPath, aIconPath);
        this.setHp(DEFAULT_HP);
    }

    @Override
    public void setNewRound() {
        this.setSkillAvailable(true);
        this.setShootingDamage(DEFAULT_SHOOTING_DAMAGE);
        this.setHealDamage(HEAL);
        this.setShootingDistance(DEFAULT_SHOOTING_DISTANCE);
        this.setSlowState(false);
        this.setSmokeState(false);
    }

    @Override
    public void setHp(int hp) {
        if (hp <= 0) {
            super.setHp(0);
            this.setDead(true);
            onDeath();
        } else {
            super.setHp(Math.min(hp, DEFAULT_HP));
        }
    }
    private void onDeath() {
        removeAgentFromBoard();
    }
}
