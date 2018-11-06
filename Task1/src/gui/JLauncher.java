package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import algorithms.EquationParser;

public class JLauncher extends JFrame {
	static String initEq;
	static CartesianPlane cp;
	
	public static void main(String arg[]){

		JFrame controlPanel = new JFrame();


		controlPanel.setLayout(new FlowLayout());

		JTextField eqField = new JTextField(10);
		eqField.setText(initEq);
		controlPanel.add(eqField);

		JButton graphBtn = new JButton("Graph");
		controlPanel.add(graphBtn);
		graphBtn.setVisible(true);

		JButton solveBtn = new JButton("Solve");
		controlPanel.add(solveBtn);
		solveBtn.setVisible(false);

		ButtonListener blistener = new ButtonListener(eqField, cp, solveBtn);
		graphBtn.addActionListener(blistener);
		eqField.addActionListener(blistener);
		solveBtn.addActionListener(blistener);

		controlPanel.setSize(350,70);
		controlPanel.setVisible(true);

		controlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	public JLauncher(String s) {
		super("Function Grapher");
		initEq = s;
		cp = new CartesianPlane(EquationParser.parseString(initEq));

		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(cp);
		validate();
	}


}