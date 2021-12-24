package controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Conector;
import models.Player;

public class gameOverController {

    @FXML
    private Button jugarBtn;
    @FXML
    private Label name1Lbl;
    @FXML
    private Label name2Lbl;
    @FXML
    private Label name3Lbl;
    @FXML
    private Label name4Lbl;
    @FXML
    private Label name5Lbl;
    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label score3;
    @FXML
    private Label score4;
    @FXML
    private Label score5;

    @FXML
    void playAgain(MouseEvent event) throws Exception{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/loginView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene =new Scene(root);
            stage.setTitle("PREGUNTAS Y RESPUESTAS");
            stage.setScene(scene);
            stage.show();
            Stage thisStage = (Stage) this.score1.getScene().getWindow();
            thisStage.close();
            
    }

    @FXML
    void initialize(){
        try {
            ArrayList<Player> top =Conector.top();
            if(top.get(0) != null){
                name1Lbl.setText(top.get(0).getUserName());
                score1.setText(Integer.toString(top.get(0).getScore()));
            }
            if(top.get(1) != null){
                name2Lbl.setText(top.get(1).getUserName());
                score2.setText(Integer.toString(top.get(1).getScore()));
            }
            if(top.get(2) != null){
                name3Lbl.setText(top.get(2).getUserName());
                score3.setText(Integer.toString(top.get(2).getScore()));
            }
            if(top.get(3) != null){
                name4Lbl.setText(top.get(3).getUserName());
                score4.setText(Integer.toString(top.get(3).getScore()));
            }
            if(top.get(4) != null){
                name5Lbl.setText(top.get(4).getUserName());
                score5.setText(Integer.toString(top.get(4).getScore()));
            }
            }
        catch (Exception e) {
            //TODO: handle exception
        }
    }
}