package lap701;

public class BallB extends BallA {
	public BallB(String treadMark) {
		super(treadMark);
	}
	@Override
	public void inflate(double volume) {
		this.volume = volume;
		System.out.println(getTreadMark()+"'s ball is inflate "+volume+" cu.ft.");
		
	}
}
