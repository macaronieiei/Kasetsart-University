package lap2;
import java.util.Scanner;

public class lap201 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input the number : ");
		String s = scanner.nextLine();
		
		int sum = 0;
		System.out.print(s + " = ");
		for(int i=0;i<s.length();i++) {
			sum = sum + Integer.parseInt(s.charAt(i)+"");
			System.out.print(s.charAt(i));
			if(i<s.length()-1) {
				System.out.print(" + ");
			}
		}
		System.out.print(" = "+sum);	
	}

}