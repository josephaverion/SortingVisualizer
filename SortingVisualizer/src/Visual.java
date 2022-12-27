import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

public class Visual extends JPanel {
	
	protected static final long serialVersionUID = 1L;
	
	protected final int WIDTH = 1000;
	protected final int HEIGHT = WIDTH * 9 / 16;
	protected int SIZE = 50;
	protected float bar_width = (float) WIDTH / SIZE;
	protected float[] bar_height = new float[SIZE];
	protected SwingWorker<Void, Void> shuffler;
	protected SwingWorker<Void, Void> sorter;
	protected boolean running = false;
	
	protected int sortType = 0;
	
	// initialize the JPanel
	public Visual() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initBarHeight();
	}
	
	// starts the sort
	public void start() {
		initSorter();
		initShuffler();
	}
	
	// stops the sort and resets the array
	public void stop() {
		shuffler.cancel(true);
		sorter.cancel(true);
		initBarHeight();
		running = false;
		repaint();
	}
	
	// changes the size of the array
	public void changeSize(int size) {
		this.SIZE = size;
		bar_height = new float[SIZE];
		bar_width = (float) WIDTH / SIZE;
		initBarHeight();
		
		repaint();
	}
	
	// sets the speed before starting the sort
	public void changeSpeed(int speed) {
		Algorithms.setSpeed(speed);
	}
	
	// paints the visual of the algorithm chosen
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.WHITE);
		Rectangle2D.Float bar;
		for(int i = 0; i < SIZE; i++) {
			bar = new Rectangle2D.Float(i * bar_width, HEIGHT - bar_height[i], bar_width, bar_height[i]);
			g2D.fill(bar);
		}
		
		if(running) {
			g2D.setColor(Color.RED);
			bar = new Rectangle2D.Float(Algorithms.getCurrentIndex() * bar_width, HEIGHT - bar_height[Algorithms.getCurrentIndex()], bar_width, bar_height[Algorithms.getCurrentIndex()]);
			g2D.fill(bar);
			
			g2D.setColor(Color.GREEN);
			bar = new Rectangle2D.Float(Algorithms.getTraversingIndex() * bar_width, HEIGHT - bar_height[Algorithms.getTraversingIndex()], bar_width, bar_height[Algorithms.getTraversingIndex()]);
			g2D.fill(bar);
			
			g2D.setColor(Color.MAGENTA);
			bar = new Rectangle2D.Float(Algorithms.getSelectedIndex() * bar_width, HEIGHT - bar_height[Algorithms.getSelectedIndex()], bar_width, bar_height[Algorithms.getSelectedIndex()]);
			g2D.fill(bar);
		}
	}
	
	// starts the sorting
	private void initSorter() {
		sorter = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws InterruptedException {
				
				running = true;
				
				if(sortType == 0) {
					Algorithms.insertionSort();
				} else if (sortType == 1) {
					Algorithms.selectionSort();
				} else if (sortType == 2) {
					Algorithms.bubbleSort();
				} else if (sortType == 3) {
					Algorithms.heapSort();
				} else if (sortType == 4) {
					Algorithms.mergeSort();
				} else if (sortType == 5) {
					Algorithms.quickSort();
				}
				
				running = false;
				
				repaint();
				Main.options.startButton.setEnabled(true);
				Main.options.sorts.setEnabled(true);
				Main.options.sizeSlider.setEnabled(true);
				Main.options.speedSlider.setEnabled(true);
				Main.options.stopButton.setEnabled(false);
				
				return null;
			}
		};
	}
	
	// shuffles the array
	private void initShuffler() {
		shuffler = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws InterruptedException {
				int middle = SIZE / 2;
				for(int i = 0, j = middle; i < middle; i++, j++) {
					int random_index = new Random().nextInt(SIZE);
					Algorithms.swap(i, random_index);
					
					random_index = new Random().nextInt(SIZE);
					Algorithms.swap(j, random_index);
					
					Thread.sleep(10);
					repaint();
				}
				return null;
			}
			
			@Override
			public void done() {
				super.done();
				sorter.execute();
			}

		};
		shuffler.execute();
	}
	
	// sets each bar height which are the values in the array
	private void initBarHeight() {
		float interval = (float) HEIGHT / SIZE;
		
		for(int i = 0; i < SIZE; i++) {
			bar_height[i] = (i+1) * interval;
		}
	}
}

