package models;

import controllers.mainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Player{
    //Atributos
    private String userName;
    private int score;

    //constructores
    public Player(){
    }
    public Player(String name){
        this.userName = name;
        this.score = 0; 
    }
    //setters y getters
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    //metodos de la clase
    public void play() throws Exception{
        System.out.println("voy a abrir");
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/mainView.fxml"));
            System.out.println("antes del los");
            Parent root = fxmlLoader.load();
            System.out.println("voy a mostrar");
            Scene scene =new Scene(root);
            stage.setTitle("PREGUNTAS Y RESPUESTAS");
            stage.setScene(scene);
            //mando los datos de usuario y score
            System.out.println("lo cargue y voya a asignar un usuario");
            mainController controller = (mainController) fxmlLoader.getController();
            controller.setPlayer(this);
            stage.show();
            
    }

    public void gameover(){
        try {
            Conector.guardar(this.userName, this.score);
        } catch (Exception e) {
            System.out.println("Error al guardar");
        }

    }
}
