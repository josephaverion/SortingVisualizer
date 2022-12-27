import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	
	protected JPanel firstRow = new JPanel();
	
	protected JLabel sizeLabel = new JLabel();
	protected JComboBox<String> sorts;
	protected JButton startButton = new JButton();
	// Default size is 100
	// Min 10, Max 100
	protected JSlider sizeSlider = new JSlider(10,500,100);
	

	protected JPanel secondRow = new JPanel();
	
	protected JLabel speedLabel = new JLabel();
	protected JButton stopButton = new JButton();
	// Default speed is 25
	// Min 1 ms, Max 250 ms
	protected JSlider speedSlider = new JSlider(1,250,25);
	
	public Options() {
		setLayout(new BorderLayout());
		add(firstRow, BorderLayout.NORTH);
		add(secondRow, BorderLayout.SOUTH);
		firstRow.setLayout(null);
		secondRow.setLayout(null);
		
		initButtons();
		initSliders();
		initLabels();
		
		firstRow.setPreferredSize(new Dimension(600,50));
		firstRow.setBackground(Color.black);
		firstRow.add(startButton);
		firstRow.add(sizeLabel);
		firstRow.add(sizeSlider);
		firstRow.add(sorts);
		
		secondRow.setPreferredSize(new Dimension(600,50));
		secondRow.setBackground(Color.black);
		secondRow.add(stopButton);
		secondRow.add(speedLabel);
		secondRow.add(speedSlider);
	}
	
	// sets each button
	private void initButtons() {
		startButton.setText("Start");
		startButton.setForeground(Color.black);
		startButton.setFont(new Font(null, Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		startButton.setBounds(247, 10, 77, 35);
		
		stopButton.setText("Stop");
		stopButton.setForeground(Color.black);
		stopButton.setFont(new Font(null, Font.PLAIN, 20));
		stopButton.setFocusable(false);
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		stopButton.setBounds(247, 10, 77, 35);
		
		String[] sortOptions = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Heap Sort", "Merge Sort", "Quick Sort"};
		sorts = new JComboBox<String>(sortOptions);
		sorts.addActionListener(this);	
		sorts.setBounds(710, 10, 110, 25);
	}
	
	// sets each slider
	private void initSliders() {
		sizeSlider.setOpaque(false);
		sizeSlider.addChangeListener(this);
		sizeSlider.setBounds(500, 10, 200, 35);
		
		speedSlider.setOpaque(false);
		speedSlider.addChangeListener(this);
		speedSlider.setBounds(500, 10, 200, 35);
	}
	
	// sets each label
	private void initLabels() {
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setText("Size: " + sizeSlider.getValue());
		sizeLabel.setFont(new Font(null, Font.PLAIN, 25));
		sizeLabel.setBounds(330, 10, 200, 35);
		
		speedLabel.setForeground(Color.WHITE);
		speedLabel.setText("Speed: " + speedSlider.getValue() + " ms");
		speedLabel.setFont(new Font(null, Font.PLAIN, 25));
		speedLabel.setBounds(330, 10, 200, 35);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// disables all modification buttons when sort is started via pressing start button
		if(e.getSource() == startButton) {
			Main.visual.start();
			startButton.setEnabled(false);
			sizeSlider.setEnabled(false);
			speedSlider.setEnabled(false);
			sorts.setEnabled(false);
			stopButton.setEnabled(true);
		}
		
		// enables all modification buttons when sort is started via pressing stop button
		if(e.getSource() == stopButton) {
			Main.visual.stop();
			startButton.setEnabled(true);
			sizeSlider.setEnabled(true);
			sorts.setEnabled(true);
			speedSlider.setEnabled(true);
			stopButton.setEnabled(false);
		}
		
		// sets the algorithm to be sorted
		if(e.getSource() == sorts) {
			Main.visual.sortType = sorts.getSelectedIndex();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		// when size slider is moved, the size of the array changes
		if(e.getSource() == sizeSlider) {
			sizeLabel.setText("Size: " + sizeSlider.getValue());
			Main.visual.changeSize(sizeSlider.getValue());
		}
		
		// when speed slider is moved, the speed of how fast the algorithm runs changes
		if(e.getSource() == speedSlider) {
			speedLabel.setText("Speed: " + speedSlider.getValue() + " ms");
			Main.visual.changeSpeed(speedSlider.getValue());
		}
	}
}

