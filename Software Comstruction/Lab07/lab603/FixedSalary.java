package lap603;

public class FixedSalary extends Employees{
	private double earn;
	
	public FixedSalary(String firstName , String lastName, String securityNumber,double earn){
		super(firstName,lastName,securityNumber);
		this.earn = earn;
	}
	public double earnings() {
		return this.earn;
	}
	public String toString(){
		return "Fixed salary employee: "+super.toString()+"\nmonthly salary:$"+String.format("%.2f",this.earn);
	}
	public double getEarn() {
		return earn;
	}
	
}