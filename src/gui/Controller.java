package gui;
import Domain.Meal;

import javafx.collections.FXCollections;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.Service;

import java.util.Comparator;
import java.util.List;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Meal> InitialList;

    public void populate(){
        List<Meal> initial=service.getAll();
        List<Meal> sortedList=initial.stream().sorted(Comparator.comparing(Meal::getName)).toList();
        ObservableList<Meal> lv = FXCollections.observableArrayList(sortedList);
        InitialList.setItems(lv);
    }
    public void initialize(){
        populate();
    }

    @FXML
    private TextField searchIng;
    @FXML
    private TextField searchTime;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<Meal> secondList;
    @FXML
    void searchButtonAction(ActionEvent event) {
        secondList.setItems(null);
        String ingredient=searchIng.getText();
        Integer time=Integer.parseInt(searchTime.getText());
        List<Meal> initial=service.getAll();
        List<Meal> secondList2=initial.stream().filter(x->x.getTime()<=time && x.getIngredients().contains(ingredient)).toList();
        ObservableList<Meal> lv = FXCollections.observableArrayList(secondList2);
        secondList.setItems(lv);
    }


}
