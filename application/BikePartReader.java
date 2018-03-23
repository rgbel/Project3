package application;

import java.util.ArrayList;

public class BikePartReader {

	public static void main(String[] args) {
/*		BikePart.startup();*/
		WarehouseFleet testFleet = new WarehouseFleet(WarehouseFleet.fileToFleet("warehouseDB.txt"));
		testFleet.transferParts("transfer.txt");
		testFleet.newVan("newVan");
		System.out.println(testFleet.sellFromAll("1234567908"));
		ArrayList<BikePart> test = testFleet.numSort("sideVan");
		for(BikePart t : test) {
			System.out.println(t.getAll());
		}
	}

}
