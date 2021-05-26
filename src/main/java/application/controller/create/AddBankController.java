package application.controller.create;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.guiUtil.AlertNotification;
import entity.entities.Bank;
import javafx.fxml.FXML;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.BankService;
import javafx.fxml.Initializable;

public class AddBankController implements Initializable {
	 	@FXML private AnchorPane mainPane;
		@FXML private TextField txtBankName;
	    @FXML private TextField txtAccountNo;
	    @FXML private TextField txtIfsc;
	    @FXML private TextField txtAccountType;
	    @FXML private TextField txtPerson;
	    @FXML private TextField txtBalance;

	    @FXML private TableView<Bank> table;
	    @FXML private TableColumn<Bank,Long>   colSrNo;
	    @FXML private TableColumn<Bank,String> colName;
	    @FXML private TableColumn<Bank,String> colAccountNo;
	    @FXML private TableColumn<Bank,String> colIfsc;
	    @FXML private TableColumn<Bank,String> colType;
	    @FXML private TableColumn<Bank,Double> colBalance;
	    @FXML private TableColumn<Bank,String> colPerson;

	    ValidationSupport validationSuport = new ValidationSupport();
	    private AlertNotification message;
	    
	    private SuggestionProvider<String> typeProvider;
	    private ArrayList<String> type;
	    private ObservableList<Bank>bankList = FXCollections.observableArrayList();
	    private BankService bankService;
	    private int id;
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			bankService = new BankService();
			id=0;
			message = new AlertNotification();
			bankList.addAll(bankService.getAllBanks());
			type = new ArrayList<>();
			type.add("Saving");
			type.add("Current");
			type.add("Joint");
			typeProvider = SuggestionProvider.create(type);
			new AutoCompletionTextFieldBinding<>(txtAccountType, typeProvider);
			validationSuport.registerValidator(txtBankName, Validator.createEmptyValidator("Add Bank Name"));   
			validationSuport.registerValidator( txtAccountNo, Validator.createEmptyValidator("Add Bank Acconu Number")); 
			validationSuport.registerValidator( txtIfsc, Validator.createEmptyValidator("Add Bank IFSC"));     
			validationSuport.registerValidator( txtAccountType, Validator.createEmptyValidator("Add Bank Account Type"));
			validationSuport.registerValidator( txtPerson, Validator.createEmptyValidator("Add Person Name"));   
			validationSuport.registerValidator( txtBalance, Validator.createEmptyValidator("Add Bank Balance"));
			
			colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
			colName.setCellValueFactory(new PropertyValueFactory<>("bankname"));     
			colAccountNo.setCellValueFactory(new PropertyValueFactory<>("accountno"));
			colIfsc.setCellValueFactory(new PropertyValueFactory<>("ifsc"));
			colType.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
			colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
			colPerson.setCellValueFactory(new PropertyValueFactory<>("person"));
			table.setItems(bankList);
		}

		@FXML
		void btnClarAction(ActionEvent event) {
			clear();
}
		@FXML
		void btnEditAction(ActionEvent event) {
			try {
				if(table.getSelectionModel().getSelectedItem()==null)
				{
					return;
				}
				Bank bank = table.getSelectionModel().getSelectedItem();
				if(bank==null)
				{
					return;
				}
				txtBankName.setText(bank.getBankname());
				txtAccountNo.setText(bank.getAccountno());
				txtIfsc.setText(bank.getIfsc());
				txtAccountType.setText(bank.getAccounttype());
	            txtPerson.setText(bank.getPerson());
	            txtBalance.setText(""+bank.getBalance());
	            id=bank.getId();
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
				if (validateData() != 1) {
					return;
				}
				Bank bank = new Bank(txtBankName.getText(), txtAccountNo.getText(), txtIfsc.getText(),
						txtAccountType.getText(), txtPerson.getText(), Double.parseDouble(txtBalance.getText()));
				bank.setId(id);
				System.out.println(bank);
				if (id == 0) {
					Bank b = bankService.saveBank(bank);
					if (b != null) {
						message.showSuccessMessage("Bank Save Success");
						addInBankList(b);
						clear();
					} else 
						message.showErrorMessage("Error in Saving Bank");
				}
				else {
					Bank b= bankService.updateBank(bank);
					if (b != null) {
						message.showSuccessMessage("Bank Update Success");
						addInBankList(b);
						clear();
					} else 
						message.showErrorMessage("Error in Updating Bank");
				}
				
			} catch (Exception e) {
				message.showErrorMessage("Error in Saving Bank");

			}
		}
		private void clear()
		{
			txtBankName.setText("");
			txtAccountNo.setText("");
			txtIfsc.setText("");
			txtAccountType.setText("");
            txtPerson.setText("");
            txtBalance.setText("");
            id=0;
            
		}
		private void addInBankList(Bank bank)
		{
			int flag=-1;
			for(int i=0;i<bankList.size();i++)
			{
				if(bankList.get(i).getBankname().equals(bank.getBankname())&&bankList.get(i).getAccountno().equals(bank.getAccountno()))
				{
					flag=i;
					break;
				}
			}
			if(flag==-1)
			{
				bankList.add(bank);
			}
			else
			{
				bankList.remove(flag);
				bankList.add(bank);
			}
		}
		private int validateData()
		{
			if(txtBankName.getText().equals(""))
			{
				message.showErrorMessage("Enter Bank Name");
				txtBankName.requestFocus();
				return 0;
			}
			
			if(txtAccountNo.getText().equals(""))
			{
				message.showErrorMessage("Enter Bank Account Number");
				txtAccountNo.requestFocus();
				return 0;
			}
			int flag = 1;
			for (Bank b : bankList) {
				if (b.getBankname().equalsIgnoreCase(txtBankName.getText())
						&& b.getAccountno().equalsIgnoreCase(txtAccountNo.getText()) && id == 0) {
					flag = 0;
					break;
				}
			}
			if(flag==0)
			{
				message.showErrorMessage("Bank Already Available");
				txtBankName.requestFocus();
				return 0;
			}
			if(!isNumber(txtAccountNo.getText()))
			{
				message.showErrorMessage("Enter Account Number in Digit");
				txtAccountNo.clear();
				txtAccountNo.requestFocus();
				return 0;
			}
			if(txtIfsc.getText().equals(""))
			{
				message.showErrorMessage("Enter Bank IFSC");
				txtIfsc.requestFocus();
				return 0;
			}
			if(txtAccountType.getText().equals(""))
			{
				message.showErrorMessage("Enter Bank Account Type");
				txtAccountType.requestFocus();
				return 0;
			}
			if(txtPerson.getText().equals(""))
			{
				message.showErrorMessage("Enter Person Name");
				txtPerson.requestFocus();
				return 0;
			}
			if(txtBalance.getText().equals(""))
			{
				message.showErrorMessage("Enter Bank Current Balance");
				txtBalance.requestFocus();
				return 0;
			}
			if(!isNumber(txtBalance.getText()))
			{
				message.showErrorMessage("Enter Current Balance In Digit");
				txtBalance.requestFocus();
				return 0;
			}
			return 1;
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
