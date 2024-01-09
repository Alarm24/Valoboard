package character;

import base.Agent;
import util.Coordinate;
import java.util.ArrayList;

public class Brimstone extends Controller {
    public static String dIconPath = "images/d-brim.png";
    public static String aIconPath = "images/a-brim.png";

    public Brimstone(int row, int col, boolean isAttacker){
        super(row, col, isAttacker, dIconPath, aIconPath);
    }
    public void skillBrimstone() {
        System.out.println("Sky Smoke");
        ArrayList<Agent> effectedAgents = getEffectAgent(getCurrentLocation(), 5);
        for (Agent agent : effectedAgents) {
            agent.setSmokeState(true);
        }
    }
    public void ultimateskill(Coordinate destination){
        System.out.println("Orbital Strike");
        ArrayList<Agent> effectedAgents = getEffectAgent(destination, 5);
        for (Agent agent : effectedAgents) {
            agent.setHp(agent.getHp() - 5);
        }
        this.setUltimateAvailable(false);
    }

    @Override
    public String toString() {
        return "Brimstone";
    }

}
