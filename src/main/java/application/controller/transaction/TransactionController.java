package application.controller.transaction;

import application.controller.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    @FXML private AnchorPane mainPane;
    @FXML private Button btnBilling;
    @FXML private Button btnViewAllBills;

    private Pane pane;
    private AnchorPane home;
    private BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void btnBillingAction(ActionEvent event) {
        pane = new ViewUtil().getPage("transaction/billingframe");
        root = (BorderPane) mainPane.getParent();
        root.setCenter(null);
        pane.setLayoutY(40);
        pane.setLayoutX(0);
        root.setLeft(pane);
//
//        home = (AnchorPane) mainPane.getParent();
//        home.getChildren().remove(2);
//        home.getChildren().add(2,pane);
//        pane.setLayoutY(40);
    }

    @FXML
    void btnVIewAllAction(ActionEvent event) {

    }

}
