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

	public Calculator(JFormattedTextField crankHorsepower, JRadioButton auto, JRadioButton fwd, JRadioButton rwd,
			JFormattedTextField wheelHorsepower) {
		this.crankHorsepower = crankHorsepower;
		this.auto = auto;
		this.fwd = fwd;
		this.rwd = rwd;
		this.wheelHorsepower = wheelHorsepower;
	}

	public void calculate() {

		/*
		 * Reference: FWD: 10-15% loss; RWD: 10-18% loss; AWD: 17-25% loss.
		 * Automatic: +2-5% loss
		 *
		 * Current calculations use most conservative numbers (biggest losses)
		 */
		if (crankHorsepower.getText().isEmpty()) {
			crankHorsepower.setValue(0);
		}
		int crankHorsepowerValue = (int) crankHorsepower.getValue();
		
		// HP is minimum 0
		crankHorsepowerValue = Math.max(0, crankHorsepowerValue);

		// Determine selected driveTrain
		if (fwd.isSelected()) {
			this.selectedDrivetrain = Drivetrain.FWD;
		} else if (rwd.isSelected()) {
			this.selectedDrivetrain = Drivetrain.RWD;
		} else {
			this.selectedDrivetrain = Drivetrain.AWD;
		}

		int loss = selectedDrivetrain.getLossPercentage();
		if (auto.isSelected()) {
			loss += AUTOMATIC_LOSS;
		}
		double percentLoss = loss / 100.0;
		double powerLost = percentLoss * crankHorsepowerValue;
		double result = crankHorsepowerValue - powerLost;
		this.wheelHorsepower.setValue(result);
	}
}
