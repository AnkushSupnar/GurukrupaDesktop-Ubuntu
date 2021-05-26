package application.controller.create;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.guiUtil.AlertNotification;
import entity.entities.Item;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import service.ItemService;
public class AddItemController implements Initializable{
	 	@FXML private AnchorPane mainPane;
	    @FXML private TextField txtItemName;
	    @FXML private TextField txtMetal;
	    @FXML private TextField txtMetalWeight;
	    @FXML private TextField txtOtherWeight;
	    @FXML private TextField txtNetWeight;
	    @FXML private TextField txtUnit;
	    @FXML private TextField txtPurity;
 	    @FXML private TextField txtHsn;
	    @FXML private TextField txtLabour;
	    @FXML private TextField txtOther;
	    @FXML private Button btnSave;
	    @FXML private Button btnEdit;
	    @FXML private Button btnClear;
	    @FXML private Button btnHome;
	    @FXML private TableView<Item> table;
	    @FXML private TableColumn<Item,Long> colSrNo;
	    @FXML private TableColumn<Item,Double> colHsn;
	    @FXML private TableColumn<Item,String> colItemName;
	    @FXML private TableColumn<Item,String> colMetal;
	    @FXML private TableColumn<Item,Double> colMetalWeight;
	    @FXML private TableColumn<Item,Double> colOtherWeight;
	    @FXML private TableColumn<Item,String> colUnit;
	    @FXML private TableColumn<Item,Double> colPurity;
	    @FXML private TableColumn<Item,Double> colLabourCharge;
	    @FXML private TableColumn<Item,Double> colOtherCharge;
	    private long id;
	    private ItemService itemService;
	    private SuggestionProvider<String> metalNameProvider;
	    private List<String>metals = new ArrayList<String>();
	    private ObservableList<Item>itemList = FXCollections.observableArrayList();
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id=0;
		itemService = new ItemService();
		itemList.addAll(itemService.getAllItems());
		metals.add("Gold");
		metals.add("Silver");
		metals.add("Platinum");
		metalNameProvider = SuggestionProvider.create(metals);
		new AutoCompletionTextFieldBinding<>(txtMetal, metalNameProvider);
		
		 	 colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
		     colHsn.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		     colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
		     colMetal.setCellValueFactory(new PropertyValueFactory<>("metal"));
		     colMetalWeight.setCellValueFactory(new PropertyValueFactory<>("metalweight"));
		     colOtherWeight.setCellValueFactory(new PropertyValueFactory<>("otherweight"));
		     colUnit.setCellValueFactory(new PropertyValueFactory<>("weightunit"));
		     colPurity.setCellValueFactory(new PropertyValueFactory<>("purity"));
		     colLabourCharge.setCellValueFactory(new PropertyValueFactory<>("labourchareges"));
		     colOtherCharge.setCellValueFactory(new PropertyValueFactory<>("othercharges"));
	     
	     table.setItems(itemList);
	}
	@FXML
    void btnClearAction(ActionEvent event) {
		txtItemName.setText("");
		txtMetal.setText("");
		txtLabour.setText("");
		txtOther.setText("");
		txtHsn.setText("");
		txtMetalWeight.setText("");
		txtNetWeight.setText("");
		txtOtherWeight.setText("");
		txtPurity.setText("");
		id=0;
    }

	 @FXML
	  void txtMetalWeightAction(ActionEvent event) {
		 if(txtOtherWeight.getText().equals("")|| !isNumber(txtOtherWeight.getText()))
		 {
			 txtNetWeight.setText(txtMetalWeight.getText());
		 }
		 else
		 {
			 txtNetWeight.setText(""+(Double.parseDouble(txtMetalWeight.getText())+Double.parseDouble(txtOtherWeight.getText())));
		 }
		 if(txtMetalWeight.getText().equals("") || !isNumber(txtMetal.getText()))
		 {
			 return;
		 }
	    }
	 @FXML
	    void txtMetalKeyRelease(KeyEvent event) {
		 if(!isNumber(txtMetalWeight.getText()))
		 {
			 txtMetalWeight.setText("");
		 }
	    }
	 @FXML
	    void txtOtherWeightKeyRelease(KeyEvent event) {
		 if(!isNumber(txtOtherWeight.getText()))
		 {
			 txtOtherWeight.setText("");
		 }
	    }
	 @FXML
	    void txtOtherWeightAction(ActionEvent event) {
		 if(txtMetalWeight.getText().equals("") ||!isNumber(txtMetalWeight.getText()))
		 {
			 txtNetWeight.setText(txtOtherWeight.getText());
		 }
		 else if(txtOtherWeight.getText().equals(""))
		 {
			 return;
		 }
		 else{
			 txtNetWeight.setText(""+
		 (Double.parseDouble(txtMetalWeight.getText())+Double.parseDouble(txtOtherWeight.getText())));
		 }
		 
	    }
    @FXML
    void btnEditAction(ActionEvent event) {
    	try {
			if(table.getSelectionModel().getSelectedItem()==null)
			{
				return;
			}
			Item item = itemService.getItemById(table.getSelectionModel().getSelectedItem().getId());
			if(item==null)
			{
				System.out.println("Not Found");
				return;
			}
			txtItemName.setText(item.getItemname());
			txtMetal.setText(item.getMetal());
			txtUnit.setText(item.getWeightunit());
			txtLabour.setText(""+item.getLabouruchareges());
			txtOther.setText(""+item.getOthercharges());
			txtHsn.setText(""+item.getHsn());
			txtMetalWeight.setText(""+item.getMetalweight());
			txtNetWeight.setText(""+(item.getMetalweight()+item.getOtherweight()));
			txtPurity.setText(""+item.getPurity());
			txtOtherWeight.setText(""+item.getOtherweight());
			id = item.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnHomeAction(ActionEvent event) {
    	mainPane.setVisible(false);
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
    	try {
			if(validateData()!=1)
			{
				return;
			}
			//Item(String itemName, String metal, double metalWeight, double otherWeight, String weightUnit,
				//	double labourChareges, double otherCharges, double purity, int hsn)
			Item item = new Item(txtItemName.getText(),
					txtMetal.getText(),
					Double.parseDouble(txtMetalWeight.getText()),
					Double.parseDouble(txtOtherWeight.getText()),
					txtUnit.getText(),
					Double.parseDouble(txtLabour.getText()),
					Double.parseDouble(txtOther.getText()),
					Double.parseDouble(txtPurity.getText()),
					Integer.parseInt(txtHsn.getText()));
			item.setId(id);
			System.out.println(item);
			if (item.getId() == 0) {
				Item i =itemService.saveItem(item) ;
				if ( i!= null) {
					System.out.println("From Ui\n"+i);
					new AlertNotification().showSuccessMessage("Item Save Success");
					itemList.add(i);
					btnClear.fire();
				} else
					new AlertNotification().showErrorMessage("Error In Saving Item");
			} else {
				if (itemService.updateItem(item) != null) {
					new AlertNotification().showSuccessMessage("Item Update Success");
					int f = -1;
					for (int i = 0; i < itemList.size(); i++) {
						if (itemList.get(i).getId() == item.getId()) {
							f = i;
							break;
						}
					}
					if (f != -1) {
						itemList.remove(f);
						itemList.add(f, item);
					}
					btnClear.fire();
				}
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Error in Saving Record!!!\n" + e.getMessage()).showAndWait();
		}
	}

    private int validateData()
    {
    	try {
			if(txtItemName.getText().equals(""))
			{
				new AlertNotification().showConfirmMessage("Enter Item Name!!!");
				txtItemName.requestFocus();
				return 0;
			}		
			int flag=0;
			for(Item i:itemList)
			{
				if(i.getItemname().equals(txtItemName.getText()))
				{
					flag=1;
					break;
				}
			}
			if(flag!=0 && id==0)
			{
				new AlertNotification().showErrorMessage("Item Name Already Exist!!!");
				return 0;
			}
			if(txtMetal.getText().equals(""))
			{
				new AlertNotification().showConfirmMessage("Enter Metal !!!");
				txtMetal.requestFocus();
				return 0;
			}
			if(txtMetalWeight.getText().equals(""))
			{
				new AlertNotification().showConfirmMessage("Enter Item Metal Weight !!!");
				txtMetalWeight.requestFocus();
				return 0;
			}
			if(!isNumber(txtMetalWeight.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Item Weight in Digit !!!");
				txtMetalWeight.requestFocus();
				return 0;
			}
			if(txtOtherWeight.getText().equals("") || !isNumber(txtOtherWeight.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Other Weight in Digit");
				txtOtherWeight.requestFocus();
				return 0;
			}
			if(txtNetWeight.getText().equals(""))
			{
				new AlertNotification().showConfirmMessage("Enter Weight Again!!!");
				txtMetalWeight.requestFocus();
				return 0;
			}
			
			if(txtUnit.getText().equals(""))
			{
				new AlertNotification().showConfirmMessage("Enter Item Weight Unit !!!");
				txtUnit.requestFocus();
				return 0;
			}
			if(txtPurity.getText().equals("")|| !isNumber(txtPurity.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Item Purity in Digit  !!!");
				txtPurity.requestFocus();
				return 0;
			}
			if(txtHsn.getText().equals("") || !isNumber(txtHsn.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Item HSN Code in Digit !!!");
				txtHsn.requestFocus();
				return 0;
			}
			if(txtLabour.getText().equals("") || !isNumber(txtLabour.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Labour Charges in Digit !!!");
				txtLabour.requestFocus();
				return 0;
			}
			if(txtOther.getText().equals("") || !isNumber(txtOther.getText()))
			{
				new AlertNotification().showConfirmMessage("Enter Other Charges in Digit !!!");
				txtOther.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			new AlertNotification().showConfirmMessage("Error in Validating Fields ");
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
}
