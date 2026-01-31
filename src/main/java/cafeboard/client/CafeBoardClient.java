package cafeboard.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CafeBoardClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeBoardClient.class.getResource("/main.fxml"));
        Parent root = fxmlLoader.load();

        UiController guiController = fxmlLoader.getController();

        Scene scene = new Scene(root, 614, 789);
        stage.setTitle("Java-Café");
        stage.setScene(scene);
        stage.show();

        // Netzwerkverbindung zum bereits laufenden Server
        NetworkListener networkListener = new NetworkListener("localhost", 5000, guiController);
        new Thread(networkListener).start();
    }
}
