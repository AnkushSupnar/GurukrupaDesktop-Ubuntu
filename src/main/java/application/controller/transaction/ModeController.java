package application.controller.transaction;

import application.controller.util.CommonData;
import application.guiUtil.AlertNotification;
import com.jfoenix.controls.JFXComboBox;
import hibernate.entities.Customer;
import hibernate.service.service.BankService;
import hibernate.service.service.CustomerService;
import hibernate.service.serviceimpl.BankServiceImpl;
import hibernate.service.serviceimpl.CustomerServiceImpl;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModeController implements Initializable {

    @FXML private AnchorPane mainPanel;
    @FXML private DatePicker date;
    @FXML private MFXTextField txtCustomerName;
    @FXML private TextArea txtCustomerInfo;
    @FXML private MFXButton btnSearch;
    @FXML private MFXTextField txtWeight;
    @FXML private MFXTextField txtLess;
    @FXML private MFXTextField txtFinalWeight;
    @FXML private MFXTextField txtRate;
    @FXML private MFXTextField txtAmount;
    @FXML private MFXButton btnAdd;
    @FXML private MFXButton btnRemove;
    @FXML private MFXButton btnClear;
    @FXML private MFXButton btnNew;
    @FXML private TableView<?> table;
    @FXML private TableColumn<?, ?> colSrno;
    @FXML private TableColumn<?, ?> colMetalName;
    @FXML private TableColumn<?, ?> colWeoght;
    @FXML private TableColumn<?, ?> colLess;
    @FXML private TableColumn<?, ?> colTotalWeight;
    @FXML private TableColumn<?, ?> colRate;
    @FXML private TableColumn<?, ?> colAmount;
    @FXML private MFXButton btnSave;
    @FXML private MFXButton btnUpdate;
    @FXML private MFXButton btnClear2;
    @FXML private MFXButton btnPrint;
    @FXML private MFXButton btnHome;
    @FXML private MFXTextField txtTotal;
    @FXML private MFXComboBox<String> cmbBankName;
    @FXML private MFXComboBox<String> cmbMetalName;
    @FXML private MFXTextField txtRefference;

    BankService bankService;
    CustomerService customerService;

    private SuggestionProvider<String> customerNameProvider;
    private ObservableList<String>customerNameList = FXCollections.observableArrayList();
    private AlertNotification notify;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankService = new BankServiceImpl();
        customerService = new CustomerServiceImpl();
        customerNameList.addAll(customerService.getAllCustomerNames());
        notify = new AlertNotification();
        date.setValue(LocalDate.now());
        customerNameProvider = SuggestionProvider.create(customerNameList);
        TextFields.bindAutoCompletion(txtCustomerName,customerNameProvider);
        cmbBankName.getItems().addAll(bankService.getAllBankNames());
        cmbMetalName.getItems().addAll("Gold","Silver","Platinum");

        txtWeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    txtWeight.setText(oldValue);
                }
            }
        });
        txtRate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    txtRate.setText(oldValue);
                }
            }
        });
        txtLess.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    txtLess.setText(oldValue);
                }
            }
        });
        txtWeight.setOnAction(e->{
            if(!txtWeight.getText().equals(""))
            {
                txtLess.requestFocus();

                if(!txtLess.getText().equals(""))
                    txtFinalWeight.setText(""+(
                            Double.parseDouble(txtWeight.getText())-
                            Double.parseDouble(txtLess.getText())));

            }
        });
        txtLess.setOnAction(e->{
            if(!txtLess.getText().equals("")) {
                if (!txtWeight.getText().equals("")) {
                    txtFinalWeight.setText(""+(
                            Double.parseDouble(txtWeight.getText())-
                                    Double.parseDouble(txtLess.getText())));
                }
                txtRate.requestFocus();
            }
        });
        txtRate.setOnAction(e->{
            if(!txtRate.getText().equals("")) {
                if (!txtWeight.getText().equals("") && txtLess.getText().equals("")) {
                    txtFinalWeight.setText("" + (
                            Double.parseDouble(txtWeight.getText()) -
                                    Double.parseDouble(txtLess.getText())));
                }
                txtAmount.setText(""+(
                        (Double.parseDouble(txtRate.getText())/10)*
                                Double.parseDouble(txtFinalWeight.getText())
                        ));
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
        if(customer==null)
            notify.showErrorMessage("No Customer Found!!!\n Please Add new one!");
        else
        txtCustomerInfo.setText(customerService.getCustomerByName(txtCustomerName.getText()).toString2());
    }
    @FXML
    void btnSaveAction(ActionEvent event) {
        try {
            AlertNotification notify = new AlertNotification();
            notify.showSuccessMessage("Done");
        }catch(Exception e)
        {
                notify.showErrorMessage(e.getMessage());
        }
    }

    @FXML
    void btnAddAction(ActionEvent event) {

    }
    @FXML
    void btnClear2Action(ActionEvent event) {

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

    }

    @FXML
    void btnNewAction(ActionEvent event) {

    }

    @FXML
    void btnPrintAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveAction(ActionEvent event) {

    }




    @FXML
    void btnUpdate(ActionEvent event) {

    }


}
