package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	// This variable should be used instead of directly indicating the file's name.
	//This allows for the databaseFile to be internally modified without having to search for all instances of it.
	public static final String databaseFile = "warehouseDB.txt";
	public static WarehouseFleet programFleet = new WarehouseFleet(WarehouseFleet.fileToFleet(databaseFile));
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Bike Part Warehouse Manager");
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			        public void run() {
			            programFleet.fleetToFile(databaseFile);
			        }
			    }));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}