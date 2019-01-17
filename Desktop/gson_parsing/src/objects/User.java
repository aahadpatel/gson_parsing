package objects;

import java.util.ArrayList;
import java.util.List;

public class User {
	Name Name;
	List<Event> Events = new ArrayList<>();
	
	public void addEvent(Event event) {
		Events.add(event);
	}
	
	public User(String fname, String lname) {
		Name = new Name(fname,lname);
	}
	
	public void setName(Name name) 
	{
		this.Name = name;
	}
	
	public Name getName() {
		return Name;
	}
	
	public List<Event> getEvents()
	{
		return Events;
	}
}
