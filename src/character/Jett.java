package character;

import base.AgentManager;
import util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Jett extends Duelist {
    public static String dIconPath = "images/d-jett.png";
    public static String aIconPath = "images/a-jett.png";


    public Jett(int row, int col, boolean isAttacker){
        super(row, col, isAttacker, dIconPath, aIconPath);
    }
    @Override
    public List<Coordinate> getAvailableNextMovePosition() {
        AgentManager agentManager = AgentManager.getInstance();
        List<Coordinate> availableMove = new ArrayList<>();
        if(isUsingSkill()){
            Coordinate center = getCurrentLocation().getTopRight().getTopRight();
            for (int i=0; i < 5; i++) {
                for (int j=0; j < 5; j++) {
                        if (center.isCoordinateExist(center.getRow() + i, center.getCol() - j)){
                            if(agentManager.haveAgentCheck(center.getRow() + i,center.getCol() - j)){
                                availableMove.add(new Coordinate(center.getRow() + i, center.getCol() - j));
                            }
                        }
                }
            }
        }else{
            return super.getAvailableNextMovePosition();
        }
        return availableMove;
    }

    public void ultimateSkill() {
        if(getUltimateAvailable()){
            System.out.println("Blade Storm");
            this.setShootingDistance(getShootingDistance() + 1);
            this.setShootingDamage(7);
            this.setUltimateAvailable(false);
        }
    }


    @Override
    public String toString() {
        return "Jett";
    }

}
