package application.controller.transaction;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import application.guiUtil.AlertNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.entities.Bill;
import entity.entities.Customer;
import entity.entities.Item;
import entity.entities.Transaction;
import entity.reportEntities.OldBill;
import entity.reportEntities.TransactionReport;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.*;

public class BillingController implements Initializable {

	 @FXML private DatePicker date;
	 @FXML private TextField txtCustomerName;
	 @FXML private TextArea txtCustomerInfo;
	 	@FXML private TextField txtItemName;
	 	@FXML private TextField txtMetal;
	    @FXML private TextField txtPurity;
	    @FXML private TextField txtMetalWeight;
	    @FXML private TextField txtOtherWeight;
	    @FXML private TextField txtTotalWeight;
	    @FXML private TextField txtLabourCharges;
	    @FXML private TextField txtOtherCharges;
	    @FXML private TextField txtRate;
 	    @FXML private TextField txtQty;
	    @FXML private TextField txtAmount;
	    @FXML private TextField txtTotalCharges;
	    @FXML private Button btnAdd;
	    
	    @FXML private TableView<TransactionReport> table;
	    @FXML private TableColumn<TransactionReport,Integer>colSrno;
	    @FXML private TableColumn<TransactionReport,String> colName;
	    @FXML private TableColumn<TransactionReport,String> colMetal;
	    @FXML private TableColumn<TransactionReport,Double> colPurity;
	    @FXML private TableColumn<TransactionReport,Double> colWeight;
	    @FXML private TableColumn<TransactionReport,Double> colQty;
	    @FXML private TableColumn<TransactionReport,Double> colLabour;
	    @FXML private TableColumn<TransactionReport,Double> colRate;
	    @FXML private TableColumn<TransactionReport,Double> colAmount;

	@FXML private TextField txtSGST;
	@FXML private TextField txtDiscount;
	@FXML private TextField txtNetTotal;
 	@FXML private TextField txtGrandTotal;
	@FXML private ComboBox<String> cmbBank;
	@FXML private TextField txtRecivedAmount;
	@FXML private TextField txtCGST;
	@FXML private Button btnClear;
	@FXML private Button btnSave;
	@FXML private Label lblRemainingAmount;

	@FXML private TableView<OldBill> tblOld;
 	@FXML private TableColumn<OldBill,Long> colBillno;
 	@FXML private TableColumn<OldBill,LocalDate> colDate;
	@FXML private TableColumn<OldBill,String> colCustomerName;
	@FXML private TableColumn<OldBill,Double> colBillAmount;
	@FXML private TableColumn<OldBill,Double> colRemaining;
	 private CustomerService customerService;
	 private BankService bankService;
	 private ItemService itemService;
	 private BillService billService;
	 private AlertNotification message;
	 private List<String>customerNames = new ArrayList<>();
	 private SuggestionProvider<String>customerNameProvider;
	 private ObservableList<TransactionReport>trList = FXCollections.observableArrayList();
	 private ObservableList<OldBill>billList = FXCollections.observableArrayList();

	 int trid;
	 long billno;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trid = 1;
		billno=0;
		customerService = new CustomerService();
		itemService = new ItemService();
		bankService = new BankService();
		billService = new BillService();
		message = new AlertNotification();
		date.setValue(LocalDate.now());
		customerNames.addAll(customerService.getAllCustomerNames());
		customerNameProvider = SuggestionProvider.create(customerNames);
		new AutoCompletionTextFieldBinding<>(txtCustomerName, customerNameProvider);
		new AutoCompletionTextFieldBinding<>(txtItemName, SuggestionProvider.create(itemService.getAllItemNames()));
		cmbBank.getItems().addAll(bankService.getAllBankNames());

		colSrno.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colMetal.setCellValueFactory(new PropertyValueFactory<>("metal"));
		colPurity.setCellValueFactory(new PropertyValueFactory<>("purity"));
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		colLabour.setCellValueFactory(new PropertyValueFactory<>("labour"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		table.setItems(trList);

		colBillno.setCellValueFactory(new PropertyValueFactory<>("billno"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
		colBillAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		colRemaining.setCellValueFactory(new PropertyValueFactory<>("remaining"));
		getOldBillList();
		tblOld.setItems(billList);

		txtCustomerName.setOnKeyPressed(event -> txtCustomerInfo.setText(""));
		txtQty.setText("" + 1);
		txtRate.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					txtRate.setText(oldValue);
				}
			}
		});
		txtDiscount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					txtDiscount.setText(oldValue);
				}
			}
		});

		txtRecivedAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					txtRecivedAmount.setText(oldValue);
				}
			}
		});
		txtQty.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					txtQty.setText(oldValue);
				}
			}
		});
		txtRate.setOnAction(event -> {
			if (!txtRate.getText().equals("") || isNumber(txtRate.getText())) {
				if (!txtQty.getText().equals("") || isNumber(txtQty.getText())) {
					calculateAmount();
					txtQty.requestFocus();
				}
			}
		});
		txtQty.setOnAction(event -> {
			if (!txtRate.getText().equals("") || isNumber(txtRate.getText())) {
				if (!txtQty.getText().equals("") || isNumber(txtQty.getText())) {
					calculateAmount();
					btnAdd.requestFocus();
				}
			}
		});
		txtDiscount.setOnAction(event -> {
			if (isNumber(txtDiscount.getText()) && !txtNetTotal.getText().isEmpty() || isNumber(txtNetTotal.getText())) {
				txtGrandTotal.setText("" + (
						Double.parseDouble(txtNetTotal.getText()) +
								Double.parseDouble(txtSGST.getText()) +
								Double.parseDouble(txtCGST.getText()) -
								Double.parseDouble(txtDiscount.getText())
				));
			}
		});
		txtRecivedAmount.setOnAction(event->{
			if(txtRecivedAmount.getText().isEmpty())
			return;
			if(!isNumber(txtRecivedAmount.getText()))
				return;
			Double remaining =Double.parseDouble(txtGrandTotal.getText()) - Double.parseDouble(txtRecivedAmount.getText());
			lblRemainingAmount.setText("" + remaining);
			if(remaining<0)
			{
				message.showErrorMessage("Received Amount Must be Less than or Equal Grand Total!");
				txtRecivedAmount.requestFocus();
				return;
			}
			btnSave.requestFocus();
		});
	}

	private void getOldBillList() {
		for(Bill bill:billService.getAllBills())
		{
			billList.add(new OldBill(
					bill.getBillno(),
					bill.getDate(),
					bill.getCustomer().getFname()+" "+bill.getCustomer().getMname()+" "+bill.getCustomer().getMname(),
					bill.getAmount(),
					bill.getAmount()-bill.getPaidamount()
					));
		}

	}

	@FXML
	    void btnNewAction(ActionEvent event) throws IOException {
		 Stage stage = new Stage();
			Parent root = FXMLLoader.load(Main.class.getResource("/application/view/create/AddCustomer.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Add New Customer");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {
					customerNames.clear();
					customerNames.addAll(customerService.getAllCustomerNames());
					customerNameProvider.clearSuggestions();
					customerNameProvider.addPossibleSuggestions(customerNames);
				}
			});
	    }

	    @FXML
	    void btnSearchAction(ActionEvent event) {
	    	if(txtCustomerName.getText().equals(""))
	    	{
	    		txtCustomerName.requestFocus();
	    		return;
	    	}
	    	Customer customer = customerService.getCustomerByName(txtCustomerName.getText());
	    	txtCustomerInfo.setText(customer.toString());
	    }
	    @FXML
	    void btnSearchItemAction(ActionEvent event) {
	    	try {
				if(txtItemName.getText().equals(""))
				{
					message.showErrorMessage("Enter Item Name");
					txtItemName.requestFocus();
					return;
				}
				Item item = itemService.getItemByName(txtItemName.getText());
				if(item==null)
				{
					message.showErrorMessage("Item Not Found Plese Select Correct Name");
					txtItemName.requestFocus();
					return;
				}
				setItemProperties(item);
				txtRate.requestFocus();
				
			} catch (Exception e) {
				e.printStackTrace();
				message.showErrorMessage("Error in Search Item "+e.getMessage());
			}
	    }
	    @FXML
	    void btnClearItemAction(ActionEvent event) {

	    }
	    @FXML
	    void btnAddAction(ActionEvent event) {
	    	if(txtMetal.getText().equals(""))
	    	{
	    		message.showErrorMessage("Search Item ");
	    		txtItemName.requestFocus();
	    		return;
	    	}
	    	calculateAmount();
	    	if(!isNumber(txtAmount.getText()))
	    	{
	    		message.showErrorMessage("Enter Correct Rate and Quantity");
	    		txtRate.requestFocus();
	    		return;
	    	}
	    	TransactionReport tr = new TransactionReport();
	    	tr.setId(trid);
	    	tr.setAmount(Double.parseDouble(txtAmount.getText()));
	    	tr.setLabour(Double.parseDouble(txtTotalCharges.getText()));
	    	tr.setMetal(txtMetal.getText());
	    	tr.setName(txtItemName.getText());
	    	tr.setPurity(Double.parseDouble(txtPurity.getText()));
	    	tr.setRate(Double.parseDouble(txtRate.getText()));
	    	tr.setWeight(Double.parseDouble(txtTotalWeight.getText()));
	    	tr.setQty(Double.parseDouble(txtQty.getText()));
	    	if(trid==1)
	    	{
	    		trList.add(tr);
	    		calculateGrandTotal();
	    		btnClear.fire();
	    	}
	    	else
	    	{
	    		int flag=-1;
	    		for(int i=0;i<trList.size();i++)
	    		{
	    			if(tr.getName().equals(trList.get(i).getName()))
	    			{
	    				flag=i;
	    				break;
	    			}
	    		}
	    		if(flag==-1)
	    		{
	    			tr.setId(trList.size()+1);;
	    			trList.add(tr);
					calculateGrandTotal();
	    			btnClear.fire();
	    		}
	    		else
	    		{
	    			TransactionReport t = trList.get(flag);
	       			t.setQty(Double.parseDouble(txtQty.getText())+t.getQty());
	       			t.setAmount(t.getAmount()+Double.parseDouble(txtAmount.getText()));
	       			trList.remove(flag);
	       			trList.add(flag, t);

					calculateGrandTotal();
	    			btnClear.fire();
	    		
	    		}
	    	}

	    	
	    }
	    @FXML
	    void btnClearAction(ActionEvent event) {
			clear();
		}
	@FXML
	void btnSaveAction(ActionEvent event) {
		if(validateBill()!=1)
		{
			return;
		}
		Bill bill = new Bill();
		if(billno!=0)
		bill.setBillno(billno);
		bill.setAmount(Double.parseDouble(txtGrandTotal.getText()));
		bill.setBank(bankService.getBankByBankName(cmbBank.getSelectionModel().getSelectedItem()));
		bill.setCgst(Double.parseDouble(txtCGST.getText()));
		bill.setCustomer(customerService.getCustomerByName(txtCustomerName.getText()));
		bill.setDate(date.getValue());
		bill.setSgst(Double.parseDouble(txtSGST.getText()));
		bill.setDiscount(Double.parseDouble(txtDiscount.getText()));
		bill.setLogin(new LoginService().getLoginById(1));
		bill.setPaidamount(Double.parseDouble(txtRecivedAmount.getText()));


		bill.setTransaction(transactionReportToTransactionList(bill));
		try {
			System.out.println(JsonUtil.convertFromObjectToJson(bill));


		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(billno==0)
		{
			Bill savedBill =billService.saveBill(bill);
			if(savedBill!=null)
			{
				message.showSuccessMessage("Bill no "+savedBill.getBillno()+" Saved Success");
			}
			else
			{
				if(billService.updateBill(bill)!=null)
				{
					message.showConfirmMessage("Bill Update Success");
				}
			}
		}
		
	}

	private int validateBill() {
		try {
			if(txtCustomerName.getText().isEmpty() || txtCustomerInfo.getText().isEmpty())
			{
				message.showErrorMessage("Select Customer First");
				txtCustomerName.requestFocus();
				return 0;
			}
			if(txtNetTotal.getText().isEmpty()|| txtNetTotal.getText().equals("0"))
			{
				message.showErrorMessage("No Data to Save");
				txtItemName.requestFocus();
				return 0;
			}
			if(cmbBank.getSelectionModel().getSelectedItem()==null)
			{
				message.showErrorMessage("Select Bank Name");
				cmbBank.requestFocus();
				return 0;
			}
			if(txtRecivedAmount.getText().isEmpty() || !isNumber(txtRecivedAmount.getText()))
			{
				message.showErrorMessage("Enter Received Amount");
				txtRecivedAmount.requestFocus();
				return 0;
			}
			return 1;
		}catch (Exception e)
		{
			message.showErrorMessage("Error in Validating Bill"+e.getMessage());
			e.printStackTrace();
			return 0;
		}

	}
	private List<Transaction> transactionReportToTransactionList(Bill bill)
	{
		List<Transaction> transactionList = new ArrayList<>();
		//Transaction(Item item, double rate, double quantity, Bill bill) {
		for (TransactionReport tr : trList) {
			transactionList.add(
					new Transaction(
							itemService.getItemByName(tr.getName()),
							tr.getRate(),
							tr.getQty(),
							bill
					));
		}
		return transactionList;
	}

	private void clear() {
		txtItemName.setText("");
		txtMetal.setText("");
		txtPurity.setText("");
		txtMetalWeight.setText("");
		txtOtherWeight.setText("");
		txtTotalWeight.setText("");
		txtLabourCharges.setText("");
		txtOtherCharges.setText("");
		txtRate.setText("");
		txtQty.setText("");
		txtAmount.setText("");
		txtTotalCharges.setText("");
		trid = trList.size()+1;
	}

	void setItemProperties(Item item)
	    {
	    	txtMetal.setText(item.getMetal());
	    	txtPurity.setText(""+item.getPurity());
	    	txtMetalWeight.setText(""+item.getMetalweight());
	    	txtOtherWeight.setText(""+item.getOtherweight());
	    	txtTotalWeight.setText(""+(item.getMetalweight()+item.getOtherweight()));
	    	txtLabourCharges.setText(""+item.getLabouruchareges());
	    	txtOtherCharges.setText(""+item.getOthercharges());
	    	txtTotalCharges.setText(""+(item.getLabouruchareges()+item.getOthercharges()));
	    	
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
	    void calculateAmount()
	    {
	    	if(txtItemName.getText().isEmpty())
			{
				message.showErrorMessage("Select Item First");
				txtItemName.requestFocus();
				return;
			}
	    	txtAmount.setText(""+(
	    			((Double.parseDouble(txtRate.getText())/10)*Double.parseDouble(txtMetalWeight.getText()))*
	    			Double.parseDouble(txtQty.getText())));
	    	txtAmount.setText(""+(Double.parseDouble(txtAmount.getText())+Double.parseDouble(txtTotalCharges.getText())));
	    	
	    }
	    private void calculateGrandTotal()
		{
			System.out.println("I am Called");
			double netAmount=0;
			for(TransactionReport tr:trList)
			{
				netAmount += tr.getAmount();
			}
			txtNetTotal.setText(""+netAmount);
			txtSGST.setText(""+(netAmount*1.5/100));
			txtCGST.setText(""+(netAmount*1.5/100));
			if(!isNumber(txtDiscount.getText()))
			{
				txtDiscount.setText(""+0);
			}
			txtGrandTotal.setText(""+(
					netAmount+
							Double.parseDouble(txtCGST.getText())+
							Double.parseDouble(txtSGST.getText())
							-Double.parseDouble(txtDiscount.getText())
					));
		}
	    
}