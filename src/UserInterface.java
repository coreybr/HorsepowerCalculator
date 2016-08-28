/*
 * Class which handles GUI.
 */
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

    //Initiate GUI
	@Override
    public void run() {
        frame = new JFrame("Horsepower Calculator v" + version);
        frame.setPreferredSize(new Dimension(440, 170));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

	//Define GUI content
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
        
        /*
         * Setup GUI elements
         */
        
        //Row 1 - Horsepower input with crank/wheel radio buttons
        JLabel horsepowerLabel = new JLabel("Horsepower:");
        container.add(horsepowerLabel);
        
        JFormattedTextField horsepowerField = new JFormattedTextField(formatter);
        container.add(horsepowerField);
        
        JRadioButton crank = new JRadioButton("at the Crank");
        JRadioButton wheel = new JRadioButton("at the Wheels");

        ButtonGroup horsepowerGroup = new ButtonGroup();
        horsepowerGroup.add(crank);
        horsepowerGroup.add(wheel);
        container.add(crank);
        container.add(wheel);
        
        //Row 2 - Drivetrain radio buttons
        JLabel driveTrainLabel = new JLabel("Drivetrain:");
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
        
        
        //Row 3 - Transmission radio buttons
        JLabel transmissionLabel = new JLabel("Transmission:");
        container.add(transmissionLabel);
        
        JRadioButton auto = new JRadioButton("Automatic");
        JRadioButton manual = new JRadioButton("Manual");

        ButtonGroup transmissionGroup = new ButtonGroup();
        transmissionGroup.add(auto);
        transmissionGroup.add(manual);
        container.add(auto);
        container.add(manual);
        
        container.add(new JLabel());
        
        //Row 4 - Horsepower output and Calculate button
        JLabel estimateLabel = new JLabel("Estimated hp:");
        container.add(estimateLabel);
        
        JFormattedTextField estimateField = new JFormattedTextField(formatter);
        estimateField.setEditable(false);
        container.add(estimateField);
        
        JLabel estimateOriginLabel = new JLabel();
        container.add(estimateOriginLabel);
        
        JButton calculateButton = new JButton("Calculate");
        container.add(calculateButton);
        
        /*
         * End: Setup GUI elements
         */
        
        //Select Default Options
        crank.doClick();
        rwd.doClick();
        manual.doClick();
        
        //Implement Calculate listener
        ActionEventListener listener = new ActionEventListener(horsepowerField, crank, auto, fwd, rwd, estimateField, estimateOriginLabel);
        calculateButton.addActionListener(listener);
    }
}
