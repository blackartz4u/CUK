package project.cuk;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setTitle("MiniWebBrowser");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
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
    private WebHistory webHistory;
    @FXML
    private ProgressBar progressBar;



    @FXML
    public void initialize() {

        webEngine = webView.getEngine();
        webEngine.locationProperty().addListener((observable, oldUrl, newUrl) -> {
            addressBar.setText(newUrl);
        });
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case RUNNING:
                    progressBar.setVisible(true);
                    break;
                case SUCCEEDED:
                case FAILED:
                    progressBar.setVisible(false);
                    Throwable e = webEngine.getLoadWorker().getException();
                    System.out.println("Page failed to load: " + (e != null ? e.getMessage() : "Unknown Error"));
                    break;
            }

        });

        webEngine.load("https://www.google.com");
        webHistory= webEngine.getHistory();
    }

    @FXML
    private void loadWebpage() {
        String url = addressBar.getText();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        webEngine.load(url);

    }
    @FXML
    private void refreshWebpage(){
        webEngine.reload();
    }
    @FXML
    private void back(){

        if(webHistory.getCurrentIndex() > 0){
            webHistory.go(-1);
        }
    }
    @FXML
    private void forward(){
        if(webHistory.getCurrentIndex() < webHistory.getEntries().size() - 1){
            webHistory.go(1);
        }
    }
    static void main(String[] args) {
        launch();
    }
}
