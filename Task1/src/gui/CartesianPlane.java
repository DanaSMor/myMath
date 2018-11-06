package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;

import javax.swing.JPanel;

import algorithms.Equation;

public class CartesianPlane extends JPanel {

	Equation currentEq;

	public CartesianPlane(Equation a) {
		updateEquation(a);
	}

	public void updateEquation(Equation n) {
		currentEq = n;
	}

	int maxheight = 600;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);

		g.drawLine(0, 300, 1000, 300);
		g.drawLine(500, 0, 500, 600);

		// draw horizontal labels

		for (int x = 500; x <= 1000; x += 20) {
			if (x != 500 && x != 1000 && x % 100 == 0) {

				g.drawLine(x, 292, x, 308);

				String label = Double.toString(x - 500);
				g.drawChars(label.toCharArray(), 0, label.length(), x - 15, 325);
			} else {
				g.drawLine(x, 295, x, 305);
			}
		}

		for (int x = 500; x >= 0; x -= 20) {
			if (x != 500 && x != 0 && x % 100 == 0) {

				g.drawLine(x, 292, x, 308);

				String label = Double.toString(x - 500);
				g.drawChars(label.toCharArray(), 0, label.length(), x - 23, 325);
			} else {
				g.drawLine(x, 295, x, 305);
			}
		}

		// draw vertical labels
		for (int y = 300; y <= 600; y += 20) {

			if (y != 300 && y != 600 && y % 100 == 0) {

				g.drawLine(492, y, 508, y);

				String label = Double.toString(-(y - 300));
				g.drawChars(label.toCharArray(), 0, label.length(), 515, y+6);
			} else {
				g.drawLine(495, y, 505, y);
			}
		}

		for (int y = 300; y >= 0; y -= 20) {
			if (y != 300 && y != 0 && y % 100 == 0) {

				g.drawLine(492, y, 508, y);

				String label = Double.toString(-(y - 300));
				g.drawChars(label.toCharArray(), 0, label.length(), 515, y+6);
			} else {
				g.drawLine(495, y, 505, y);
			}
		}

		g.setColor(Color.RED);
		for (int x = -500; x <= 500; x++) {
			int y1 = 300 - (int) Math.round(currentEq.compute(x));
			int y2 = 300 - (int) Math.round(currentEq.compute(x + 1));

			/*
			 * if(Math.abs(y1) > 600){ y1 = 600 * y1 / Math.abs(y1); }
			 */

			if (y2 <= 600 && y1 <= 600)
				g.drawLine(x + 500, y1, x + 1 + 500, y2);
		}

		g.setColor(Color.BLUE);
	}

	public double getSolution() {
		return Equation.solve(currentEq);
	}
}
