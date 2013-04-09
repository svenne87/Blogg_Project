package blog;
public class Author {
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String country;
	private String email;
	
	public void setValues(String firstName_in, String lastName_in, int age_in, String gender_in, String country_in, String email_in){
		firstName = firstName_in;
		lastName = lastName_in;
		age = age_in;
		gender = gender_in;
		country = country_in;
		email = email_in;
	}
	
	
	public int getId(){
		return id;
	}
	
	public void setId(int id_in){
		id = id_in;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getName(){
		return firstName + " " + lastName;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getCountry(){
		return country;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String returnString(){
		return "Namn: " + firstName + " " + lastName + "\r\n" + "Ålder: " + age + "\r\n" + "Kön: " + gender + "\r\n" + "Nationalitet: " + country + "\r\n" + "E-mail: " + email + "\r\n";
	}
	

}
