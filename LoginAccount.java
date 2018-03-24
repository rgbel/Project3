 package roster;

public class LoginAccount {
	
	String username;
	String password;
	int permission;
	static int permissionRange = 3;
	
	public LoginAccount(String username, String password, int permission) {
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	
	public String getUsername() { return username; }
	public int getPermission() { return permission; }
	
	public boolean resetPassword(String newPassword) {
		
		if(newPassword != null) {
			this.password = newPassword;
			return true;
		}
		return false;
		
	}
	
	public boolean confirmUsername(String attemptUsername) { return(attemptUsername.equals(this.username)); }
	public boolean confirmPassword(String attemptPassword) { return(attemptPassword.equals(this.password)); }
	
	public boolean changePermission(int newPermission) {
		if(newPermission <= permissionRange && newPermission > -1) {
			permission = newPermission;
			return true;
		}
		return false;
	}
	
	public static boolean changeRange(int newRange) {
		if(newRange >= 0) {
			permissionRange = newRange;
			return true;
		}
		return false;
	}

}
