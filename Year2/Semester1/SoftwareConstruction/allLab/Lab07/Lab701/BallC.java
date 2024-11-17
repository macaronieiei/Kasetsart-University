package lap701;

public class BallC extends BallA {
	public BallC(String treadMark) {
		super(treadMark);
	}
	@Override
	public void inflate(double volume) {
		this.volume = volume;
		System.out.println(getTreadMark()+"'s ball is inflate "+volume+" cu.ft.");	
	}
}
