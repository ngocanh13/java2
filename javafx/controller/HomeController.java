package javafx.controller;

import javafx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    public void goToCar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/car/outCars.fxml"));

        Main.rootStage.setScene(new Scene(root, 800, 600));
    }




    public void goToCostomer(ActionEvent actionEvent) throws IOException {
     //Parent root = FXMLLoader.load(getClass().getResource("../fxml/car/listCustomers.fxml"));
//
//        Main.rootStage.setScene(new Scene(root, 800, 600));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/customer/listcustomer.fxml"));
        Stage stage = new Stage();
        Stage primaryStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void goToDone(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/car/doneList.fxml"));

        Main.rootStage.setScene(new Scene(root, 800, 600));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");

    }
}