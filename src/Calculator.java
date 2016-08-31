/*
 * Class which performs calculations. 
 */

public class Calculator {

	private UserInterface ui;
	private Drivetrain selectedDrivetrain;
	private static final int AUTOMATIC_LOSS = 5;

	/*
	 * Constructor takes GUI input.
	 */
	public Calculator(UserInterface ui) {
		this.ui = ui;
	}

	/*
	 * Main calculate function called by Calculate button.
	 */
	public void calculate() {

		//Empty crank HP = 0
		if (ui.getHorsepower().isEmpty()) {
			ui.setHorsepowerValue(0);
		}
		
		//Retrieve crank HP value
		int horsepowerValue = ui.getHorsepowerValue();
		
		//Set crank HP to 0 at minimum
		horsepowerValue = Math.max(0, horsepowerValue);

		//Determine selected driveTrain
		//(We are assuming there is one as we select a default)
		if (ui.isFWD()) {
			this.selectedDrivetrain = Drivetrain.FWD;
		} else if (ui.isRWD()) {
			this.selectedDrivetrain = Drivetrain.RWD;
		} else {
			this.selectedDrivetrain = Drivetrain.AWD;
		}
		
		//Calculate loss percentage for selected drivetrain
		int loss = selectedDrivetrain.getLossPercentage();
		
		//If automatic transmission is selected, add additional loss percentage.
		if (ui.isAuto()) {
			loss += AUTOMATIC_LOSS;
		}
		
		double percentLoss, result;
		if (ui.isCrankValueGiven()){
			//Calculate wheel HP by calculating and subtracting drivetrain losses.
			percentLoss = loss / 100.0;
			result = horsepowerValue - percentLoss * horsepowerValue;
			ui.setEstimateOriginLabel(" at the wheels");
		} else {
			//Calculate crank HP by calculating and adding drivetrain losses.
			percentLoss = 100.0 / (100 - loss);
			result = percentLoss * horsepowerValue;
			ui.setEstimateOriginLabel(" at the crank");
		}
		
		//Return result to interface
		ui.setEstimate(result);
	}
}
