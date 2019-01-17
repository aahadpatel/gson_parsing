package objects;
import java.util.List;

public class Users {
	List<User> Users;
	public List<User> getUsers() {
		return Users;
	}
	public void addElement(User read_name) {
		this.Users.add(read_name);
	}
	public void removeElement(User read_name) {
		this.Users.remove(read_name);
	}
}
