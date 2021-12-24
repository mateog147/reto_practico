import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = (new FXMLLoader(getClass().getResource("./views/loginView.fxml"))).load();
        Scene scene =new Scene(root);
        stage.setTitle("JUGAR");
        stage.setScene(scene);
        stage.show();
    }
}
