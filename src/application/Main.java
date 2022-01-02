package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class Main extends Application{

	public void start(Stage primaryStage) {
		try {

			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainController.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        primaryStage.setTitle("Projet entropot");
			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
