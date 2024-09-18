package lap701;

public class BallA extends Ball implements rollAble{
	protected double volume;
	public BallA(String tradeMark) {
		super(tradeMark);
	}
	
	@Override
	public void inflate(double volume) {
		this.volume = volume;
		System.out.println(getTreadMark()+"'s ball is inflate "+volume+" cu.ft.");	
	}
	
	@Override
	public void roll() {
		if(volume==1.0)
			System.out.println(getTreadMark()+" rolls rather smoothly.");
		if(volume==1.1)
			System.out.println(getTreadMark()+" rolls smoothly.");
		if(volume==1.2)
			System.out.println(getTreadMark()+" rolls very smoothly.");
	}
}