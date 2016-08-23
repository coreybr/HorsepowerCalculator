import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class UserInterface implements Runnable {

    private JFrame frame;
    private double version;

    public UserInterface(double version) {
		this.version = version;
	}

	@Override
    public void run() {
        frame = new JFrame("Horsepower Calculator v" + version);
        frame.setPreferredSize(new Dimension(370, 170));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
    	//Setup layout
        GridLayout layout = new GridLayout(4,4);
        container.setLayout(layout);

        //Setup integer field formatter
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // Commit value on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        
        //Setup GUI elements
        JLabel crankLabel = new JLabel("Crank HP:");
        container.add(crankLabel);
        
        JFormattedTextField crankField = new JFormattedTextField(formatter);
//        crankField.setValue(0);
        container.add(crankField);
        
        //TODO: Make this Drivetrain label when layout is refactored
        JLabel driveTrainLabel = new JLabel("");
        container.add(driveTrainLabel);
        
        JRadioButton fwd = new JRadioButton("FWD");
        JRadioButton rwd = new JRadioButton("RWD");
        JRadioButton awd = new JRadioButton("AWD");

        ButtonGroup drivetrainGroup = new ButtonGroup();
        drivetrainGroup.add(fwd);
        drivetrainGroup.add(rwd);
        drivetrainGroup.add(awd);
        container.add(fwd);
        container.add(rwd);
        container.add(awd);
        
        JLabel transmissionLabel = new JLabel("Transmission:");
        container.add(transmissionLabel);
        
        JRadioButton auto = new JRadioButton("Automatic");
        JRadioButton manual = new JRadioButton("Manual");

        ButtonGroup transmissionGroup = new ButtonGroup();
        transmissionGroup.add(auto);
        transmissionGroup.add(manual);
        container.add(auto);
        container.add(manual);
        
        JLabel wheelLabel = new JLabel("Wheel HP:");
        container.add(wheelLabel);
        
        JFormattedTextField wheelField = new JFormattedTextField(formatter);
        wheelField.setEditable(false);
        container.add(wheelField);
        
        JButton calculateButton = new JButton("Calculate");
        container.add(calculateButton);
        
        //Select Default Options
        rwd.doClick();
        manual.doClick();
        
        //Implement Calculate listener
        ActionEventListener listener = new ActionEventListener(crankField, auto, fwd, rwd, wheelField);
        calculateButton.addActionListener(listener);
    }
}
