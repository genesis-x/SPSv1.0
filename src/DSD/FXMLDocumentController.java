/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSD;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author alienware
 */
public class FXMLDocumentController extends AnchorPane implements Initializable  {

    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblPass;
    @FXML
    private Label lblUsername;
    @FXML
    private ProgressIndicator determinateProgressBar;
    @FXML
    private AnchorPane framlogin;

    private GXDSD application;

    public void setApp(GXDSD application) {
        this.application = application;
    }

    private Timeline timeline;
    private double progress = 0.1;
    private double oPacity = 1;

    private void hide_all() {

        Timeline tick0 = new Timeline();
        tick0.setCycleCount(Timeline.INDEFINITE);
        tick0.getKeyFrames().add(
                new KeyFrame(new Duration(20), (ActionEvent t) -> {
                    oPacity -= 0.01;
                    if (oPacity < 0.01) {//20 divided by 0.01 equals 3000 so you take the duration and divide it be the opacity to get your transition time in milliseconds
                        txtusername.setVisible(false);
                        txtpassword.setVisible(false);
                        btnLogin.setVisible(false);
                        lblPass.setVisible(false);
                        lblUsername.setVisible(false);
                        tick0.stop();
                        run_progress();
                    } else {
                        txtusername.setOpacity(oPacity);
                        txtpassword.setOpacity(oPacity);
                        btnLogin.setOpacity(oPacity);
                        lblPass.setOpacity(oPacity);
                        lblUsername.setOpacity(oPacity);
                    }
                }));
        tick0.play();
    }

    private void run_progress() {
        progress = 0;

        EventHandler<ActionEvent> onTimeChanged = event -> {
            if (progress > 1.10) {
                application.CallHomePage();
                timeline.stop();
            } else {
                progress += 0.01;
            }
        };

        determinateProgressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), onTimeChanged));
        determinateProgressBar.setVisible(true);
        timeline.play();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String Username = txtusername.getText();
        String Password = txtpassword.getText();
        if ((!Username.isEmpty()) && (!Password.isEmpty())) {
            hide_all();
        } else {
            GaussianBlur GB = new GaussianBlur(10);
            framlogin.setEffect(GB);
            Alert artWrong = new Alert(Alert.AlertType.ERROR);
            artWrong.setTitle("Username or Password is WRONG");
            artWrong.setHeaderText("You not have access !");
            artWrong.setContentText("Please Make sure for your Username and Password");
            Optional<ButtonType> result = artWrong.showAndWait();
            if (result.get() == ButtonType.OK) {
                GB.setRadius(0);
                framlogin.setEffect(GB);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        determinateProgressBar.setStyle("-fx-background-color: #1d1d1d;");
        determinateProgressBar.setVisible(false);
        btnLogin.setDefaultButton(true);
    }

}
