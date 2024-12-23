package lap602;

public class Cashier {
    private String customer;
    private int total = 0;

    public Cashier(String customer) {
        this.customer = customer;
    }

    public void printReceipt(InventoryCart c) {
        int count = c.getAllProduct().length;
        System.out.println("\t-------------\t");
        System.out.println("Pumpkin Shop (" + customer + ")");

        for (int i = 0; i < count; i++) {
            int number = 0;
            
            for (int j = i; j < count; j++) {
            	if(c.getProductAt(i)!=null) {
            		if (c.getProductAt(i).equals(c.getProductAt(j)) && !c.getProductAt(j).isCheck()) {
            			number++;
            			c.getProductAt(j).setCheck(true);
            		}
            	}
            }
            Product pc = c.getProductAt(i);
            if(pc!=null) {
            	pc.setAmount(number);
            	if (number != 0) {
            		System.out.println(number + " x " + c.getProductAt(i).toString());
            	}
            	total += pc.getPrice() * number;
            }
        }
        System.out.println("\t-------------\t");
        System.out.println("\t\tTotal\t\t" + total +" $");
    }
}