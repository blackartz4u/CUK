package project.cuk;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        Scene scene = new Scene(root, 720, 480);
        stage.setTitle("MiniWebBrowser");

        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("/style.css").toExternalForm());
        stage.show();
    }
    @FXML
    private TextField addressBar;
    @FXML
    private WebView webView;
    @FXML
    private WebEngine webEngine;
    @FXML
    private Button button;
    @FXML
    public void initialize() {

        webEngine = webView.getEngine();
        webEngine.load("https://www.google.com");
    }
    @FXML
    private void loadWebpage() {
        String url = addressBar.getText();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        webEngine.load(url + "/index.html");
    }
    static void main(String[] args) {
        launch();
    }
}
