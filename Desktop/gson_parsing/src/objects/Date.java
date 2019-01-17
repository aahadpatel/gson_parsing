package objects;

public class Date {
	String Month;
	String Day;
	String Year;
	public Date(String month, String day, String year) {
		Month = month;
		Day = day;
		Year = year;
	}
	public String getMonth() {
		return Month;
	}
	public String getDay() {
		return Day;
	}
	public String getYear() {
		return Year;
	}
	public void setMonth(String month) 
	{
		this.Month = month;
	}
	public void setDay(String day) 
	{
		this.Day = day;
	}
	public void setYear(String year) 
	{
		this.Year = year;
	}
}
