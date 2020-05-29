
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class SaveController {

    @FXML
    private AnchorPane SaveUP;

    @FXML
    private AnchorPane SaveLow;

    @FXML
    private Button DirectoryButton;

    @FXML
    private TextField pathField;

    @FXML
    private Button SaveButton;

    @FXML
    private TextField fileField;


    @FXML
    void initialize() {
        DirectoryButton.setOnAction(actionEvent -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Save file");
            File defaultDirectory = new File(".");
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog((((Button) actionEvent.getSource()).getScene().getWindow()));
            try {
                pathField.setText(selectedDirectory.toString());
            } catch (NullPointerException ignored) {

            }
        });

        SaveButton.setOnAction(actionEvent -> {
            try {
                if (new File(pathField.getText()).exists()) {
                    if (fileField.getText().endsWith(".xml")) {
                        String path = pathField.getText().concat(File.separator).concat(fileField.getText());
                        Serialization.LongTermPersistenceWrite(MyCollections.list, path);
                        SaveButton.getScene().getWindow().hide();
                    } else {
                        Warning.showWarnWithHeaderText("File should be .xml");
                    }
                } else {
                    Warning.showErrorWithHeaderText("FILE NOT FOUND!");
                }
            }catch (Exception e){
                System.err.println(e.getCause());
            }
        });
    }
}