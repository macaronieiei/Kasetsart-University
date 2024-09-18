package lap801;

public class SavingAccount extends BaseAccount{
	private double balance;
	private Card card;
	
	public SavingAccount(Employees employees) {
		this.balance = employees.earnings();
		this.card = new DebitCard(this, "VISA", 2.5, this.balance, employees.getSecurityNumber());
		employees.setSavingAccount(this);
		employees.setDebitCard(card);
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
	public Card getCard() {
		return card;
	}
	public String getType() {
		return card.type();
	}
	public String getSecurityNumber() {
		return ((DebitCard)card).getSecurityNumber();
	}
	public double getDiscount() {
	   return ((DebitCard)card).discount();
	}
	public SavingAccount getSavingAccount(){
	   return ((DebitCard)card).getAccount();
	}
	
	public class DebitCard extends Card {
		private double balance;
		private SavingAccount account;
		private String type;
		private double discount;
		private String securityNumber;
		
		public DebitCard(SavingAccount s,String type,double discount,double balance,String securityNumber) {
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
		public SavingAccount getAccount() {
			return account;
		}
	}
}
