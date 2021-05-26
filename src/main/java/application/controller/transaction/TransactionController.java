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
    private BorderPane home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void btnBillingAction(ActionEvent event) {
        pane = new ViewUtil().getPage("transaction/billing");
        home = (BorderPane) mainPane.getParent();
        home.setCenter(pane);
    }

    @FXML
    void btnVIewAllAction(ActionEvent event) {

    }

}
