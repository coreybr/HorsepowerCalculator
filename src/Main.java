import javax.swing.SwingUtilities;

public class Main {
	
	private final static double version = 1.2;
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserInterface(version));
    }
}
