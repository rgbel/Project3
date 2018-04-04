package application;

	

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Main extends Application {

	

	// This variable should be used instead of directly indicating the file's name.

	//This allows for the databaseFile to be internally modified without having to search for all instances of it.

	public static final String databaseFile = "warehouseDB.txt";

	public static WarehouseFleet programFleet = new WarehouseFleet(WarehouseFleet.fileToFleet(databaseFile));

	@Override

	public void start(Stage primaryStage) {

		try {

			primaryStage.setTitle("Bike Parts Distributorship");

			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("bikemenu.fxml")); //.....

			Scene scene = new Scene(root,600,400);

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
		
		
		/**
		 *  File f = new File("project2.ser");
      if(f.exists() && !f.isDirectory()) {
         System.out.println("Using serializable file, project2.ser.");
         ObjectInputStream objectinputstream = null;
         FileInputStream streamIn = null;
         try {
            streamIn = new FileInputStream("project2.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            whf = (WareHouseFactory) objectinputstream.readObject();
         } 
         catch (Exception e) {
            e.printStackTrace();
         } 
         finally {
            if (objectinputstream != null) {
               objectinputstream.close();
            }
         }
      }
      else {
         System.out.println("project2.ser not found");
      }
      launch(args);
		 */

	}

}