package character;

import base.Agent;
import constant.GameMap;
import java.util.ArrayList;

public class Sage extends Sentinel {
    public static String dIconPath = "images/d-sage.png";
    public static String aIconPath = "images/a-sage.png";
    private final GameMap gameMap;

    public Sage(int row, int col, boolean isAttacker){
        super(row, col, isAttacker, dIconPath, aIconPath);
        this.gameMap = new GameMap();
    }

    public void skillSage(){
            System.out.println("Slow Orb");
            ArrayList<Agent> effectedAgents = getEffectAgent(getCurrentLocation(), 5);
            for (Agent agent : effectedAgents) {
                agent.setSlowState(true);
            }
    }

    @Override
    public String toString() {
        return "Sage";
    }

}
