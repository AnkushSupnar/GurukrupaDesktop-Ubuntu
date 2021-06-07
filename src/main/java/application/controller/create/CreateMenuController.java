package application.controller.create;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.controller.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class CreateMenuController implements Initializable {
	 	@FXML private AnchorPane mainPanel;
	 	private ViewUtil viewUtil;
	 	private BorderPane home;
	 	private AnchorPane root;
	 	private Pane  pane,addItem,viewItems,addCustomer,addBank;
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			viewUtil = new ViewUtil();
		}

		@FXML
	    void btnAddItemAction(ActionEvent event) {
			root = (AnchorPane) mainPanel.getParent();
			root.getChildren().remove(2);
			try {
				pane = FXMLLoader.load(getClass().getResource("/view/create/additem.fxml"));
				root.getChildren().add(2,pane);
				pane.setLayoutX(10);
				pane.setLayoutY(40);
				System.out.println(root.getChildren().indexOf(pane));
			} catch (IOException e) {
				e.printStackTrace();
			}
//			addItem = viewUtil.getPage("create/additem");
//	    	home = (BorderPane) mainPanel.getParent();
//	    	home.setCenter(addItem);

	    }
		@FXML
	    void btnViewAllItemAction(ActionEvent event) {
			viewItems = viewUtil.getPage("report/viewallitems");
	    	home = (BorderPane) mainPanel.getParent();
	    	home.setCenter(viewItems);
	    	//mainPanel.setVisible(false);
	    }
		
		@FXML
		void btnAddCustomerAction(ActionEvent event) {
			addCustomer = viewUtil.getPage("create/addcustomer");
			home = (BorderPane) mainPanel.getParent();
			home.setCenter(addCustomer);
			//mainPanel.setVisible(false);
		}

		@FXML
		void btnAddBankAction(ActionEvent event) {
			addBank = viewUtil.getPage("create/addbank");
			home = (BorderPane) mainPanel.getParent();
			home.setCenter(addBank);
			//mainPanel.setVisible(false);
		}
	}
