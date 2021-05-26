package application.controller.create;

import java.net.URL;
import java.util.ResourceBundle;

import application.guiUtil.AlertNotification;
import entity.entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.CustomerService;

public class AddCustomerController implements Initializable {

		@FXML private AnchorPane mainPane;
		@FXML private TextField txtFname;
	    @FXML private TextField txtMname;
	    @FXML private TextField txtLname;
	    @FXML private TextField txtHouseno;
	    @FXML private TextField txtStreet;
	    @FXML private TextField txtCity;
	    @FXML private TextField txtTaluka;
	    @FXML private TextField txtDistrict;
	    @FXML private TextField txtPin;
	    @FXML private TextField txtContact;
	    @FXML private TextField txtCode;
	    
	    @FXML private TableView<Customer> table;
	    @FXML private TableColumn<Customer,Long>   colSrNo;
	    @FXML private TableColumn<Customer,String> colCode;
	    @FXML private TableColumn<Customer,String> colName;
	    @FXML private TableColumn<Customer,String> colAddress;
	    @FXML private TableColumn<Customer,String> colContact;
	    
	    private long id;
	    private CustomerService customerService;
	    private ObservableList<Customer>customerList=FXCollections.observableArrayList();
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	customerService = new CustomerService();
	    	
	    	colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	colCode.setCellValueFactory(new PropertyValueFactory<>("code"));    
	    	colName.setCellValueFactory(new PropertyValueFactory<>("fname"));
	    	colAddress.setCellValueFactory(new PropertyValueFactory<>("street"));
	    	colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
	    	customerList.addAll(customerService.getAllCustomer());
	    	ListToTable();
	    	table.setItems(customerList);
	    	
	    }
	    
	    @FXML
	    void btnClarAction(ActionEvent event) {
	    	clear();
	    }

	    private void clear() {
	    	txtFname.setText("");  
	    	txtMname.setText("");   
	    	txtLname.setText("");
	    	txtHouseno.setText("");
	    	txtStreet.setText("");
	    	txtCity.setText("");
	    	txtTaluka.setText("");
	    	txtDistrict.setText("");
	    	txtPin.setText("");
	    	txtCode.setText("");
	    	txtContact.setText("");
	    	id=0;
		}

		@FXML
	    void btnEditAction(ActionEvent event) {
	    	if(table.getSelectionModel().getSelectedItem()==null)
	    	{
	    		return;
	    	}
	    	Customer cust = table.getSelectionModel().getSelectedItem();
	    	if(cust==null)
	    	{
	    		return;
	    	}
	    	cust = customerService.getCuatomerById(cust.getId());
	    	if(cust==null)
	    	{
	    		new AlertNotification().showErrorMessage("No Customer Found");
	    		return;
	    	}
	    	txtFname.setText(cust.getFname());  
	    	txtMname.setText(cust.getMname());   
	    	txtLname.setText(cust.getLname());
	    	txtHouseno.setText(cust.getHouseno());
	    	txtStreet.setText(cust.getStreet());
	    	txtCity.setText(cust.getCity());
	    	txtTaluka.setText(cust.getTaluka());
	    	txtDistrict.setText(cust.getDistrict());
	    	txtPin.setText(""+cust.getPin());
	    	txtCode.setText(cust.getCode());
	    	txtContact.setText(cust.getContact());
	    	id=cust.getId();
	    }

	    @FXML
	    void btnHomeAction(ActionEvent event) {
	    	mainPane.setVisible(false);
	    }

	    @FXML
	    void btnSaveAction(ActionEvent event) {

	    	if(validateData()!=1)
	    	{
	    		return;
	    	}
	    	//code Generation and Checking Availability
	    	if(txtCode.getText().equals("")) {
	    		txtCode.setText(generateCode());
	    		return;
	    	}
	    	
	    		if(id==0 && customerService.getCuatomerByCode(txtCode.getText())!=null)
	    		{
	    			new AlertNotification().showErrorMessage("Customer Code Not Available please Choose Another");
	    			return;
	    		}	    	
	    	Customer customer = new Customer(
	    			txtCode.getText(),
	    			txtFname.getText(),
	    			txtMname.getText(),
	    			txtLname.getText(),
	    			txtHouseno.getText(),
	    			txtStreet.getText(),
	    			txtCity.getText(),
	    			txtTaluka.getText(),
	    			txtDistrict.getText(),
	    			Integer.parseInt(txtPin.getText()),
	    			txtContact.getText());
	    	customer.setId(id);
	    	if(id==0)
	    	{
	    		Customer c = customerService.saveCustomer(customer);
	    		if(c!=null)
	    		{
	    			new AlertNotification().showSuccessMessage("Customer Save Success");
	    			addInCustomerList(customer);
	    			clear();	    			
	    		}
	    		else
	    		{
	    			new AlertNotification().showErrorMessage("Error in Saving Data");
	    		}
	    	}
	    	else
	    	{
	    		Customer c = customerService.updateCustomer(customer);
	    		if(c!=null)
	    		{
	    			new AlertNotification().showSuccessMessage("Customer Update Success");
	    			addInCustomerList(customer);
	    			clear();
	    		}
	    		else
	    		{
	    			new AlertNotification().showErrorMessage("Error in Updating Data");
	    		}
	    	}
	    }
	    private int validateData()
	    {
	    	try {
				if(txtFname.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter First Name");
					txtFname.requestFocus();
					return 0;
				}
				if(txtMname.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Middle Name");
					txtMname.requestFocus();
					return 0;
				}
				if(txtLname.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Last Name");
					txtLname.requestFocus();
					return 0;
				}
				if(txtHouseno.getText().equals(""))
				{
					txtHouseno.setText(""+0);
				}
				if(txtStreet.getText().equals(""))
				{
					txtStreet.setText("-");
				}
				if(txtCity.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter City Name");
					txtCity.requestFocus();
					return 0;
				}
				if(txtTaluka.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Taluka Name");
					txtTaluka.requestFocus();
					return 0;
				}
				if(txtDistrict.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter District Name");
					txtDistrict.requestFocus();
					return 0;
				}
				if(txtPin.getText().equals("") ||!isNumber(txtPin.getText()))
				{
					new AlertNotification().showErrorMessage("Enter Pin Code in Digit");
					txtPin.requestFocus();
					return 0;
				}
				if(txtContact.getText().equals("") ||!isNumber(txtContact.getText()))
				{
					new AlertNotification().showErrorMessage("Enter Contact no in Digit");
					txtContact.requestFocus();
					return 0;
				}
				return 1;
				
			} catch (Exception e) {
				new AlertNotification().showErrorMessage("Error in Validating Data"+e.getMessage());
				return 0;
			}
	    }
	   
	    private String generateCode()
	    {
	    	try {
				if(!txtFname.getText().equals("")&& !txtMname.getText().equals("")&&!txtLname.getText().equals("") && txtCode.getText().equals(""))
				{
					return ""+txtFname.getText().trim().charAt(0)+txtMname.getText().trim().charAt(0)+txtLname.getText().trim().charAt(0);
				}
				else
					return "";
			} catch (Exception e) {
				return "";
			}
	    }
	    private boolean isNumber(String num) {
			if (num == null) {
				return false;
			}
			try {
				Double.parseDouble(num);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	    private void ListToTable()
	    {
	    	for(int i=0;i<customerList.size();i++)
	    	{
	    		customerList.get(i).setFname(customerList.get(i).getFname()+" "+customerList.get(i).getMname()+" "+customerList.get(i).getLname());
	    		customerList.get(i).setStreet(
	    				customerList.get(i).getHouseno()+","+
	    						customerList.get(i).getStreet()+","+
	    						customerList.get(i).getCity()+","+
	    						customerList.get(i).getTaluka()+","+
	    						customerList.get(i).getDistrict()+","+
	    						customerList.get(i).getPin()	    				
	    				);
	    	}
	    }
	    private void addInCustomerList(Customer cust)
	    {
	    	cust.setFname(cust.getFname()+" "+cust.getMname()+" "+cust.getLname());
	    	cust.setStreet(cust.getHouseno()+","+cust.getStreet()+","+cust.getCity()+","+cust.getTaluka()+","+cust.getDistrict()+","+cust.getPin());
	    	int flag=-1;
	    	for(int i=0;i<customerList.size();i++)
	    	{
	    		if(customerList.get(i).getId()==cust.getId())
	    		{
	    			flag=i;
	    		}
	    	}
	    	if(flag==-1)
	    	{
	    		customerList.add(cust);
	    	}
	    	else
	    	{
	    		customerList.remove(flag);
	    		customerList.add(flag,cust);
	    	}
	    }
}
