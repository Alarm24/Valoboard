package character;

import base.Agent;

public abstract class Controller extends Agent {

    public final int DEFAULT_HP = 7;
    public final int DEFAULT_SHOOTING_DAMAGE = 3;
    public final int DEFAULT_SHOOTING_DISTANCE = 3;

    public Controller(int row, int col, boolean isAttacker, String dIconPath, String aIconPath) {
        super(row, col, isAttacker, dIconPath, aIconPath);
        this.setHp(DEFAULT_HP);
    }

    @Override
    public void setNewRound() {
        this.setSkillAvailable(true);
        this.setShootingDamage(DEFAULT_SHOOTING_DAMAGE);
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