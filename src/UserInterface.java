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
    private JFormattedTextField horsepowerField, estimateField;
    private JRadioButton crank, wheel, fwd, rwd, awd, auto, manual;
    private JLabel horsepowerLabel, driveTrainLabel, transmissionLabel, estimateLabel, estimateOriginLabel;
    private ButtonGroup horsepowerGroup, drivetrainGroup, transmissionGroup;
    private JButton calculateButton;

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
        horsepowerLabel = new JLabel("Horsepower:");
        horsepowerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(horsepowerLabel);
        
        horsepowerField = new JFormattedTextField(formatter);
        container.add(horsepowerField);
        
        crank = new JRadioButton("at the Crank");
        wheel = new JRadioButton("at the Wheels");

        horsepowerGroup = new ButtonGroup();
        horsepowerGroup.add(crank);
        horsepowerGroup.add(wheel);
        container.add(crank);
        container.add(wheel);
        
        //Row 2 - Drivetrain radio buttons
        driveTrainLabel = new JLabel("Drivetrain:");
        driveTrainLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(driveTrainLabel);
        
        fwd = new JRadioButton("FWD");
        rwd = new JRadioButton("RWD");
        awd = new JRadioButton("AWD");

        drivetrainGroup = new ButtonGroup();
        drivetrainGroup.add(fwd);
        drivetrainGroup.add(rwd);
        drivetrainGroup.add(awd);
        container.add(fwd);
        container.add(rwd);
        container.add(awd);
        
        
        //Row 3 - Transmission radio buttons
        transmissionLabel = new JLabel("Transmission:");
        transmissionLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(transmissionLabel);
        
        auto = new JRadioButton("Automatic");
        manual = new JRadioButton("Manual");

        transmissionGroup = new ButtonGroup();
        transmissionGroup.add(auto);
        transmissionGroup.add(manual);
        container.add(auto);
        container.add(manual);
        
        container.add(new JLabel());
        
        //Row 4 - Horsepower output and Calculate button
        estimateLabel = new JLabel("Estimated hp:");
        estimateLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        container.add(estimateLabel);
        
        estimateField = new JFormattedTextField(formatter);
        estimateField.setEditable(false);
        container.add(estimateField);
        
        estimateOriginLabel = new JLabel();
        container.add(estimateOriginLabel);
        
        calculateButton = new JButton("Calculate");
        container.add(calculateButton);
        
        /*
         * End: Setup GUI elements
         */
        
        //Select Default Options
        crank.doClick();
        rwd.doClick();
        manual.doClick();
        
        //Implement Calculate listener
        ActionEventListener listener = new ActionEventListener(this);
        calculateButton.addActionListener(listener);
    }
    
    /*
     * Getters and Setters
     */
    
    public String getHorsepower(){
    	return horsepowerField.getText();
    }
    
    public int getHorsepowerValue(){
    	return (int) horsepowerField.getValue();
    }
    
    public void setHorsepowerValue(int value){
    	horsepowerField.setValue(value);
    }
    
    public boolean isCrankValueGiven(){
    	return crank.isSelected();
    }
    
    public boolean isAuto(){
    	return auto.isSelected();
    }
    
    public boolean isFWD(){
    	return fwd.isSelected();
    }
    
    public boolean isRWD(){
    	return rwd.isSelected();
    }
    
    public void setEstimate(double value){
    	estimateField.setValue(value);
    }
    
    public void setEstimateOriginLabel(String value){
    	estimateOriginLabel.setText(value);
    }
    
}
