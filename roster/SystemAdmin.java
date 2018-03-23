package roster;

public class SystemAdmin extends Employee{

	public SystemAdmin(String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {
		super(nameFirst, nameLast, phoneNumber, email, loginInfo);
		this.getLoginInfo().changePermission(0);
	}

}
