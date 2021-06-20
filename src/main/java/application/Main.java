package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {
    String pageName;
    @Override
    public void start(Stage primaryStage) {
        try {
            pageName="home/login";
            //pageName="home/DashboardFrame";
            //pageName="create/additem";
            //pageName="create/createcounter";
            //pageName="create/adduser";
            //pageName="create/AddCustomer";
            //pageName="create/AddBank";
           // pageName="transaction/billing";
            pageName="transaction/billingframe";
            pageName="transaction/modeframe";
           // pageName="home/homepage2";
            Parent root;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/"+pageName+".fxml")));
            Scene scene = new Scene(root);
          //  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gurukrupa Jewellers Management System");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}