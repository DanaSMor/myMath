package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import algorithms.Equation;
import algorithms.EquationParser;

public class ButtonListener implements ActionListener {
	
	JTextField jf;
	CartesianPlane cp;
	JButton solveButton;
	public ButtonListener(JTextField j, CartesianPlane c, JButton solvebtn){
		jf = j;
		cp = c;
		solveButton = solvebtn;
	}
	
	public void actionPerformed(ActionEvent a) {
		Equation q = EquationParser.parseString(jf.getText());
		cp.updateEquation(q);
		cp.repaint();
		if(a.getSource().equals( solveButton )){
			double root = cp.getSolution();
			if(Double.isNaN(root)){
				JOptionPane.showMessageDialog(null, "f(x) has no real roots");
			} else{
				JOptionPane.showMessageDialog(null, "x = " + cp.getSolution());
			}
			return;
		}
	
	}
}
