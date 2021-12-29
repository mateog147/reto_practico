package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Player;
//Este es el controlador de la vista del Login
public class loginController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button playBtn;

    @FXML
    //Este metodo controla la validacion de que se ingrese un nombre de usuario, desplega una nueva partida y cierra el formulario de login.
    void play(MouseEvent event) {
        String userName = nameTxt.getText();
        if(userName.isEmpty() || userName == null ){
            errorLabel.setText("INGRESA UN NOMBRE DE USUARIO");
        }else{
            Player player = new Player(userName);
            try {
                player.play();
                Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
                thisStage.close();
            } catch (Exception e) {
                errorLabel.setText("Ha ocurrido un error");
                System.out.println(e);
            }
        }
    }
}
