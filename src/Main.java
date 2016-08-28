import javax.swing.SwingUtilities;

public class Main {
	
	private final static double version = 1.3;
	
	// Main method simply initializes GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserInterface(version));
    }
}
