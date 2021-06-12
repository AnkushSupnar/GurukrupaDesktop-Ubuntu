package application.controller.home;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import application.controller.util.CommonData;
import application.controller.util.ViewUtil;
import application.guiUtil.AlertNotification;
import hibernate.entities.Login;
import hibernate.service.service.CounterService;
import hibernate.service.service.LoginService;
import hibernate.service.serviceimpl.CounterServiceImpl;
import hibernate.service.serviceimpl.LoginServiceImpl;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
//import service.CounterService;
//import service.LoginService;

public class LoginController implements Initializable {
	  	@FXML private AnchorPane mainWindow;
	 	@FXML private TextField txtCountrName;
		@FXML private TextField txtUserName;
	    @FXML private PasswordField txtPassword;
	    @FXML private Button btnLogin;
	    @FXML private Button btnCancel;
	    @FXML private Hyperlink linkAddCounter;
	    @FXML private Hyperlink linkAddUser;

	   
	    private SuggestionProvider<String> userNameProvider;
	    private SuggestionProvider<String> counterNameProvider;
	    private List<String>counterNames = new ArrayList<>();
	    private List<String>userNames = new ArrayList<>();
	    private LoginService loginService;
	    private CounterService counterService;
	    private ViewUtil viewUtil;
	  
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	
	    	counterService = new CounterServiceImpl();
	    	loginService = new LoginServiceImpl();
	    	viewUtil = new ViewUtil();
	    	userNames.addAll(loginService.getAllUserNames());
	    	counterNames.addAll(counterService.getAllCounterNames());

	    	
	    	if(counterNames.size()==0)
	    	{
	    		//linkAddCounter.fire();
	    		new Alert(AlertType.ERROR,"No Counter Available Click on Add Counter Link").showAndWait();
	    		//new AlertNotification().showErrorMessage("No Counter Available Click on Add Counter Link");
	    		linkAddCounter.setVisible(true);
	    		linkAddCounter.requestFocus();
	    		return;
	    	}
	    	if(userNames.size()==0)
	    	{	    		
	    		new Alert(AlertType.ERROR,"No User Available Click on Add User Link").showAndWait();
	    		linkAddUser.setVisible(true);
	    		linkAddUser.requestFocus();
	    		return;
	    	}
	    	
	    	counterNameProvider = SuggestionProvider.create(counterNames);
	    	new AutoCompletionTextFieldBinding<>(txtCountrName, counterNameProvider);	    	
	    	userNameProvider = SuggestionProvider.create(userNames);
	    	new AutoCompletionTextFieldBinding<>(txtUserName, userNameProvider);
	    	txtCountrName.requestFocus();
	    	
		}
	    @FXML
	    void addCounterAction(ActionEvent event) {
	    	try {
				viewUtil.changeWindow(event, "create/createcounter");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void addUserAction(ActionEvent event) {
	    	try {
				viewUtil.changeWindow(event, "create/adduser");
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void btnCancelAction(ActionEvent event) {

	    }

	    @FXML
	    void btnLoginAction(ActionEvent event) {
	    	try {
				if(txtUserName.getText().equals(""))
				{
					new Alert(AlertType.ERROR,"Select User Name!!!").showAndWait();
					txtUserName.requestFocus();
					return;
				}
				if(txtPassword.getText().equals(""))
				{
					new Alert(AlertType.ERROR,"ENter User Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
				Login login = loginService.getLoginByName(txtUserName.getText());
				if(!login.getPassword().equals(txtPassword.getText()))
				{
					new Alert(AlertType.ERROR,"Wron Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
				new AlertNotification().showSuccessMessage("Login Success");
				CommonData.login = login;
				new ViewUtil().changeWindow(event, "home/homepage");
			} catch (Exception e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,"Error "+e.getMessage()).showAndWait();				
			}
	    }

		
}
