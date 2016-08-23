
public enum Drivetrain {
	FWD(15), RWD(18), AWD(25);
	private int lossPercentage;
	
	private Drivetrain(int lossPercentage) {
		this.lossPercentage = lossPercentage;
	}
	
	public int getLossPercentage() {
		return lossPercentage;
	}
}
