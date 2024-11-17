package lap701;

public abstract class Ball implements rollAble {
	private String tradeMark;

	public String getTreadMark() {
		return tradeMark;
	}
	public Ball(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public abstract void inflate(double volume);
}
