/*
 * Action Listener for Calculate button.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListener implements ActionListener {

    private Calculator calculator;

    /*
     * Action Listener pulls values from GUI at time of button press and passes to Calculator.
     */
    public ActionEventListener(UserInterface ui) {
        this.calculator = new Calculator(ui);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.calculator.calculate();
    }
}
