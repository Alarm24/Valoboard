package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.Coordinate;

public class BoardPane extends Pane {
    private static BoardPane INSTANCE = null;
    public static final int height = 29; // Set the height to 8
    public static final int width = 29; // Set the width to 8
    public static final int GRID_SIZE = 25;
    public static final int ICON_SIZE = GRID_SIZE - 2;
    public static final String defaultGridStyle = "-fx-border-color: black; -fx-border-width: 1px";
    public static final String defaultAvailableMoveGuideStyle = "-fx-border-color: green; -fx-border-width: 4px";
    public static final String defaultAvailableShootStyle = "-fx-border-color: grey; -fx-border-width: 1px";

    public static final String defaultAvailableHealStyle = "-fx-border-color: yellow; -fx-border-width: 1px";


    private final Label[][] grids;

    private BoardPane() {
        grids = new Label[height][width];
        for (int i = 0; i < height; i++) { // Corrected loop limits
            for (int j = 0; j < width; j++) { // Corrected loop limits
                addGridToThePane(i, j);
            }
        }
    }

    private void setGridColor(Label newLabel, int height, int width) {
        if((height==0 || width==0 || height==28 || width==28)){
            newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        //red
        else if(height==1){
            if(width>=0 && width <=5){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=6 && width<=18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>7 && width<=16){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==2){
            if(width>=0 && width <=5){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=6 && width<=19){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>7 && width<=16){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==3){
            if(width>=0 && width <=5){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=6 && width<=20){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>7 && width<=16){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==4){
            if(width>=4 && width<=23){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>7 && width<=16){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==5){
            if(width>=3 && width<=25){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>7 && width<=16){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        //Not special
        else if(height==6){
            if(width>=7 && width<=10){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==7){
            if(width>=6 && width<=15){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=18 && width<=19){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==8){
            if(width>=13 && width<=15){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=19 && width<=22){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==9){
            if(width>=21 && width<=24){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==10){
            if(width>=1 && width<=2){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=21 && width<=24){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==11){
            if(width>=1 && width<=2){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=7 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        //Yellow
        else if(height==12){
            if(width>=1 && width<=2){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=7 && width<=12){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=22 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==13){
            if(width>=7 && width<=13){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=22 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==14){
            if(width>=9 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=13){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=21){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==22){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=23 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==15){
            if(width>=8 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==20){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=21 && width<=22){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=23 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        //Yellow with Green
        else if(height==16){
            if(width>=3 && width<=5){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=6 && width<=7){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=8 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=13){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>=14 && width<=17){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else if(width==18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==20){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=21 && width<=22){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=23 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==17){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=5){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=6 && width<=7){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=8 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=13){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>=14 && width<=17){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else if(width==18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==18){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=4){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=5 && width<=7){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=8 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>=14 && width<=17){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else if(width==18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=26){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==19){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=4){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=5 && width<=7){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=8 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>=14 && width<=17){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else if(width==18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=26){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width==27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==20){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }
//            else if(width>=14 && width<=17){
//                newLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//            }
            else if(width==18){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=24){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=25&&width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        //Yellow
        else if(height==21){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=15){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=20 && width<=24){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=25&&width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==22){
            if(width==1){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=2 && width<=11){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(13,78,84), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=12 && width<=15){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=22&&width<=27){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        //Free
        else if(height==23){
            if(width>=1 && width<=3){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=7 && width<=15){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==24){
            if(width>=1 && width<=3){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }else if(width>=11 && width<=13){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==25){
            if(width>=18 && width<=19){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==26){
            if(width>=18 && width<=19){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        else if(height==27){
            if(width>=18 && width<=19){
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(50,205,222), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else{
                newLabel.setBackground(new Background(new BackgroundFill(Color.rgb(12,41,59), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }
    private void setChessboardGrid(Label newLabel, int height, int width) {
        newLabel.setLayoutX(((width) * GRID_SIZE));
        newLabel.setLayoutY(((height) * GRID_SIZE));
        newLabel.setPrefHeight(GRID_SIZE);
        newLabel.setPrefWidth(GRID_SIZE);
        grids[height][width] = newLabel;
    }

    private void addGridToThePane(int height, int width) {
        Label newLabel = new Label();
        newLabel.setAlignment(Pos.CENTER);
        newLabel.setStyle(defaultGridStyle);

        setGridColor(newLabel, height, width);
        setChessboardGrid(newLabel, height, width);
        this.getChildren().add(newLabel);
    }

    public Label getOneCell(int row, int col) {
        return grids[row][col];
    }

    public Label getOneCell(Coordinate coord) {
        return getOneCell(coord.getRow(), coord.getCol());
    }


    public static BoardPane getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BoardPane();
        }
        return INSTANCE;
    }
}
