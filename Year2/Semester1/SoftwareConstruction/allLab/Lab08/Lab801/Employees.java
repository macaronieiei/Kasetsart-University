package Lab08.Lab801;

public abstract class Employees {
	protected String firstName;
	protected String lastName;
	protected String securityNumber;
	private Card debitCard;
	private SavingAccount savingAccount;
	
	public Employees(String first, String last, String ssn) {
		firstName = first;
		lastName = last;
		securityNumber = ssn;
	}
	public String toString() {
		return String.format("%s %s\nsocial security number: %s",firstName, lastName, securityNumber);
	}
	public abstract double earnings();
	
	public String getSecurityNumber() {
		return securityNumber;
	}
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}
	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}
	public void setDebitCard(Card card) {
		debitCard = card;
	}
}
