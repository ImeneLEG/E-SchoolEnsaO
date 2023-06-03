package model;

public class Student {
	
	private String firstname;
	private String lastname;
	private String email;
	private String level ;
	private int id; 
	
	//Constructors:
	
	public Student() {
		firstname= "";
		lastname="";
		email="";
		level="";
	}
	
	public Student(String firstname,String lastname,String email,String level,int id) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.level=level;
		this.id=id;
	}
	
	//getters and setters
	
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname=firstname;
	}
	
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname=lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level=level;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int  id) {
		this.id=id;
	}
	
	
	
}
