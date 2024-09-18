package Lab09.Lab901;

public class Lab_901 {
    public static void main(String[] args) {
		int num[] = { 1, 2, 3, 4 };
		try {
			System.out.println(num[5]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);		
		}
		for (int i : num) {
			System.out.println(i);
		}
	}
}
