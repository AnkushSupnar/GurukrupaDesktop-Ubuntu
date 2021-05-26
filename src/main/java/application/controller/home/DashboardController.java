package application.controller.home;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.util.CommonData;
import application.controller.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class DashboardController implements Initializable {
	 @FXML private BorderPane mainPane;
	@FXML private Button menuTransaction;
    @FXML private Button menuCreate;
    @FXML private Button menuReport;
    @FXML private Button menuApplication;
    @FXML private TextField title;
    @FXML private Text txtUserName;

    private ViewUtil viewUtil;
    private Pane transactionPane,createPane;
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		viewUtil = new ViewUtil();
		title.setText("Home Page");
		txtUserName.setText(CommonData.login.getUserName());
		System.out.println(CommonData.login);
	}
	 @FXML
	    void menuCreate(ActionEvent event) {
		 createPane = viewUtil.getPage("create/createmenu");
		 mainPane.setCenter(createPane);
	    }

	    @FXML
	    void menuReportAction(ActionEvent event) {

	    }

	    @FXML
	    void menuTransactionAction(ActionEvent event) {
	    	transactionPane = viewUtil.getPage("transaction/transactionmenu");
	    	mainPane.setCenter(transactionPane);
	    }
	 
   

}