import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class ActionEventListener implements ActionListener {

    private Calculator calculator;

    public ActionEventListener(JFormattedTextField crankHorsepower, JRadioButton auto, JRadioButton fwd, JRadioButton rwd, JFormattedTextField wheelHorsepower) {
        this.calculator = new Calculator(crankHorsepower, auto, fwd, rwd, wheelHorsepower);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.calculator.calculate();
    }
}
