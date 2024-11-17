package lap5;

public class Cashier {
	public String nameCahier;
	
	public Cashier(String nameCart) {
		this.nameCahier = nameCart;
	}
	public void printReceipt(InventoryCart cart) {
        double total = 0;
        System.out.println("\tPumkin Shop ( " + nameCahier + " )");

        for (int i = 0; i < cart.getAllProduct().length; i++) {
            Product eachProduct = cart.getProductAt(i);
            if (eachProduct != null) {
                System.out.println("1 x " + eachProduct.getName() + "\t(" + eachProduct.getCode() + ")\t" + eachProduct.getPrice());
                total = total + eachProduct.getPrice();
           }
        }
		System.out.println("\t\t--------------------");
		System.out.println("\t\tTotal "+total+" Bath");
	}
}