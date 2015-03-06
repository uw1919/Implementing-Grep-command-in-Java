/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandelbrot1;

/**
 *
 * @author Ankita Sambhare
 */


/*
 * Mandelbrot.java
 * @author:Anjali Pachpute
 * @author:Aishwarya Thipparthi
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * 
 * class implements zoom on Mandelbrot set,implements MouseListener interface
 * and extends JFrame
 *
 */
public class Mandelbrot1 extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private final int MAX = 5000;
	private final int LENGTH = 400;
	private final double ZOOM = 500;
	private BufferedImage I;
	private double zx, zy, cX, cY, tmp;
	private int[] colors = new int[MAX];
	private int xstart, ystart, xend, yend;
	private double ratio = 1;
	private int xmin = 0;
	private int ymin = 0;

	/**
	 * constructor method
	 */
	public Mandelbrot1() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * createSet method implements the mandelbrot set with the ratio for the
	 * zoom
	 */
	public void createSet() {
		I = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				zx = zy = 0;
				cX = (xmin + (x * ratio) - LENGTH) / ZOOM;
				cY = (ymin + (y * ratio) - LENGTH) / ZOOM;
				int iter = 0;
				while ((zx * zx + zy * zy < 4) && (iter < MAX - 1)) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter++;
				}
				if (iter > 0)
					I.setRGB(x, y, colors[iter]);
				else
					I.setRGB(x, y, iter | (iter << 8));
				repaint();
			}
		}
	}

	public synchronized void rpaint(int x, int y, int iter) {

		if (iter > 0)

			I.setRGB(x, y, colors[iter]);
		else
			I.setRGB(x, y, iter | (iter << 8));
		repaint();

	}

	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index
					/ (index + 8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(I, 0, 0, this);
	}

	/**
	 * the main method
	 * 
	 * @param args
	 *            commandline arguments
	 */
	public static void main(String[] args) {
		Mandelbrot1 aMandelbrot = new Mandelbrot1();
		aMandelbrot.setVisible(true);
		aMandelbrot.addMouseListener(aMandelbrot);
		aMandelbrot.createSet();
	}

	@Override
	public void mouseClicked(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * gets the x and y coordinates from when mouse is pressed
	 */
	public void mousePressed(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub
		xstart = paramMouseEvent.getX();
		ystart = paramMouseEvent.getY();

	}

	@Override
	/**
	 * gets the x and y coordinates from when mouse is released
	 */
	public void mouseReleased(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub
		xend = paramMouseEvent.getX();
		yend = paramMouseEvent.getY();
		reCreate();

	}

	/**
	 * calculates the ratio from the new x,y coordinates. and calls createSet
	 * again
	 */
	private void reCreate() {
		// TODO Auto-generated method stub
		int xratio = Math.abs(xend - xstart);
		int yratio = Math.abs(yend - ystart);
		xmin += (ratio*Math.min(xstart, xend));
		ymin += (ratio*Math.min(ystart, yend));

		ratio *= Math.max(xratio, yratio) / (double) LENGTH;
		createSet();

	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}
}
