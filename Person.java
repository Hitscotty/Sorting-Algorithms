package HW7;

public class Person extends SearchKey<String>{

	private String name; // Will be used as the Search Key.
	private String phoneNumber;// Format xxx-xxx-xxxx, where every x is in the range 0-9.
	private static int size = 0;
	
	public Person(String name, String phone){
		super(name);
		this.name = name;
		this.phoneNumber = phone;
		
		size++;
	}
	public boolean equals(Object obj){
		if(this.name == obj && this.phoneNumber == obj){
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setPhoneNumber(String phone){
		this.phoneNumber = phone;
	}
	
	//display info
	public String toString(){
		return getName() + ": " + phoneNumber;
	}
	
	public int size() {
		return size;
	}
}