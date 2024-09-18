package lap702;
import lap602.Milk;
import lap602.Sugar;
import lap603.FixedSalary;
import lap602.Coffee;
import lap602.Product;
import lap602.InventoryCart;
import lap602.Cashier;

public class Main {
	public static void main(String[] args) {
	FixedSalary employeeA = new FixedSalary("Clark", "Kent",
	"555-999-5555", 15000.00);
	SavingAcoount b = new SavingAcoount(employeeA);
	//System.out.println(b.getBalance());
	b.deposit(1000);
	//System.out.println(b.getBalance());
	//DebitCard card = b.getCard();
	
	
	Milk p1 = new Milk(150);
	p1.setVolumn(250);
	Sugar p2 = new Sugar(50);
	p2.setWeight(250);
	Product p3 = new Coffee(250);
	p3.setWeight(50);
	Product p4 = new Coffee(250);
	p4.setWeight(50);
	InventoryCart ic = new InventoryCart (10);
	ic.add(p1);
	ic.add(p2);
	ic.add(p3);
	ic.add(p4);
	Cashier c = new Cashier();
	c.doPayment(ic, employeeA.getCard());
	c.printReceipt();
	//System.out.print(b.getBalance());
	
	}
}
