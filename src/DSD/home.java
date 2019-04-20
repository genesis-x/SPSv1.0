/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSD;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author RedR00t <Telegram:https://t.me/RedRo0t>
 */
public class home extends AnchorPane implements Initializable {

    private GXDSD application;

    @FXML
    private Button btnLogout;
    
    public void setApp(GXDSD application) {
        this.application = application;
    }

    @FXML
    public void CloseHome() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.YES, ButtonType.NO);

        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.YES.equals(result)) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogout.setCancelButton(true);
    }
}
