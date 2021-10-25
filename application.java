import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

public class application extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("scene1.fxml"));
            Parent root = loader.load();
            Image icon = new Image("C:\\Users\\Admin\\Documents\\Dictionary_V\\src\\icon.png");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Basic Dictionary");
            primaryStage.show();

            controller callFunction = loader.getController();
            DictionaryManagement data = new DictionaryManagement();
            data.takeDataFromText();
            callFunction.setFunction(data);

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
