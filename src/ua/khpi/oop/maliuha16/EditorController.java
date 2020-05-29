import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.*;

//добавление новых объектов
public class EditorController {

    @FXML
    private TextField nameCompField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField genreField;

    @FXML
    private TextField authorField;

    @FXML
    private TextArea lyricsField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField dataFormatField;

    @FXML
    private TextField sourceRatingsField;

    @FXML
    private TextField ratingsField;



    @FXML
    void initialize() {

        signUpButton.setOnAction(actionEvent -> {

            Composition composition = new Composition();

            StringBuilder sb = new StringBuilder();
            boolean add = true;
            int i = 1;
            String name = nameCompField.getText();
            String genre = genreField.getText();
            String author = authorField.getText();
            List<String> lyrics = Collections.singletonList(lyricsField.getText());
            String date = dateField.getText();
            String duration = durationField.getText();
            String dataFormat = dataFormatField.getText();
            String sourceRatings = sourceRatingsField.getText();
            String ratings = ratingsField.getText();


            if (!Regex.checkGenre(genre)) {
                add = false;
                System.out.println("error");
                sb.append(i++).append(") Error in genre.\n");
            }

            if (!Regex.checkDate(date)) {
                add = false;
                System.out.println("error");
                sb.append(i++).append(") Error in date.\n");
            }

            if (!Regex.checkNumber(duration)) {
                add = false;
                System.out.println("error");
                sb.append(i++).append(") Error in duration.\n");
            }

            if (!Regex.checkDataFormat(dataFormat)) {
                add = false;
                System.out.println("error");
                sb.append(i++).append(") Error in format of file.\n");
            }

            try {
                if (add) {
                    composition.setName(name);
                    composition.setGenre(genre);
                    composition.setAuthor(author);
                    composition.setDataFormat(dataFormat);
                    composition.setDurationSec(Integer.parseInt(duration));
                    composition.setDate(date);
                    composition.setLyrics(lyrics);
                    Map<String, Integer> rating = new LinkedHashMap<>();
                    rating.put(sourceRatings, Integer.parseInt(ratings));
                    composition.setRatings((LinkedHashMap) rating);
                    MyCollections.list.add(composition);
                    MyCollections.compositionsObservableList.clear();
                    MyCollections.compositionsObservableList.addAll(MyCollections.list);

                    signUpButton.getScene().getWindow().hide();
                }
            } catch (Exception e) {
                System.err.println(e.getCause());
            }
        });
    }
}
