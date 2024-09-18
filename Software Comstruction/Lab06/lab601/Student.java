package lap601;

public class Student {
	private String name;
	private int id;
	private double gpa;
	
	public Student(int id,String name,double gpa) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;
	}
	public Student(int id,double gpa) {
		this.id = id;
		this.name = " ";
		this.gpa = gpa;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return id;
	}
	public double getGPA() {
		return gpa;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "Student : \nID : "+getID()+"\nName : "+getName()+"\nGPA : "+getGPA()+"\n";
	}

}