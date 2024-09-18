package lap602;

public class InventoryCart {
	private Product[] products;
	private int index = 0;

	public InventoryCart(int product) {
		products = new Product[product];
	}
	public void add(Product p) {
		products[index++] = p;
	}
	public Product getProductAt(int num) {
            return products[num];
    }
	public Product[] getAllProduct() {
		return products;
	}
}