package lap601;

public class Undergrad extends Student {
	private String year;
	public Undergrad(int id, String name, double gpa, String year) {
		super(id, name, gpa);
		this.year = year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String  getYear() {
		return year;
	}
	public String toString() {
		return "Undergraduate Student : \nID : "+getID()+"\nName : "+getName()+"\nGPA : "+getGPA()+"\nYear : "+getYear()+"\n";
	}
}