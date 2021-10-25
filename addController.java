import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addController extends mainController {

    @FXML
    private Button back;
    @FXML
    private TextField newWord;
    @FXML
    private TextField meaning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scene1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage)back.getScene().getWindow();
        window.setScene(scene);
        controller callFunction = loader.getController();
        callFunction.setFunction(getFunction());
        window.show();
    }

    public void addWord(ActionEvent event) throws IOException {
        String word = newWord.getText();
        String explain = meaning.getText();
        if (!word.isEmpty() && !explain.isEmpty()) {
            if (getFunction().lookUp(word).equals("khong tim thay")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("thong bao");
                alert.setContentText("thanh cong");
                alert.show();
                getFunction().add(word.toLowerCase().trim(), explain);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("thong bao");
                alert.setContentText("tu nay da co san");
                alert.show();
            }
        }
    }

}
