package roster;

public class OfficeMan extends Employee {
	
	public OfficeMan (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {

		super(nameFirst, nameLast, phoneNumber, email, loginInfo);

		this.getLoginInfo().changePermission(2);

	}
	
	public OfficeMan() {}

}
