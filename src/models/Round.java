package models;

public class Round {
    //atributos
    public int points;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correct;
    //constructores
    public Round(int level){
        //logica de buscar preguntas
        try {
            Question newQuestion = Conector.consulta(level);
            this.question = newQuestion.getQuestion();
            this.optionA=newQuestion.getAnswer1();
            this.optionB=newQuestion.getAnswer2();
            this.optionC=newQuestion.getAnswer3();
            this.optionD=newQuestion.getAnswer4();
            this.correct = newQuestion.getCorrectAnswer();
            this.points = level*100;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        }

    }
    //setters y getters
    public String getQuestion() {
        return question;
    }
    public String getOptionA() {
        return optionA;
    }
    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrect() {
        return correct;
    }


    
    
}
