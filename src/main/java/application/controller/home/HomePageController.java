package application.controller.home;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.core.appender.ConsoleAppender;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    private Pane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

        AnchorPane box = FXMLLoader.load(getClass().getResource("/view/home/homemenu.fxml"));
        for(Node node:box.getChildren())
        {
           if(node.getAccessibleText()!=null)
           {
               node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
                   switch (node.getAccessibleText())
                   {
                       case "btnTransaction":
                           drawer.close();
                           openTransaction();
                           break;
                       case "btnCreate":
                           drawer.close();
                           openCreate();
                   }
               });
           }
        }
        drawer.setSidePane(box);
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            if(drawer.isOpened())
                drawer.close();
            else {
                drawer.open();


//                if(root.getChildren().indexOf(pane)>0) {
//                    root.getChildren().remove(root.getChildren().indexOf(pane));
//                   // pane = (Pane) root.getChildren().get(2);
//                }
//                System.out.println("size==="+root.getChildren().size());
                if (root.getChildren().size() >2) {
                    root.getChildren().remove(2);
                }
            }
        });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openCreate() {
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/create/createmenu.fxml"));
            root.getChildren().add(2,pane);
            pane.setLayoutX(100);
            pane.setLayoutY(100);
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void openTransaction() {
        try {
             pane = FXMLLoader.load(getClass().getResource("/view/transaction/transactionmenu.fxml"));
            root.getChildren().add(2,pane);
            pane.setLayoutX(100);
            pane.setLayoutY(100);
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
