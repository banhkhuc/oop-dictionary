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

public class textController extends mainController {
    @FXML
    private Button back;
    @FXML
    private TextArea text;
    @FXML
    private TextArea trans;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setWrapText(true);
        trans.setWrapText(true);
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

    public void translate(ActionEvent event) throws IOException {
        String string = text.getText();
        if (label1.getText().equals("Tiếng Việt")) {
            trans.setText(getFunction().translate("VI", "EN", string));
        }
        else {
            trans.setText(getFunction().translate("EN", "VI", string));
        }
    }

    public void swap(ActionEvent event) {
        String s = label1.getText();
        label1.setText(label2.getText());
        label2.setText(s);
    }

}