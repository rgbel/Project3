package application;

import java.io.Serializable;
import java.util.ArrayList;

import roster.EmployeeRoster;

public class EmployeesAndWarehouses implements Serializable{

	WarehouseFleet programFleet = new WarehouseFleet(new ArrayList<Warehouse>());
	EmployeeRoster programRoster = new EmployeeRoster();
	
	public EmployeesAndWarehouses(WarehouseFleet programFleet, EmployeeRoster programRoster) {
		this.programFleet = programFleet;
		this.programRoster = programRoster;
	}
	public EmployeesAndWarehouses() {}
	
	public WarehouseFleet getProgramFleet() {
		return programFleet;
	}

	public void setProgramFleet(WarehouseFleet programFleet) {
		this.programFleet = programFleet;
	}

	public EmployeeRoster getProgramRoster() {
		return programRoster;
	}

	public void setProgramRoster(EmployeeRoster programRoster) {
		this.programRoster = programRoster;
	}


	

	
}
