package lap702;

public class DebitCard extends Card {
	private double balance;
	private SavingAcoount account;
	private String type;
	private double discount;
	private String securityNumber;
	
	public DebitCard(SavingAcoount s,String type,double discount,double balance,String securityNumber) {
		this.account = s;
		this.type = type;
		this.discount = discount;
		this.balance = balance;
		this.securityNumber = securityNumber;
	}
	@Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public String type() {
        return type;
    }
    @Override
    public double discount() {
        return discount; 
    }
    public double getBalance() {
        return balance;
    }
	public String getSecurityNumber() {
		return securityNumber;
	}
	public SavingAcoount getAccount() {
		return account;
	}
}
