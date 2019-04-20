/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSD;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro8.JMetro;

/**
 *
 * @author alienware
 */
public class GXDSD extends Application {

    private final String LOGING = "FXMLDocument.fxml";
    private final String HOME = "home.fxml";
    public Stage MainStage;

    @Override
    public void start(Stage stage) throws Exception {
        MainStage = stage;
        MainStage.initStyle(StageStyle.TRANSPARENT);
        MainStage.setOpacity(1);
        LoginForm();
        stage.show();
    }

    public void LoginForm() {
        try {
            FXMLDocumentController login = (FXMLDocumentController) replaceSceneContent(LOGING, 590, 406);
            MainStage.setAlwaysOnTop(true);
            MainStage.centerOnScreen();
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GXDSD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CallHomePage() {
        try {
            home HomePage = (home) replaceSceneContent(HOME, 1295, 916);
            MainStage.centerOnScreen();
            MainStage.setAlwaysOnTop(false);
            MainStage.setResizable(true);
            HomePage.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GXDSD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml, int SizeW, int SizeH) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GXDSD.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GXDSD.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        // keep gridPane at original size
        page.setMinSize(SizeW, SizeH);
        page.setMaxSize(SizeW, SizeH);

        StackPane root = new StackPane(page);
// root.setAlignment(Pos.TOP_LEFT);

// use gridPane size to determine the factor to scale by
        NumberBinding maxScale = Bindings.min(root.widthProperty().divide(SizeW),
                root.heightProperty().divide(SizeH));
        root.scaleXProperty().bind(maxScale);
        root.scaleYProperty().bind(maxScale);
        Scene scene = new Scene(root, Color.TRANSPARENT);
        new JMetro(JMetro.Style.DARK).applyTheme(scene);
        MainStage.setScene(scene);
        MainStage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
