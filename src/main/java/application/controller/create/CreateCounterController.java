package application.controller.create;

import java.net.URL;
import java.util.ResourceBundle;

import application.guiUtil.AlertNotification;
import entity.entities.Counter;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CounterService;
public class CreateCounterController implements Initializable {
	   	@FXML private TextField txtName;
	    @FXML private TextField txtShopNo;
	    @FXML private TextField txtRoad;
	    @FXML private TextField txtCity;
	    @FXML private TextField txtTaluka;
	    @FXML private TextField txtDistrict;
	    @FXML private TextField txtPin;
	    @FXML private TextField txtPerson;
	    @FXML private TextField txtContact;
	    @FXML private TextField txtBillNoInitial;
	    @FXML private Button btnClear;
	    
	    @FXML private TableView<Counter> table;
	    @FXML private TableColumn<Counter,Integer> colId;
	    @FXML private TableColumn<Counter,String> colCounterName;
	    @FXML private TableColumn<Counter,String> colAddress;
	    @FXML private TableColumn<Counter,String> colPerson;
	    
	    
	    
	    private CounterService counterService; 
	    private ObservableList<Counter> allCounters = FXCollections.observableArrayList();
	    private int id; 
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			id=0;
			counterService = new CounterService();
			allCounters.addAll(counterService.getAllCounters());
			txtBillNoInitial.setText(""+((char)(allCounters.size()+65)));
			
			colId.setCellValueFactory(new PropertyValueFactory<>("id"));
			colCounterName.setCellValueFactory(new PropertyValueFactory<>("counterName"));
			colAddress.setCellValueFactory(new PropertyValueFactory<>("road"));
			colPerson.setCellValueFactory(new PropertyValueFactory<>("person"));
			listTable();
			table.setItems(allCounters);
			
	}
	  @FXML
	    void btnClearAction(ActionEvent event) {
			txtName.setText("");
			txtShopNo.setText("");
			txtRoad.setText("");
			txtCity.setText("");
			txtTaluka.setText("");
			txtDistrict.setText("");
			txtPin.setText("");
			txtPerson.setText("");
			txtContact.setText("");
			txtBillNoInitial.setText("");
			allCounters.clear();
			allCounters.addAll(counterService.getAllCounters());
			txtBillNoInitial.setText("" + ((char) (allCounters.size() + 65)));
			id=0;
	    }

	    @FXML
	    void btnCreateAction(ActionEvent event) {
	    	if(validateData()!=1)
	    	{
	    		return;
	    	}
	    	Counter counter = new Counter(txtName.getText(),
	    			txtShopNo.getText(),
	    			txtRoad.getText(),
	    			txtCity.getText(),
	    			txtTaluka.getText(),
	    			txtDistrict.getText(), 
	    			Integer.parseInt(txtPin.getText()),
	    			txtPerson.getText().trim(),
	    			txtContact.getText(), 
	    			txtBillNoInitial.getText().charAt(0));
	    	counter.setId(id);
	    	service.CounterService ser = new service.CounterService();
	    	int flag = ser.saveCounter(counter);
	    	if(flag==1)
	    	{
	    		new AlertNotification().showSuccessMessage("Counter Save Success!!!");
	    		allCounters.add(counter);
	    		listTable();
	    		btnClear.fire();
	    	}
	    	
	    }

	    @FXML
	    void btnEditAction(ActionEvent event) {
	    	if(table.getSelectionModel().getSelectedItem()==null)
	    	{
	    		return;
	    	}
	    	Counter c;
			c = counterService.getCounterById(table.getSelectionModel().getSelectedItem().getId());
			if(c==null)
	    	{
	    		return;
	    	}
	    	txtBillNoInitial.setText(""+c.getBillinitial());
	    	txtCity.setText(c.getCity());
	    	txtContact.setText(c.getContact());
	    	txtDistrict.setText(c.getDistrict());
	    	txtName.setText(c.getCounterName());
	    	txtPerson.setText(c.getPerson());
	    	txtPin.setText(""+c.getPin());
	    	txtRoad.setText(c.getRoad());
	    	txtShopNo.setText(c.getShopNo());
	    	txtTaluka.setText(c.getTaluka());
	    	id = c.getId();
	    }

	    @FXML
	    void btnHOmeAction(ActionEvent event) {

	    }
	    private int validateData()
	    {
	    	try {
				if(txtName.getText().equals(""))
				{
					//new Alert(AlertType.ERROR,"Enter Counter Name!!!").showAndWait();
					//new AlertNotification().showErrorMessage("Enter Counter Name!!!");
					new AlertNotification().showErrorMessage("Enter Counter Name!!!");
					txtName.requestFocus();
					return 0;
				}
				for(Counter c:allCounters)
				{
					if(c.getCounterName().equals(txtName.getText())&& id==0)
					{
						new AlertNotification().showErrorMessage("Counter Name Already Exist!!!");
						txtName.requestFocus();
						return 0;
					}
				}				
				if(txtShopNo.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Shop No!!!");
					//new Alert(AlertType.ERROR,"Enter Shop No!!!").showAndWait();
					txtShopNo.requestFocus();
					return 0;
				}
				if(txtRoad.getText().equals(""))
				{
					txtRoad.setText("-");
				}
				if(txtCity.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter City or Village Name!!!");					
					txtCity.requestFocus();
					return 0;
				}
				if(txtTaluka.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Counter Name Already Exist!!!");
					txtTaluka.requestFocus();
					return 0;
				}
				if(txtDistrict.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter District Name!!!");
					txtDistrict.requestFocus();
					return 0;
				}
				if(txtPin.getText().equals("") || !isNumber(txtPin.getText()))
				{
					new AlertNotification().showErrorMessage("Enter Pin Code in Digit!!!");
					txtPin.requestFocus();
					return 0;
				}
				if(txtPerson.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Person Name");
					//new Alert(AlertType.ERROR,"Enter Person Name!!!").showAndWait();
					txtPerson.requestFocus();
					return 0;
				}
				if(txtContact.getText().equals(""))
				{
					new AlertNotification().showErrorMessage("Enter Contact Number!!!");
					txtContact.requestFocus();
					return 0;
				}
				return 1;
			} catch (Exception e) {
				new AlertNotification().showErrorMessage("Error in Validate Data!!!");
				return 0;
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
	    private void listTable()
	    {
	    	for(int i=0;i<allCounters.size();i++)
	    	{
	    		allCounters.get(i).setRoad(getAddress(allCounters.get(i)));
	    				
	    	}
	    }
	    private String getAddress(Counter counter)
	    {
	    	return "ShopNo:"+counter.getShopNo()+",Road:"+counter.getRoad()
	    	+",City:"+counter.getCity()+".Taluka:"+counter.getTaluka()
	    	+"District:"+counter.getDistrict()+",Pin:"+counter.getPin();
	    }
}
