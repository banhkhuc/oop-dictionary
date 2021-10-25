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

public class deleteController extends mainController {
    @FXML
    private Button back;
    @FXML
    private TextField wordDelete;

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

    public void deleteWord(ActionEvent event) {
        String word = wordDelete.getText();
        if (!word.isEmpty()) {
            if (getFunction().lookUp(word.toLowerCase().trim()).equals("khong tim thay")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("thong bao");
                alert.setContentText("tu nay khong ton tai");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("thong bao");
                alert.setContentText("xoa thanh cong");
                getFunction().delete(word.toLowerCase().trim());
                alert.show();
            }
        }
    }

}
