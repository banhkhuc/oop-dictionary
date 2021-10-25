import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    private DictionaryManagement function;

    public void setFunction(DictionaryManagement function) {
        this.function = function;
    }

    public DictionaryManagement getFunction() {
        return function;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
