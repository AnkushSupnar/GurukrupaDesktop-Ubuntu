package application.controller.transaction;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import application.guiUtil.AlertNotification;

import entity.reportEntities.OldBill;
import entity.reportEntities.TransactionReport;
import hibernate.entities.Bill;
import hibernate.entities.Customer;
import hibernate.entities.Item;
import hibernate.entities.Transaction;
import hibernate.service.service.BankService;
import hibernate.service.service.BillService;
import hibernate.service.service.CustomerService;
import hibernate.service.service.ItemService;
import hibernate.service.serviceimpl.*;
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

import io.github.palexdev.materialfx.controls.MFXTextField;

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
	 @FXML private Button btnSearch;
	 @FXML private Button btnClearBill;

	    
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
	private ObservableList<Bill>oldBillList = FXCollections.observableArrayList();
	 int trid;
	 long billno;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trid = 1;
		billno=0;
		customerService = new CustomerServiceImpl();
		itemService = new ItemServiceImpl();
		bankService = new BankServiceImpl();
		billService = new BillServiceImpl();
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

		oldBillList.addAll(billService.getAllBills());
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
			searchCustomer();
		}

	private void searchCustomer() {
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
				Item item = itemService.getByName(txtItemName.getText());
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
    void btnRemoveAction(ActionEvent event) {
        try {
            if(table.getSelectionModel().getSelectedItem()!=null)
            {
                TransactionReport tr = table.getSelectionModel().getSelectedItem();
                if(tr!=null)
                {
                    trList.remove(tr);
					manageSrNo();
                }
            }
        }catch(Exception e)
        {
            message.showErrorMessage("Unable to Remove Item");
        }
    }

	    @FXML
	    void btnClearAction(ActionEvent event) {
			clear();
		}
	@FXML
	void btnUpdateAction(ActionEvent event) {
	try {
		if(table.getSelectionModel().getSelectedItem()!=null)
		{
			txtItemName.setText(table.getSelectionModel().getSelectedItem().getName());
			btnSearch.fire();
		}
	}catch(Exception e)
	{
		message.showErrorMessage("Error inUpdating Item");
	}

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
		bill.setLogin(new LoginServiceImpl().getLoginById(1));
		bill.setPaidamount(Double.parseDouble(txtRecivedAmount.getText()));


		bill.setTransaction(transactionReportToTransactionList(bill));

		if(billno==0)
		{
			int savedBill =billService.saveBill(bill);
			if(savedBill==0)
			{
				message.showSuccessMessage("Bill no "+bill.getBillno()+" Saved Success");
				oldBillList.add(bill);
				billList.add(
						new OldBill(
								bill.getBillno(),
								bill.getDate(),
								bill.getCustomer().getFname()+" "+bill.getCustomer().getMname()+" "+bill.getCustomer().getLname(),
								bill.getAmount(),
								bill.getPaidamount(),
								bill.getAmount()-bill.getPaidamount()
								));
            } else {
                message.showErrorMessage("Error in Saving Bill");
            }
        } else {
            int savedBill =billService.updateBill(bill);
            if (savedBill ==2) {
                message.showSuccessMessage("Bill" + bill.getBillno() + " Update Success");
                OldBill updated = new OldBill(
						bill.getBillno(),
						bill.getDate(),
                        bill.getCustomer().getFname()+" "+bill.getCustomer().getMname()+" "+bill.getCustomer().getLname(),
                        bill.getAmount(),
						bill.getPaidamount(),
                        bill.getAmount()-bill.getPaidamount()
                );
                int index = billList.indexOf(
                		billList.stream().filter(
                				b->b.getBillno()==updated.getBillno()
								).findAny().orElse(null)
				);
                billList.remove(index);
                billList.add(index,updated);
                index = oldBillList.indexOf(
                	oldBillList.stream().filter(
                			b->b.getBillno()==bill.getBillno()
					).findAny().orElse(null)
				);
                oldBillList.remove(index);
                oldBillList.add(index,bill);

            } else {
                message.showErrorMessage("Error in Updating Bill");
            }
        }

    }
	@FXML
	void btnClearBillAction(ActionEvent event) {
		clearBill();

	}

	@FXML
	void btnUpdateBill(ActionEvent event) {
		try {
			if(tblOld.getSelectionModel().getSelectedItem()!=null)
			{
				OldBill obill = tblOld.getSelectionModel().getSelectedItem();
				if(obill!=null)
				{
					Bill bill = oldBillToBill(obill);
					txtCustomerName.setText(
							bill.getCustomer().getFname()+" "+
							bill.getCustomer().getMname()+" "+
							bill.getCustomer().getLname()
					);
					searchCustomer();
					trList.clear();
					int sr=0;
					bill.getTransaction().forEach(tr->{
						trList.add(new TransactionReport(
						sr,
						tr.getItem().getItemname(),
						tr.getItem().getMetal(),
						tr.getItem().getPurity(),
						tr.getItem().getMetalweight()+tr.getItem().getOtherweight(),
						tr.getQuantity(),
						tr.getItem().getLabouruchareges()+tr.getItem().getOthercharges(),
						tr.getRate(),
								((tr.getRate()/10)*
										tr.getItem().getMetalweight())*
										tr.getQuantity()+
								tr.getItem().getLabouruchareges()+tr.getItem().getOthercharges()

						));
					});
					manageSrNo();
					txtDiscount.setText(""+bill.getDiscount());
					calculateGrandTotal();
					txtRecivedAmount.setText(""+bill.getPaidamount());
					lblRemainingAmount.setText(""+(bill.getAmount()-bill.getPaidamount()));
					billno = bill.getBillno();
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void clearBill() {
		billno=0;
		date.setValue(LocalDate.now());
		txtCustomerName.setText("");
		txtCustomerInfo.setText("");
		txtItemName.clear();
		txtDiscount.setText(""+0);
		btnClear.fire();
		trList.clear();
		calculateGrandTotal();
		txtRecivedAmount.clear();
		lblRemainingAmount.setText(""+0.0);
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
			if(!isNumber(lblRemainingAmount.getText()))
			{
				message.showErrorMessage("Enter Received Amount and Press Enter");
				txtRecivedAmount.requestFocus();
				return 0;
			}
			if(Double.parseDouble(lblRemainingAmount.getText())<0)
			{
				message.showErrorMessage("Recived Amount must be less than or equal to Grand Total");
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
							itemService.getByName(tr.getName()),
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
		txtQty.setText(""+1);
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
	    private void manageSrNo()
		{
			int sr=0;
			for(int i=0;i<trList.size();i++)
			{
				trList.get(i).setId(++sr);
			}
			calculateGrandTotal();
		}

	private void getOldBillList() {
		oldBillList.forEach(bill->{
			billList.add(billToOldBill(bill));
		});
	}
	private OldBill billToOldBill(Bill bill)
	{
		return new OldBill(
				bill.getBillno(),
				bill.getDate(),
				bill.getCustomer().getFname()+" "+
				bill.getCustomer().getMname()+" "+
				bill.getCustomer().getLname(),
				bill.getAmount(),
				bill.getPaidamount(),
				bill.getAmount()-bill.getPaidamount());
	}
	private Bill oldBillToBill(OldBill oBill)
	{
		return oldBillList.stream()
				.filter(bill->bill.getBillno()==oBill.getBillno())
				.findAny()
				.orElse(null);
	}
}
