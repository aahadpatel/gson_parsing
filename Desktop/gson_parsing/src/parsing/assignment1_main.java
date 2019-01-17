/*
 * Aahad Patel
 * USCID: 2550970371
*/

package parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Event;
import objects.User;
import objects.Users;
import com.google.gson.JsonSyntaxException;

public class assignment1_main {
	public static void main(String[] args) {
		//boolean for changes made 
		boolean changes = false;
		//boolean for exiting 
		boolean option_eight_exit = false; 
		while (true) {
			Scanner reader = new Scanner(System.in); // Reading from System.in
			// INITIAL QUESTION
			System.out.print("What is the name of the input file? ");
			String filename = reader.nextLine(); // Scans the next token of the input as a string
			System.out.println();
			// once finished
			try {
				//take in filename, trim the whitespace on the ends
				BufferedReader bf = new BufferedReader(new FileReader(filename.trim()));
				Gson gson = new Gson();
				Users users = gson.fromJson(bf, Users.class);
				List<Event> events;
				
				//do not put changes == true if user just chooses option 1 
				while(true) {
					System.out.println(displayMenu());
					System.out.println();
					System.out.print("What would you like to do? ");
					Scanner read = new Scanner(System.in);
					int optionNumber = read.nextInt();
					System.out.println();
					//make sure user response is within bounds
					if (optionNumber < 9 && optionNumber > 0) {
						//DISPLAY USER'S CALENDAR
						if (optionNumber == 1) {
							while(true) {
							char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
							//print out all users
							for (int i = 0; i < users.getUsers().size(); i++) {
								System.out.println();
								System.out.print(i + 1 + ") ");
								System.out.print(users.getUsers().get(i).getName().getLname() + ", "
										+ users.getUsers().get(i).getName().getFname());
								//PRINT OUT EVENTS
								for (int j = 0; j < users.getUsers().get(i).getEvents().size(); j++) {
									System.out.println();
									System.out.print("\t" + alphabet[j] + ". ");
									System.out.print(users.getUsers().get(i).getEvents().get(j).getTitle() + ", ");
									System.out.print(users.getUsers().get(i).getEvents().get(j).getTime());
									System.out.print(", " + users.getUsers().get(i).getEvents().get(j).getDate().getMonth());
									System.out.print(" " + users.getUsers().get(i).getEvents().get(j).getDate().getDay());
									System.out.print(", " + users.getUsers().get(i).getEvents().get(j).getDate().getYear());
								}
								System.out.println();
							}
							System.out.println();
							break;
							}
						} 
						//ADD USER
						else if (optionNumber == 2) {
							// CHECK IF USING WHILE TRUE IS APPROPRIATE
							while (true) {
								boolean duplicate_name = false;
								//ask for user's name 
								System.out.print("What is the user’s name? ");
								Scanner name = new Scanner(System.in);
								String read_name = name.nextLine();
								//check if user inputs first AND last name
								
								if (!((read_name.trim()).contains(" "))) {
									System.out.println();
									System.out.println("Invalid, must have first and last name. ");
									System.out.println();
								} 
								else {
									String[] split_read_name = read_name.split(" ", 2);
									String first_name = split_read_name[0];
									String last_name = split_read_name[1];	
									
									for(int i = 0; i < users.getUsers().size(); i++) {
										if((first_name.toLowerCase()).equals((users.getUsers().get(i).getName().getFname()).toLowerCase()) && (last_name.toLowerCase()).equals((users.getUsers().get(i).getName().getLname()).toLowerCase()))
										{
											duplicate_name = true;
											break;
										}
									}
									//if user is already in the system 
									if(duplicate_name == true)
									{
										System.out.println();
										System.out.println("User is already in the system! ");
										System.out.println();
									}
									//if user is not in the system, add user to the system
									else {
										System.out.println();
										User user = new User(first_name, last_name);
										users.addElement(user);
										changes = true;
										break;
									}
								}
							}
						} 
						//REMOVE USER
						else if (optionNumber == 3) {
							boolean no_user = false;
							while (true) {
								//print out all users
								for (int i = 0; i < users.getUsers().size(); i++) {
									System.out.print("\t" + (i + 1) + ") ");
									System.out.print(users.getUsers().get(i).getName().getLname() + ", "
											+ users.getUsers().get(i).getName().getFname());
									System.out.println();
								}
								System.out.println();
								System.out.print("Enter the name of the user you would like to remove: ");
								Scanner name = new Scanner(System.in);
								String read_name = name.nextLine();
								//if input does not have a first and a last name, prompt the user to enter both
								if (!read_name.contains(" ")) {
									System.out.println("Invalid, must have first and last name. ");
								} else {
									String[] split_read_name = read_name.split(" ");
									String first_name = split_read_name[0];
									String last_name = split_read_name[1];
	
									// LOOP THROUGH LIST OF USERS
									for (int i = 0; i < users.getUsers().size(); i++) {
										if (first_name.equals(users.getUsers().get(i).getName().getFname())
												&& last_name.equals(users.getUsers().get(i).getName().getLname())) {
											users.getUsers().remove(i);
											no_user = false;
											break;
										} 
										else {
											no_user = true;
											System.out.println();
											System.out.println("No such user! ");
											System.out.println();
											break;
										}
									}
									if(no_user == false) {
										changes = true;
										break;
									}
								}
								if(no_user == false) {
									break;
								}
							}
						} 
						//ADD EVENT
						else if (optionNumber == 4) {
							for (int i = 0; i < users.getUsers().size(); i++) {
								System.out.print("\t" + (i + 1) + ") ");
								System.out.print(users.getUsers().get(i).getName().getLname() + ", "
										+ users.getUsers().get(i).getName().getFname());
								System.out.println();
							}
							System.out.println();
							System.out.print("To which user would you like to add an event? ");
							Scanner new_read = new Scanner(System.in);
							int option_four = new_read.nextInt();
							User currentUser = users.getUsers().get(option_four - 1);
							if (option_four <= users.getUsers().size() && option_four > 0) {
								System.out.println();
								System.out.print("What is the title of the event? ");
								Scanner title = new Scanner(System.in);
								String title_response = title.nextLine();
								System.out.println();
								System.out.print("What time is the event? ");
								Scanner time = new Scanner(System.in);
								String time_response = time.nextLine();
								System.out.println();
								
								System.out.print("What month? ");
								Scanner month = new Scanner(System.in);
								String month_response = month.nextLine();
								//CHECK WITHIN BOUNDS
								while(Integer.parseInt(month_response) < 1 || Integer.parseInt(month_response) > 12) {
									if(Integer.parseInt(month_response) < 1 || Integer.parseInt(month_response) > 12) {
										System.out.println();
										System.out.println("Please input an integer between 1 and 12 corresponding to each month! ");
										System.out.println();
										System.out.print("What month? ");
										Scanner new_month = new Scanner(System.in);
										month_response = new_month.nextLine();
									}
									else {
										break;
									}
								}
								
								System.out.println();
								System.out.print("What day? ");
								Scanner day = new Scanner(System.in);
								String day_response = day.nextLine();
								//CHECK IF DAY ENTERED IS WITHIN BOUNDS
								while(Integer.parseInt(day_response) < 1 || Integer.parseInt(day_response) > 31) {
									if(Integer.parseInt(day_response) < 1 || Integer.parseInt(day_response) > 31) {
										System.out.println();
										System.out.println("Please input a valid day between 1 and 31! ");
										System.out.println();
										System.out.print("What day? ");
										Scanner new_day = new Scanner(System.in);
										day_response = new_day.nextLine();
									}
									else {
										break;
									}
								}
								System.out.println();
								System.out.print("What year? ");
								Scanner year = new Scanner(System.in);
								String year_response = year.nextLine();
								//check if valid year
								while(Integer.parseInt(year_response) < 1) {
									if(Integer.parseInt(year_response) < 1) {
										System.out.println();
										System.out.println("Please input a positive year! ");
										System.out.println();
										System.out.print("What year? ");
										Scanner new_year = new Scanner(System.in);
										year_response = new_year.nextLine();
									}
								}
	
								// ADD THE ACTUAL EVENT
								String[] month_index = { "January", "February", "March", "April", "May", "June", "July",
										"August", "September", "October", "November", "December" };
								
								String month_conversion = month_index[Integer.parseInt(month_response) - 1];
								Event event = new Event(title_response, time_response, month_conversion, day_response,
										year_response);
								currentUser.addEvent(event);
								changes = true;
								System.out.println();
								System.out.println("Added: " + title_response + ", " + time_response
										+  ", " + month_conversion + " " + day_response + ", " + year_response + " to " + 
										users.getUsers().get(option_four - 1).getName().getFname() + " " +  users.getUsers().get(option_four - 1).getName().getLname() 
										+ "'s " +
										 "calendar.");
								
								//sorting every event 
								Collections.sort(currentUser.getEvents(), new Comparator<Event>() {
									 public int compare(Event a1, Event a2) {
										 //compare years
										//IF YEARS ARE EQUAL 
										 if(a1.getDate().getYear().equals(a2.getDate().getYear())) 
										 {
											 //IF MONTHS ARE EQUAL
											 if(getMonthValue(a1.getDate().getMonth()) == (getMonthValue(a2.getDate().getMonth()))) 
											 {
												 return a1.getDate().getDay().compareTo(a2.getDate().getDay());
											 } 
											 return Integer.valueOf(getMonthValue(a1.getDate().getMonth())).compareTo(Integer.valueOf(getMonthValue(a2.getDate().getMonth())));
										 }
										 else {
											 return a1.getDate().getYear().compareTo(a2.getDate().getYear());
										 }
									 }
								 });
								 changes = true;
								 System.out.println();
							} else {
								System.out.println("Not valid! ");
							}
						}
						//DELETE EVENT
						else if(optionNumber == 5) {
							for (int i = 0; i < users.getUsers().size(); i++) {
								System.out.print("\t" + (i + 1) + ") ");
								System.out.print(users.getUsers().get(i).getName().getLname() + ", "
										+ users.getUsers().get(i).getName().getFname());
								System.out.println();
							}
							System.out.println();
							System.out.print("From which user would you like to delete an event? ");
							Scanner new_read = new Scanner(System.in);
							int option_five = new_read.nextInt();
							//error checking
							while(option_five > users.getUsers().size() || option_five > users.getUsers().size()) {
								System.out.println();
								System.out.print("Invalid option! Make sure you choose a valid number- From which user would you like to delete an event? ");
								Scanner read_again = new Scanner(System.in);
								option_five = read_again.nextInt();
							}
							User currentUser = users.getUsers().get(option_five - 1);
							System.out.println();
							for(int j = 0; j < users.getUsers().get(option_five-1).getEvents().size(); j++) {
								System.out.print("\t" + (j + 1) + ") ");
								System.out.println(users.getUsers().get(option_five-1).getEvents().get(j).getTitle() + 
										", " + users.getUsers().get(option_five-1).getEvents().get(j).getTime() + 
										", " + users.getUsers().get(option_five-1).getEvents().get(j).getDate().getMonth() + 
										" " + users.getUsers().get(option_five-1).getEvents().get(j).getDate().getDay() + 
										", " + users.getUsers().get(option_five-1).getEvents().get(j).getDate().getYear());
							}
							if( users.getUsers().get(option_five-1).getEvents().size() == 0) 
							{
								System.out.println("Calendar is empty.");
								System.out.println();
							}
							else {
								System.out.print("Which event would you like to delete? ");
								Scanner delete_event_read = new Scanner(System.in);
								int delete_option = delete_event_read.nextInt();
								if((delete_option) > users.getUsers().get(option_five-1).getEvents().size() && users.getUsers().get(option_five-1).getEvents().size() != 0) {
									System.out.println();
									System.out.println("Invalid choice! ");
									System.out.println();
								}
								else if((delete_option) < 0 && users.getUsers().get(option_five-1).getEvents().size() != 0) {
									System.out.println();
									System.out.println("Invalid choice! ");
									System.out.println();
								}
								else {
									String removed_option = users.getUsers().get(option_five-1).getEvents().get(delete_option-1).getTitle();
									users.getUsers().get(option_five-1).getEvents().remove(delete_option-1);
									changes = true;
									System.out.println();
									System.out.println(removed_option + " has been deleted.");
									System.out.println();
								}
							}
						}
						//SORT USERS
						else if(optionNumber == 6) {
							 System.out.println("\t1) Ascending (A-Z)\n" + 
							 		"\t2) Descending (Z-A)");
							 System.out.println();
							 System.out.print("How would you like to sort? ");
							 Scanner sort_read = new Scanner(System.in);
							 int sort_option = sort_read.nextInt();
							 
							 //SORT IN ASCENDING ORDER
							 if(sort_option == 1) {
								 //comparator for last names
								 //if last names are equal sort by first name 
								 Collections.sort(users.getUsers(), new Comparator<User>() {
									 public int compare(User a1, User a2) {
										 if(a1.getName().getLname().equals(a2.getName().getLname())) {
											 //sort by first name if last names are equal
											 return a1.getName().getFname().compareTo(a2.getName().getFname());
										 }
										 else {
											 return a1.getName().getLname().compareTo(a2.getName().getLname());
										 }
									 }
								 });
								 System.out.println();
								 for (int i = 0; i < users.getUsers().size(); i++) {
										System.out.print("\t" + (i + 1) + ") ");
										System.out.print(users.getUsers().get(i).getName().getLname() + ", "
												+ users.getUsers().get(i).getName().getFname());
										System.out.println();
									}
								 System.out.println();
								 changes = true;
							 }
							 //SORT IN DESCENDING ORDER 
							 else if(sort_option == 2) {
								 Collections.sort(users.getUsers(), new Comparator<User>() {
									 public int compare(User a1, User a2) {
										//sort by first name if last names are equal
										 if(a1.getName().getLname().equals(a2.getName().getLname())) {
											 return a1.getName().getFname().compareTo(a2.getName().getFname());
										 }
										 else {
											 return a1.getName().getLname().compareTo(a2.getName().getLname());
										 }
									 }
								 });
								 //reverse for descending order
								 Collections.reverse(users.getUsers());
								 System.out.println();
								 for (int i = 0; i < users.getUsers().size(); i++) {
										System.out.print("\t" + (i + 1) + ") ");
										System.out.print(users.getUsers().get(i).getName().getLname() + ", "
												+ users.getUsers().get(i).getName().getFname());
										System.out.println();
									}
								 System.out.println();
								 changes = true;
							 }
							 else {
								 System.out.println("Invalid option!");
							 }
						 }
						//WRITE TO FILE
						 else if(optionNumber == 7) {
							//save to a file
							 while(true) {
								 try (FileWriter file_writer = new FileWriter(filename)) {
									 Gson gson_object = new GsonBuilder().create();
									 //write to file using .toJson()
									 gson_object.toJson(users, file_writer);
									 file_writer.flush();
									 System.out.println("File has been saved. ");
									 System.out.println();
									 break;
								 }
								 catch (Exception e) {
										e.printStackTrace();
								 }
							 }
						 }
						//EXIT/PROMPT USER TO SAVE IF CHANGES HAVE BEEN MADE
						 else if(optionNumber == 8) {
							 //if changes have been made do this->
							 if(changes == true) {
								 System.out.println("Changes have been made since the file was last saved. ");
								 System.out.println("\t1) Yes\n" + 
								 		"\t2) No");
								 System.out.println();
								 System.out.print("Would you like to save the file before exiting? ");
								 Scanner yes_no_read = new Scanner(System.in);
								 int option_value = yes_no_read.nextInt();
								 //if user wants to save
								 if(option_value == 1) {
									 System.out.println("File was saved. ");
									 System.out.println();
									 System.out.println("Thank you for using my program! ");
									 option_eight_exit = true;
									 
									 //save to a file
									 try (FileWriter file_writer = new FileWriter(filename)) {
										 Gson gson_object = new GsonBuilder().create();
										 gson_object.toJson(users, file_writer);
										 file_writer.flush();
										 break;
									 }
									 catch (Exception e) {
											e.printStackTrace();
									 }
								 }
								 //if user does not want to save (if the user enters "2")
								 else 
								 {
									 System.out.println();
									 System.out.println("File was not saved. ");
									 System.out.println();
									 System.out.println("Thank you for using my program! ");
									 option_eight_exit = true;
									 break;
								 }
							 }
							 else 
							 {
								 System.out.println();
								 System.out.println("File was not saved. ");
								 System.out.println();
								 System.out.println("Thank you for using my program! ");
								 option_eight_exit = true;
								 break;
							 }
						 }
					}
					//else if out of bounds
					 else {
						 System.out.println("That is not a valid option");
						 System.out.println();
					 }
					if(option_eight_exit == true) {
						break;
					}
				}
			} catch (FileNotFoundException fnfe) {
				System.out.println("That file could not be found.");
				System.out.println();
			} catch (JsonSyntaxException ex) {
				System.out.println("That file is not a well-formed JSON file.");
				System.out.println();
			}
			if(option_eight_exit == true) {
				break;
			}
		}
	}
	//MENU
	public static String displayMenu() {
		String menu = "\t1) Display User’s Calendar\n" + "\t2) Add User\n" + "\t3) Remove User\n" + "\t4) Add Event\n"
				+ "\t5) Delete Event\n" + "\t6) Sort Users\n" + "\t7) Write File\n" + "\t8) Exit";
		return menu;
	}
	//INTEGER VALUE FOR EACH MONTH
	public static int getMonthValue(String month) {
		if(month.equals("January"))
		{
			return 1;
		}
		else if(month.equals("February"))
		{
			return 2;
		}
		else if(month.equals("March"))
		{
			return 3;
		}
		else if(month.equals("April"))
		{
			return 4;
		}
		else if(month.equals("May"))
		{
			return 5;
		}
		else if(month.equals("June"))
		{
			return 6;
		}
		else if(month.equals("July"))
		{
			return 7;
		}
		else if(month.equals("August"))
		{
			return 8;
		}
		else if(month.equals("September"))
		{
			return 9;
		}
		else if(month.equals("October"))
		{
			return 10;
		}
		else if(month.equals("November"))
		{
			return 11;
		}
		//if December
		else
		{
			return 12;
		}
	}
}
