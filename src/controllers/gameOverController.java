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

//Este es el metodo controlador de la Vista de Game Over
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
    //Este metodo controla cuando el usuario quiere jugar nuevamente 
    void playAgain(MouseEvent event) throws Exception{
            //Abro la interfaz para ingresar usuario
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/loginView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene =new Scene(root);
            stage.setTitle("PREGUNTAS Y RESPUESTAS");
            stage.setScene(scene);
            stage.show();
            //Cierro este formulario
            Stage thisStage = (Stage) this.score1.getScene().getWindow();
            thisStage.close();
            
    }

    @FXML
    //Este metodo carga en pantalla los 5 mejores resultados, usando el metodo estatico del objeto Conector
    void initialize(){
        try {
            ArrayList<Player> top =Conector.top(); //Traemos los 5 mejores 
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