package application.controller.create;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.guiUtil.AlertNotification;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;
import hibernate.service.serviceimpl.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AddUserController implements Initializable {

	   	@FXML private AnchorPane mainPanel;
	    @FXML private TextField txtPerson;
	    @FXML private TextField txtUserName;
	    @FXML private PasswordField txtPassword;
	    @FXML private PasswordField txtPassword1;
	    @FXML private Button btnCreate;
	    @FXML private Button btnCancel;
	    private LoginService service;
	    private List<Login>loginList = new ArrayList<>();
	    @Override
		public void initialize(URL location, ResourceBundle resources) {			
			service = new LoginServiceImpl();
			loginList.addAll(service.getAllLogin());
			
	    }
	    
	    @FXML
	    void btnCancelAction(ActionEvent event) {
	    	txtUserName.setText("");
	    	txtPassword.setText("");
	    	txtPassword1.setText("");
	    	txtPerson.setText("");
	    }

	    @FXML
	    void btnCreateAction(ActionEvent event) {
	    	if(txtPerson.getText().equals(""))
	    	{
	    		new AlertNotification().showErrorMessage("Enter Your Name !!!");
	    		txtPerson.requestFocus();
	    		return;
	    	}
	    	if(txtUserName.getText().equals(""))
	    	{
	    		new AlertNotification().showErrorMessage("Enter User Name");
	    		txtUserName.requestFocus();
	    		return;
	    	}
	    	
	    	if(txtPassword.getText().equals("") || txtPassword1.getText().equals(""))
	    	{
	    		new AlertNotification().showErrorMessage("Enter Password");
	    		return;
	    	}
	    	if(!txtPassword.getText().equalsIgnoreCase(txtPassword1.getText()))
	    	{
	    		new AlertNotification().showErrorMessage("Password Not Matched");
	    		return;
	    	}
	    	Login login;
			login = null;
			for(Login l:loginList)
	    	{
	    		if(l.getPerson().equals(txtPerson.getText()))
	    		{
	    			login=l;
	    			break;
	    		}
	    	}
	    	if(login==null)
	    	{
	    		System.out.println("NOt found");
	    		login = new Login();
	    		login.setPerson(txtPerson.getText());
	    		login.setPassword(txtPassword.getText());
	    		login.setUserName(txtUserName.getText());
	    		if(service.saveLogin(login)==1)
	    		{
	    			
	    			new AlertNotification().showSuccessMessage("Login Saved Success");
	    			btnCancel.fire();
	    		}
	    		else
	    		{
	    			new AlertNotification().showErrorMessage("Login Not Saved");
	    		}
	    	}
	    	else
	    	{
	    		login.setUserName(txtUserName.getText());
	    		login.setPassword(txtPassword.getText());
	    		if(service.saveLogin(login)==2)
	    		{
	    			new AlertNotification().showSuccessMessage("Login Update Success");
	    			btnCancel.fire();
	    		}
	    		else
	    		{
	    			new AlertNotification().showErrorMessage("Error in Update Login");
	    		}
	    	}
	    }
}
