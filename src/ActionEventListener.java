/*
 * Action Listener for Calculate button.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ActionEventListener implements ActionListener {

    private Calculator calculator;

    /*
     * Action Listener pulls values from GUI at time of button press and passes to Calculator.
     */
    public ActionEventListener(JFormattedTextField horsepowerField, JRadioButton crank, JRadioButton auto, JRadioButton fwd, JRadioButton rwd, JFormattedTextField estimateField, JLabel estimateOriginLabel) {
        this.calculator = new Calculator(horsepowerField, crank, auto, fwd, rwd, estimateField, estimateOriginLabel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.calculator.calculate();
    }
}
