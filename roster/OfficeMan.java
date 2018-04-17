package roster;

import java.io.Serializable;

public class OfficeMan extends Employee implements Serializable{
	
	public OfficeMan (String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {

		super(nameFirst, nameLast, phoneNumber, email, loginInfo);

		this.getLoginInfo().changePermission(2);

	}
	
	public OfficeMan() {}

}
