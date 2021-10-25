import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.Scanner;


public class controller extends mainController {
    @FXML
    private Label myLabel;
    @FXML
    private TextField TextField_search;
    @FXML
    private TextArea myTextArea;
    @FXML
    private Button add, delete, textTranslate;
    @FXML
    private MenuButton menuButton;
    @FXML
    private ListView listView;

    private String string = "";
    private String tmp = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Action(ActionEvent event) throws IOException {
        String word = TextField_search.getText();
        myTextArea.setText(getFunction().lookUp(word.toLowerCase()));
        if (!getFunction().lookUp(word.toLowerCase()).equals("khong tim thay")) myLabel.setText(word.toLowerCase());
        tmp = word;
    }

    public void Voice(ActionEvent event) {
        if (!tmp.equals("khong tim thay")){
            VoiceImplement voice = new VoiceImplement();
            voice.TextToSpeech(tmp);
        }

    }

    public void keyPressed(KeyEvent e) {
        if(e.getCode().equals(KeyCode.BACK_SPACE) && !string.isEmpty()) {
            string = string.replace(Character.toString(string.charAt(string.length() - 1)), "");
        } else if (e.getCode().equals(KeyCode.ENTER) && !string.isEmpty()) {
            myTextArea.setText(getFunction().lookUp(string.toLowerCase()));
            if (!getFunction().lookUp(string.toLowerCase()).equals("khong tim thay")) myLabel.setText(string.toLowerCase());
            tmp = string;
        } else {
            string += e.getText();
        }
        listView.getItems().clear();
        ArrayList<String> s = getFunction().exportPrefixList(string);
        listView.getItems().addAll(s);
        listView.getSelectionModel();
    }

    public void selectFromList(MouseEvent event) {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            String word = (String)listView.getSelectionModel().getSelectedItem();
            myLabel.setText(word.toLowerCase());
            myTextArea.setText(getFunction().lookUp(word));
            tmp = word;
        }
    }

    public void switchToAddWord(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage) add.getScene().getWindow();
        window.setScene(scene);
        addController callFunction = loader.getController();
        callFunction.setFunction(getFunction());
        window.show();

    }

    public void switchToDeleteWord(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("deleteword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage) delete.getScene().getWindow();
        window.setScene(scene);
        deleteController callFunction = loader.getController();
        callFunction.setFunction(getFunction());
        window.show();
    }

    public void switchTextTranslate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("textTranslate.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage window = (Stage) textTranslate.getScene().getWindow();
        window.setScene(scene);
        textController callFunction = loader.getController();
        callFunction.setFunction(getFunction());
        window.show();
    }



}
