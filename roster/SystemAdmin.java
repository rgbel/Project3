package roster;

import java.io.Serializable;

public class SystemAdmin extends Employee implements Serializable{

	public SystemAdmin(String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {
		super(nameFirst, nameLast, phoneNumber, email, loginInfo);
		this.getLoginInfo().changePermission(0);
	}
	
	public SystemAdmin() {}

}
