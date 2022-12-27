import java.awt.*;

import javax.swing.*;

public class Main {
	
	// initialize the JFrame and starts the program
	
	// separate JPanels for the input and the visual for the sorting algorithm
	protected static Visual visual = new Visual();
	protected static Options options = new Options();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Sorting Visualizer");
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(visual);
			frame.getContentPane().add(options, BorderLayout.NORTH);
			frame.validate();
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}