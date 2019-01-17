package objects;

public class Event {
	String Title;
	String Time;
	String Year;
	Date Date;
	public String getTitle() {
		return Title;
	}
	public String getTime() {
		return Time;
	}
	public Date getDate() {
		return Date;
	}
	public String getYear() {
		return Year;
	}
	
	public Event(String title, String time, String month, String day, String year) {
		this.Title = title; 
		this.Time = time;
		this.Date = new Date(month,day,year);
		this.Year = year;
	}
	
	public void setTitle(String title) {
		this.Title = title;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	public void setDate(Date date) {
		this.Date = date;
	}
}
