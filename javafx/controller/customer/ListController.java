package javafx.controller.customer;


import javafx.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.database.Connector;
import javafx.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListController implements Initializable {


    public TableView<Customer> tbView;
    public TableColumn <Customer, String> tcID;
    public TableColumn <Customer, String> tcName;
    public TableColumn<Customer, LocalDate> tcDOB;
    public TableColumn <Customer, String> tcAddress;
    public TableColumn <Customer, String> tcCar;
    public TableColumn<Customer, LocalDate> tcDateStart;
    public TableColumn <Customer, String> tcDays;



    public void create(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../fxml/car/addController.fxml"));

        Main.rootStage.setScene(new Scene(root, 800, 600));
    }


    private List<Customer> FetchCustomerFromDatabase() throws SQLException, ClassNotFoundException {
       List<Customer> customerList = new ArrayList<Customer>();
        Connector connector = Connector.getInstance();
        Connection connection = connector.getConn();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from customerlist");
        while (rs.next()) {
            // Get each field fromt the result set
            Integer id = rs.getInt(1);
            String fullname = rs.getString(2);
            LocalDate dob = rs.getDate(3).toLocalDate();
            String carName = rs.getString(5);
            LocalDate dateStart = rs.getDate(3).toLocalDate();
            String day = rs.getString(7);
            String address = rs.getString(4);

            // Create new customer

            Customer customer = new Customer(id, fullname, dob, carName, dateStart, day, address);
            customerList.add(customer);
        }
        return customerList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcID.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Customer, String>("fullName"));
        tcDOB.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("dob"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        tcCar.setCellValueFactory(new PropertyValueFactory<Customer, String>("car"));
        tcDateStart.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("dateStart"));
        tcDays.setCellValueFactory(new PropertyValueFactory<Customer, String>("days"));

        List<Customer> listCustomer = null;
        try {
            listCustomer = FetchCustomerFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Customer> data = FXCollections.observableArrayList(listCustomer);
        tbView.setItems(data);
//        customerInfoHyperlink.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Display the customer table when the hyperlink is clicked
//                ShowCustomer();
//            }
//
//            private void ShowCustomer() {
//            }
//        });
    }
//    public void Back(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../../../javafx/fxml/homePage.fxml"));
//
//        Main.rootStage.setScene(new Scene(root, 800, 600));
//        Stage parentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
//        parentStage.close();
//    }
    public void closeCustomerForm(ActionEvent actionEvent) {

        Stage parentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        parentStage.close();
    }


}