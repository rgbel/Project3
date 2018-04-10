package application;

	

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import roster.Employee;
import roster.EmployeeRoster;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main extends Application {

	

	// This variable should be used instead of directly indicating the file's name.

	//This allows for the databaseFile to be internally modified without having to search for all instances of it.

	public static final String databaseFile = "warehouseDB.txt";
	
	public static Employee activeUser = null;
	public static EmployeesAndWarehouses whf = new EmployeesAndWarehouses(new WarehouseFleet(),new EmployeeRoster()); //newdhjkwfh 

	//public static WarehouseFleet programFleet = new WarehouseFleet(WarehouseFleet.fileToFleet(databaseFile));

	@Override

	public void start(Stage primaryStage) {

		try {

			primaryStage.setTitle("Bike Parts Distributorship");

			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("bikemenu.fxml"));

			Scene scene = new Scene(root,600,400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.show();

			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			        public void run() {

			            //programFleet.fleetToFile(databaseFile);

			        }

			    }));

		} catch(Exception e) {

			e.printStackTrace();

		}

	}

//warehouse and employess
   @Override
   public void stop() throws FileNotFoundException, IOException {
      System.out.println("Stage is closing");
      // Save warehouse DB
      ObjectOutputStream oos = null;
      FileOutputStream fout = null;
      try{
         fout = new FileOutputStream("project3.ser", false);
         oos = new ObjectOutputStream(fout);
         oos.writeObject(whf); //...
         System.out.println("project3.ser written");
      } 
      catch (Exception e) {
         e.printStackTrace();
      } 
      finally {
         if(oos != null){
            oos.close();
         }
      }
   
   }

	public static void main(String[] args) throws IOException {
		
		File f = new File("project3.ser");
      if(f.exists() && !f.isDirectory()) {
         System.out.println("Using serializable file, project3.ser.");
         ObjectInputStream objectinputstream = null;
         FileInputStream streamIn = null;
         try {
            streamIn = new FileInputStream("project3.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            whf = (EmployeesAndWarehouses) objectinputstream.readObject();
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
         System.out.println("project3.ser not found");
      }


		launch(args);
	}

}