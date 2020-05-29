//основное окно

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {

    @FXML
    private TableView<Composition> tableCompositions;

    @FXML
    private TableColumn<Composition, String> nameColumn;

    @FXML
    private TableColumn<Composition, String> genreColumn;

    @FXML
    private TableColumn<Composition, String> lyricsColumn;

    @FXML
    private TableColumn<Composition, String> infoColumn;

    @FXML
    private TableColumn<Composition, String> fileColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button sortButton;

    @FXML
    private TextField countOfGeneratedField;

    @FXML
    private Button generateButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button saveDataButton;

    @FXML
    private Button addButton;

    @FXML
    private RadioButton sortName;

    @FXML
    private RadioButton sortRating;

    @FXML
    private RadioButton findAuthor;

    @FXML
    private ToggleGroup sort1;

    @FXML
    private RadioButton findName;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button resetButton;




    private int extractInt(String s) {
        String num = s.replaceAll("\\D", "");
        // return 0 if no digits found
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }



    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        nameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getNameAndAuthor()));
        genreColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getGenre()));
        lyricsColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().parseLyrics()));
        infoColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getRatingsAndDate()));
        fileColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getFileInfo()));


        // заполняем таблицу данными
        tableCompositions.setItems(MyCollections.compositionsObservableList);

        deleteButton.setOnAction(actionEvent ->{
            ObservableList<Composition> accountsSelected = tableCompositions.getSelectionModel().getSelectedItems();
            ArrayList<Composition> items =  new ArrayList<>(tableCompositions.getSelectionModel().getSelectedItems());
            MyCollections.compositionsObservableList.removeAll(accountsSelected);
            tableCompositions.getSelectionModel().clearSelection();
            MyCollections.list.removeAll(items);
        });

        sortButton.setOnAction(actionEvent -> {
            if (sortName.isSelected()) {
                MyCollections.compositionsObservableList.sort(Comparator.comparing(o -> o.getName()));
            } else if (sortRating.isSelected()) {
                MyCollections.compositionsObservableList.sort(Comparator.comparingInt(Composition::getMiddleRatings));
            } else {
                MyCollections.compositionsObservableList.sort(Comparator.comparing(o -> o.getAuthor()));
            }
        });

        searchButton.setOnAction(actionEvent -> {
                    MyCollections.compositionsObservableList.clear();
                    String searchPredicate = searchField.getText();
                    if (findAuthor.isSelected()) {
                        for (Composition e : MyCollections.list) {
                            if (searchPredicate.equalsIgnoreCase(e.getAuthor())) {
                                MyCollections.compositionsObservableList.add(e);
                            }
                        }
                    } else if (findName.isSelected()) {
                        for (Composition e : MyCollections.list) {
                            if (searchPredicate.equalsIgnoreCase(e.getName())) {
                                MyCollections.compositionsObservableList.add(e);
                            }
                        }
                    }
                });

        generateButton.setOnAction(actionEvent -> {
            int count = Integer.parseInt(countOfGeneratedField.getText());
            MyCollections.compositionsObservableList.clear();
            MyCollections.list.clear();
            Generator.generator(count);
            MyCollections.compositionsObservableList.addAll(MyCollections.list);
        });

        loadDataButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("load.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        saveDataButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("save.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        addButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        resetButton.setOnAction(actionEvent -> {
            MyCollections.compositionsObservableList.clear();
            MyCollections.compositionsObservableList.addAll(MyCollections.list);
        });

    }

    private void initData() {
        Generator.generator(10);
        MyCollections.compositionsObservableList.addAll(MyCollections.list);
    }


}
