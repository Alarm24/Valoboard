package base;

import constant.GameMap;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pane.BoardPane;
import util.Coordinate;
import util.Resources;
import java.util.ArrayList;
import java.util.List;

public abstract class Agent{

    private int shootingDamage;
    private int healDamage;
    private int hp;
    private int shootingDistance;
    private boolean skillAvailable;
    private boolean ultimateAvailable=true;
    private Coordinate currentLocation;
    private final ImageView agentIcon;
    public String agentIconPath;
    private final GameMap gameMap;
    boolean isAttacker;
    private boolean isSlowState;
    private boolean isSmokeState;
    private boolean isDead;
    private boolean usingSkill = false;

    public Agent(int row, int col, boolean isAttacker, String dIconPath, String aIconPath){
        currentLocation = new Coordinate(row, col);
        this.isAttacker = isAttacker;
        if (this.isAttacker) {
            agentIconPath = aIconPath;
            agentIcon = Resources.loadAgentIcon(aIconPath);
        } else {
            agentIconPath = dIconPath;
            agentIcon = Resources.loadAgentIcon(dIconPath);
        }
        agentIcon.setPreserveRatio(true);
        agentIcon.setFitHeight(BoardPane.ICON_SIZE);

        this.gameMap = new GameMap();
    }

    public abstract void setNewRound();
    public boolean isAttacker() {
    return isAttacker;
}

    public List<Coordinate> getAvailableNextMovePosition() {
        AgentManager agentManager = AgentManager.getInstance();
        List<Coordinate> availableMove = new ArrayList<>();
        Coordinate center = getCurrentLocation().getTopRight();
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (center.isCoordinateExist(center.getRow() + i, center.getCol() - j)){
                    if(agentManager.haveAgentCheck(center.getRow() + i,center.getCol() - j)){
                        availableMove.add(new Coordinate(center.getRow() + i, center.getCol() - j));
                    }
                }
            }
        }
        return availableMove;
    }

    public List<Coordinate> getAvailableSkillRange(int distance) {
        AgentManager agentManager = AgentManager.getInstance();
        ArrayList<Coordinate> result = new ArrayList<>();
        Coordinate center = getCurrentLocation();
        for (int i=1; i <= distance; i++) {
//          Straight
            if (center.isCoordinateExist(center.getRow(), center.getCol() + i)) {
                result.add(new Coordinate(center.getRow(), center.getCol() + i));
            }

            if (center.isCoordinateExist(center.getRow(), center.getCol() - i)) {
                result.add(new Coordinate(center.getRow(), center.getCol() - i));
            }
            if (center.isCoordinateExist(center.getRow()+i, center.getCol())) {
                result.add(new Coordinate(center.getRow()+i, center.getCol()));
            }
            if (center.isCoordinateExist(center.getRow()-i, center.getCol())) {
                result.add(new Coordinate(center.getRow()-i, center.getCol()));
            }
//          Cross
            if (center.isCoordinateExist(center.getRow()+i, center.getCol()+i)) {
                result.add(new Coordinate(center.getRow()+i, center.getCol()+i));
            }

            if (center.isCoordinateExist(center.getRow()+i, center.getCol()-i)) {
                result.add(new Coordinate(center.getRow()+i, center.getCol()-i));
            }

            if (center.isCoordinateExist(center.getRow()-i, center.getCol()+i)) {
                result.add(new Coordinate(center.getRow()-i, center.getCol()+i));
            }

            if (center.isCoordinateExist(center.getRow()-i, center.getCol()-i)) {
                result.add(new Coordinate(center.getRow()-i, center.getCol()-i));
            }
        }
        return result;
    }

    public static ArrayList<Coordinate> getSkillEffectRange(Coordinate coordinate, int distance) {
        ArrayList<Coordinate> effectRange = new ArrayList<>();

        Coordinate topLeft = new Coordinate(coordinate.getRow() - distance / 2, coordinate.getCol() + distance / 2);
        for (int i=0; i < distance; i++) {
            for (int j=0; j < distance; j++) {
                if (topLeft.isCoordinateExist(topLeft.getRow() + i, topLeft.getCol() - j)) {
                    effectRange.add(new Coordinate(topLeft.getRow() + i, topLeft.getCol() - j));
                }
            }
        }
        return effectRange;
    }

    public static ArrayList<Agent> getEffectAgent(Coordinate coordinate, int distance) {
        ArrayList<Agent> effectAgent = new ArrayList<>();
        ArrayList<Coordinate> effectRange = getSkillEffectRange(coordinate, distance);

        AgentManager agentManager = AgentManager.getInstance();
        for (Agent defender : agentManager.getDefenderAgents()) {
                if (effectRange.contains(defender.getCurrentLocation())) {
                    effectAgent.add(defender);
                }
            }
            for (Agent attacker : agentManager.getAttackerAgents()) {
                if (effectRange.contains(attacker.getCurrentLocation())) {
                    effectAgent.add(attacker);
                }
            }
        return effectAgent;
    }

    public List<Coordinate> getAllAllyLocations() {
        List<Coordinate> allyLocations = new ArrayList<>();
        List<Agent> allyAgents;

        AgentManager agentManager = AgentManager.getInstance();
        if (this.isAttacker) {
            allyAgents = agentManager.getAttackerAgents();
        } else {
            allyAgents = agentManager.getDefenderAgents();
        }

        for (Agent ally : allyAgents) {
            if (!ally.equals(this)) {
                allyLocations.add(ally.getCurrentLocation());
            }
        }

        return allyLocations;
    }


    public void setIsMoved() {
        visualizeAgent();
    }

    public void visualizeAgent() {
        Label target = BoardPane.getInstance().getOneCell(currentLocation);
        target.setGraphic(agentIcon);
    }
    public void removeAgentFromBoard() {
        if (isDead) {
            clearAgentIcon();
        }
    }
    public void clearAgentIcon() {
        BoardPane.getInstance().getOneCell(currentLocation).setGraphic(null);
    }
    public boolean getSkillAvailable() {
        return skillAvailable;
    }

    public void setSkillAvailable(boolean skillAvailable) {
        this.skillAvailable = skillAvailable;
    }
    public boolean getUltimateAvailable() {
        return ultimateAvailable;
    }
    public void setUltimateAvailable(boolean ultimateAvailable) {
        this.ultimateAvailable = ultimateAvailable;
    }
    public int getShootingDamage() {
        return shootingDamage;
    }
    public void setShootingDamage(int shootingDamage) {
        this.shootingDamage = shootingDamage;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }
    public int getShootingDistance() {
        return shootingDistance;
    }
    public void setShootingDistance(int shootingDistance) {
        this.shootingDistance = shootingDistance;
    }
    public void setCurrentLocation(Coordinate currentLocation) {
        this.currentLocation = currentLocation;
    }
    public Coordinate getCurrentLocation() {
        return currentLocation;
    }
    public int getHealDamage(){return healDamage;}
    public void setHealDamage(int healDamage){
        this.healDamage=healDamage;
    }
    public void setCoordinate(Coordinate currentLocation) {
        this.currentLocation = currentLocation;
    }
    public boolean isSlowState() {
        return isSlowState;
    }
    public void setSlowState(boolean slowState) {
        isSlowState = slowState;
    }
    public boolean isSmokeState() {
        return isSmokeState;
    }
    public void setSmokeState(boolean smokeState) {
        isSmokeState = smokeState;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isUsingSkill() {
        return usingSkill;
    }

    public void setUsingSkill(boolean usingSkill) {
        this.usingSkill = usingSkill;
    }
    public abstract String toString();

    public String getAgentIconPath() {
        return agentIconPath;
    }
}
