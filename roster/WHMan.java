package roster;

import java.io.Serializable;

public class WHMan extends Employee implements Serializable{

	public WHMan(String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {
		
		super(nameFirst, nameLast, phoneNumber, email, loginInfo);
		
		this.getLoginInfo().changePermission(3);
	}
	
	public WHMan() {}

}
