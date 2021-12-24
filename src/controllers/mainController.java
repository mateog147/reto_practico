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

public class mainController {
    private Player player;
    private int level;
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player p) {
        this.player = p;
        if(this.player != null){
            userLabel.setText(this.player.getUserName());
            scoreLabel.setText(Integer.toString(this.player.getScore()));
        }
    }
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

    @FXML
    void quit(MouseEvent event) {
        player.gameover();
        Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void nextRound(MouseEvent event) {
        this.level+=1;
        if(level>=6){
            player.gameover();
            Stage thisStage = (Stage) this.errorLabel.getScene().getWindow();
            thisStage.close();
        }
        else{
            setScreen(new Round(this.level));
            correctBtn.setVisible(false);
            aBtn.setDisable(false);
            bBtn.setDisable(false);
            cBtn.setDisable(false);
            dBtn.setDisable(false);
        }

    }

    @FXML
    void initialize(){
        this.level= 1;
        Round round = new Round(level);
        setScreen(round);
    }

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
