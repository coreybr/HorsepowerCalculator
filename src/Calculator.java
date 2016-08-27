/*
 * Class which performs calculations. 
 */
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class Calculator {

	private JFormattedTextField crankHorsepower;
	private JRadioButton auto;
	private JRadioButton fwd;
	private JRadioButton rwd;
	private Drivetrain selectedDrivetrain;
	private JFormattedTextField wheelHorsepower;
	private static final int AUTOMATIC_LOSS = 5;

	/*
	 * Constructor takes GUI input.
	 */
	public Calculator(JFormattedTextField crankHorsepower, JRadioButton auto, JRadioButton fwd, JRadioButton rwd,
			JFormattedTextField wheelHorsepower) {
		this.crankHorsepower = crankHorsepower;
		this.auto = auto;
		this.fwd = fwd;
		this.rwd = rwd;
		this.wheelHorsepower = wheelHorsepower;
	}

	/*
	 * Main calculate function called by Calculate button.
	 */
	public void calculate() {

		//Empty crank HP = 0
		if (crankHorsepower.getText().isEmpty()) {
			crankHorsepower.setValue(0);
		}
		
		//Retrieve crank HP value
		int crankHorsepowerValue = (int) crankHorsepower.getValue();
		
		//Set crank HP to 0 at minimum
		crankHorsepowerValue = Math.max(0, crankHorsepowerValue);

		//Determine selected driveTrain
		//(We are assuming there is one as we select a default)
		if (fwd.isSelected()) {
			this.selectedDrivetrain = Drivetrain.FWD;
		} else if (rwd.isSelected()) {
			this.selectedDrivetrain = Drivetrain.RWD;
		} else {
			this.selectedDrivetrain = Drivetrain.AWD;
		}
		
		//Calculate loss percentage for selected drivetrain
		int loss = selectedDrivetrain.getLossPercentage();
		
		//If automatic transmission is selected, add additional loss percentage.
		if (auto.isSelected()) {
			loss += AUTOMATIC_LOSS;
		}
		
		//Calculate wheel HP by calculating and subtracting drivetrain losses.
		double percentLoss = loss / 100.0;
		double powerLost = percentLoss * crankHorsepowerValue;
		double result = crankHorsepowerValue - powerLost;

		//Return result to interface
		this.wheelHorsepower.setValue(result);
	}
}
