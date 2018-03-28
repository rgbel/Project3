package roster;



public abstract class Employee {



    private String nameFirst;

    private String nameLast;

    private String phoneNumber;

    private String email;



    private LoginAccount loginInfo;



    public Employee(String nameFirst, String nameLast, String phoneNumber, String email, LoginAccount loginInfo){



        this.nameFirst = nameFirst;

        this.nameLast = nameLast;

        this.phoneNumber = phoneNumber;

        this.email = email;

        this.loginInfo = loginInfo;

    }

    public Employee() {}



    public void setNameFirst(String nameFirst){  this.nameFirst = nameFirst;  }

    public String getNameFirst(){  return nameFirst;  }



    public void setNameLast(String nameLast){  this.nameLast = nameLast;  }

    public String getNameLast(){  return nameLast;  }



    public void setPhoneNumber(String phoneNumber){  this.phoneNumber = phoneNumber;  }

    public String getPhoneNumber(){  return phoneNumber;  }



    public void setEmail(String email){  this.email = email;  }

    public String getEmail(){  return email;  }



    public void setLoginInfo(LoginAccount loginInfo){  this.loginInfo = loginInfo;  }

    public LoginAccount getLoginInfo(){  return loginInfo;  }

    

    /**

     * Returns all fields of the employee formatted for placement in a file.

     */

    public String getAll() {

    	return(nameFirst + "," + nameLast + "," + phoneNumber + "," + email + "," + loginInfo.getUsername() + "," + loginInfo.getPassword() + "," + loginInfo.getPermission());	

    }



}