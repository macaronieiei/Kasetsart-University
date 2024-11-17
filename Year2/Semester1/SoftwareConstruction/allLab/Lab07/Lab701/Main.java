package lap701;

public class Main {

	public static void main(String[] args) {
		BallA b1 = new BallA("Zentia");
		BallB b2 = new BallB("Zapphire");
		BallC b3 = new BallC("Zenith");
		TestBall(b1, 1.0);
		TestBall(b2, 1.1);
		TestBall(b3, 1.2);
	}
	
	public static void TestBall(Ball b,double volume) {
		System.out.println(b.getTreadMark()+" is a trademark of "+b.getClass().getSimpleName());
		b.inflate(volume);
		b.roll();
		System.out.println();
	}
}