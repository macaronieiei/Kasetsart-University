package lap602;

import lap801.SavingAccount;

public class Cashier {
    private double total = 0;
    private InventoryCart cart;  
    private SavingAccount savingAccount;

    public void doPayment(InventoryCart cart, SavingAccount savingAccount) {
        this.cart = cart;
        this.savingAccount = savingAccount;
        
        Product[] products = cart.getAllProduct();
        int count = products.length;
        boolean[] processed = new boolean[count]; 
        total = 0; 
        for (int i = 0; i < count; i++) {
            if (products[i] != null && !processed[i]) {
                int number = 0;
                for (int j = i; j < count; j++) {
                    if (products[j] != null && products[i].equals(products[j]) && !processed[j]) {
                        number++;
                        processed[j] = true;  
                    }
                }
                if (number > 0) {
                    Product pc = products[i];
                    pc.setAmount(number);
                    total += pc.getPrice() * number;
                }
            }
        }
        double discountedTotal = total - (total * (savingAccount.getDiscount() / 100));
        savingAccount.getSavingAccount().withdraw(discountedTotal); 
        this.total = discountedTotal;
    }
    public void printReceipt() {
        Product[] products = cart.getAllProduct();
        int count = products.length;
        boolean[] processed = new boolean[count]; 
        System.out.println("Pumkin Shop");
        System.out.println("CARD TYPE: " + savingAccount.getType());
        String secret = savingAccount.getSecurityNumber();
        String maskedCardNumber = "";
        int index = 0;
        for (char c : secret.toCharArray()) {
            if (c == '-') {
                maskedCardNumber += '-';
            } else if (index < 8) {
                maskedCardNumber += 'x';
            } else {
                maskedCardNumber += c;
            }
            index++;
        }
        System.out.println("CARD NUMBER: " + maskedCardNumber);

       
        for (int i = 0; i < count; i++) {
            if (products[i] != null && !processed[i]) {
                int number = 0;

                
                for (int j = i; j < count; j++) {
                    if (products[j] != null && products[i].equals(products[j]) && !processed[j]) {
                        number++;
                        processed[j] = true;  
                    }
                }
                if (number > 0) {
                    Product pc = products[i];
                    pc.setAmount(number);
                    System.out.println(number + " x " + pc.toString());
                }
            }
        }
        
        System.out.println("------------------------------");
        System.out.println("CARD DISCOUNT: " + savingAccount.getDiscount() + " %");
        System.out.printf("\t\tTotal: %.2f $%n", total);
    }
}
