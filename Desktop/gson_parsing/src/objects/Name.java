package objects;

public class Name {
	String Fname;
	String Lname;
	
	Name(String fname, String lname) {
		Fname = fname;
		Lname = lname;
	}
	public String getFname() {
		return Fname;
	}
	public String getLname() {
		return Lname;
	}
	
}
