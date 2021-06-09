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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage2Controller implements Initializable {
    @FXML private BorderPane root;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer menuDrawer;

    private Pane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menu);
            transition.setRate(-1);
            menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                if (menuDrawer.isOpened()) {
                    menuDrawer.close();


                } else {
                    menuDrawer.open();
                    root.setCenter(null);
                   if(root.getLeft().getClass().equals(AnchorPane.class))
                   {
                       root.setLeft(null);
                       root.setCenter(null);
                       root.setLeft(menuDrawer);
                   }


                }
            });

            AnchorPane box = FXMLLoader.load(getClass().getResource("/view/home/homemenu.fxml"));
            menuAction(box);
            menuDrawer.setSidePane(box);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Pane getPage(String name)
    {
        try {
            System.out.println(name);
            // pane = FXMLLoader.load(getClass().getResource("/view/" + name + ".fxml"));
            pane = FXMLLoader.load(getClass().getResource("/view/"+name+".fxml"));
            //root.getChildren().add(2,pane);
            pane.setLayoutX(100);
            pane.setLayoutY(100);
            return pane;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void menuAction(AnchorPane box)
    {
        for(Node node:box.getChildren())
        {
            if(node.getAccessibleText()!=null)
            {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
                    switch (node.getAccessibleText())
                    {
                        case "btnTransaction":
                            menuDrawer.close();
                            root.setCenter(getPage("transaction/transactionmenu"));
                            break;
                        case "btnCreate":
                            root.setCenter(getPage("create/createmenu"));
                            menuDrawer.close();
                            break;
                        case "btnReport":
                            menuDrawer.close();
                            break;
                        case "btnSettings":
                            menuDrawer.close();
                            break;
                        default:
                            menuDrawer.close();
                            break;

                    }
                });
            }
        }
    }
}
