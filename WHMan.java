package roster;

public class WHMan extends Employee {

	public WHMan(String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo) {
		
		super(nameFirst, nameLast, phoneNumber, email, loginInfo);
		
		this.getLoginInfo().changePermission(3);
	}
	
	public WHMan() {}

}
