/*
 * Class which performs calculations. 
 */
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Calculator {

	private JFormattedTextField horsepowerField, estimateField;
	private JRadioButton crank, auto, fwd, rwd;
	private Drivetrain selectedDrivetrain;
	private JLabel estimateOriginLabel;
	private static final int AUTOMATIC_LOSS = 5;

	/*
	 * Constructor takes GUI input.
	 */
	public Calculator(JFormattedTextField horsepowerField, JRadioButton crank, JRadioButton auto, JRadioButton fwd, JRadioButton rwd,
			JFormattedTextField estimateField, JLabel estimateOriginLabel) {
		this.horsepowerField = horsepowerField;
		this.crank = crank;
		this.auto = auto;
		this.fwd = fwd;
		this.rwd = rwd;
		this.estimateField = estimateField;
		this.estimateOriginLabel = estimateOriginLabel;
	}

	/*
	 * Main calculate function called by Calculate button.
	 */
	public void calculate() {

		//Empty crank HP = 0
		if (horsepowerField.getText().isEmpty()) {
			horsepowerField.setValue(0);
		}
		
		//Retrieve crank HP value
		int horsepowerValue = (int) horsepowerField.getValue();
		
		//Set crank HP to 0 at minimum
		horsepowerValue = Math.max(0, horsepowerValue);

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
		
		double percentLoss, result;
		if (crank.isSelected()){
			//Calculate wheel HP by calculating and subtracting drivetrain losses.
			percentLoss = loss / 100.0;
			result = horsepowerValue - percentLoss * horsepowerValue;
			this.estimateOriginLabel.setText(" at the wheels");
		} else {
			//Calculate crank HP by calculating and adding drivetrain losses.
			percentLoss = 100.0 / (100 - loss);
			result = percentLoss * horsepowerValue;
			this.estimateOriginLabel.setText(" at the crank");
		}
		
		//Return result to interface
		this.estimateField.setValue(result);
	}
}
