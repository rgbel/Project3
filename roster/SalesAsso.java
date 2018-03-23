package roster;

public class SalesAsso extends Employee {
	
	public SalesAsso (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {

		super(nameFirst, nameLast, phoneNumber, email, loginInfo);

		this.getLoginInfo().changePermission(1);

	}
	
	public SalesAsso() {}
}
