package lap702;

import lap603.Employees;

public class SavingAcoount extends BaseAccount{
	private double balance;
	private Card card;
	
	public SavingAcoount(Employees employees) {
		this.balance = employees.earnings();
		this.card = new DebitCard(this, "VISA", 2.5, this.balance, employees.getSecurityNumber());
		employees.setDebitCard((DebitCard)card);
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

	public double getBalance() {
		return balance;
	}
}
