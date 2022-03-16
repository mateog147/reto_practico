package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Player;
import models.Round;
//Este es el controlador de la vista principal donde se controlan las partidas 
public class mainController {
    //Creo un instancia de la clase Player, para esta partida
    private Player player;
    //Creo una variable para manejar el nivel del juego 
    private int level; 
    //Set y get plara el obejto Player de la partida 
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player p) {
        this.player = p;
        if(this.player != null){
            //Dentro del metodo Set llevo a plantalla el nombre de usuario y el puntaje
            userLabel.setText(this.player.getUserName());
            scoreLabel.setText(Integer.toString(this.player.getScore()));
        }
    }
    //Elementos de la vista principal
    @FXML
    private Button aBtn;
    @FXML
    private Button bBtn;
    @FXML
    private Button cBtn;
    @FXML
    private Button correctBtn;
    @FXML
    private Button dBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField optionA;
    @FXML
    private TextField optionB;
    @FXML
    private TextField optionC;
    @FXML
    private TextField optionD;
    @FXML
    private TextArea questionTxt;
    @FXML
    private Button quitBtn;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label pointLabel;
    @FXML
    private Label answer;
    
    //Metodos de cuando se selecciona un boton, cada auno manda al metodo validar la respuesta correspondiente
    @FXML
    void selectA(MouseEvent event) {
        validar(optionA.getText());
    }
    @FXML
    void selectB(MouseEvent event) {
        validar(optionB.getText());
    }
    @FXML
    void selectC(MouseEvent event) {
        validar(optionC.getText());
    }
    @FXML
    void selectD(MouseEvent event) {
        validar(optionD.getText());
    }
    //Metodo para abandonar la partida, llama al metodo game over del objeto player y cierra la vista principal
    @FXML
    void quit(MouseEvent event) {
        player.gameover();
        Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
        thisStage.close();
    }

    //Metodo para generar la proxima ronda
    @FXML
    void nextRound(MouseEvent event) {
        //Se aumenta el nivel y se valida que aun es dentro de las primeras 5 rondas
        this.level+=1;
        if(level>=6){
            //si ya termino las 5 rondas se invoca el metodo gameover del jugador y se cierra esta vista 
            player.gameover(); 
            Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
            thisStage.close();
        }
        else{
            //si aun no termina las 5 rondas entonces lanzo una ronda nueva con el nivel de dificultad en el que va lA apartida
            setScreen(new Round(this.level)); //uso el metodo de setScreen pasando una nueva instancia de Round 
            correctBtn.setVisible(false); 
            aBtn.setDisable(false);
            bBtn.setDisable(false);
            cBtn.setDisable(false);
            dBtn.setDisable(false);
        }

    }

    //Metodo para inicializar la vista principal
    @FXML
    void initialize(){
        this.level= 1; //Inicializo el nivel de la partida en 1
        Round round = new Round(level); // creo una instancia de Round para dar inicio a la partida
        setScreen(round); //uso el metodo setScreen con el objeto Round inicial 
    }

    //Metodo para poner en pantalla los valores de la ronda.  
    void setScreen(Round round){
        
        questionTxt.setText(round.getQuestion());
        optionA.setText(round.getOptionA());
        optionB.setText(round.getOptionB());
        optionC.setText(round.getOptionC());
        optionD.setText(round.getOptionD());
        pointLabel.setText(Integer.toString(round.points));
        answer.setText(round.getCorrect());
        errorLabel.setText("");
    }

    //metodo para validar si la respuesta es correcta, si es incorrecta pone el puntaje en 0 y termina la partida
    void validar(String option){
        if(option.equals(answer.getText())){
            System.out.println("correcto");
            player.setScore(player.getScore()+Integer.parseInt(pointLabel.getText()));
            scoreLabel.setText(Integer.toString(this.player.getScore()));
            correctBtn.setVisible(true);
            if(level>=5){
                correctBtn.setText("GANASTE! \n presiona para continuar");
            }
            aBtn.setDisable(true);
            bBtn.setDisable(true);
            cBtn.setDisable(true);
            dBtn.setDisable(true);
        }else{
            System.out.println("incorrecto");
            player.setScore(0);
            player.gameover();
            Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
            thisStage.close();
        }
    }
}
