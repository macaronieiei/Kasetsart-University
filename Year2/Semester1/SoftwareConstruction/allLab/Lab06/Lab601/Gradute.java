package lap601;

public class Gradute extends Student {
	private String thesisTitle;
	public  Gradute(int id,String name,double gpa,String thesisTitle) {
		super(id,name,gpa);
		this.thesisTitle = thesisTitle;
	}
	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}
	public String getThesisTitle() {
		return thesisTitle;
	}
	public String toString() {
		return "Gradute Student : \nID : "+getID()+"\nName : "+getName()+"\nGPA : "+getGPA()+"\nThesis : "+getThesisTitle();
	}
}