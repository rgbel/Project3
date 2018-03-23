package application;

public class Part implements PartInterface {
	String name;
	int number;
	
	public Part(String iname, int inumber) {
		name = iname;
		number = inumber;
	}
	public Part() {
		String name;
		int number;
	}
	
	/** Sets the name value of the Part to the newName value 
	 * @param newName 		Chosen value to replace the existing name.
	 * */
	public void setName(String newName){
		name = newName;
	}
	
	/** Sets the ID number value of the Part to the newNumber value 
	 * @param newNumber 		Chosen value to replace the existing number.
	 * */
	public void setNumber(int newNumber){
		number = newNumber;
	}
	/** Outputs the name value of a part
	 *  @return name, pulled from the object.
	 * */
	public String getName(){
		return name;
	}
	
	/** Outputs the number value of a part
	 *  @return number, pulled from the object.
	 * */
	public int getNumber(){
		return number;
	}
	// Created for Lab 10
	public String getAll() { return (this.getName() + "," + this.getNumber()); }
	
	
}
