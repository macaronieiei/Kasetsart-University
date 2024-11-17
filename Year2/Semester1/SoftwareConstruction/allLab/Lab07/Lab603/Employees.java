package lap603;

import lap702.DebitCard;
import lap702.SavingAcoount;

public abstract class Employees {
	protected String firstName;
	protected String lastName;
	protected String securityNumber;
	private DebitCard debitCard;
	private SavingAcoount savingAcoount;
	
	public Employees(String first, String last, String ssn) {
		firstName = first;
		lastName = last;
		securityNumber = ssn;
	}
	public String toString() {
		return String.format("%s %s\nsocial security number: %s",
	firstName, lastName, securityNumber);
	}
	public abstract double earnings();
	public String getSecurityNumber() {
		return securityNumber;
	}
	public DebitCard getCard() {
		return debitCard;
	}
	public void setDebitCard(DebitCard debitCard) {
		this.debitCard = debitCard;
	}
	public SavingAcoount getSavingAcoount() {
		return savingAcoount;
	}
}