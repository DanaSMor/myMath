package myMath;
//
//import javax.swing.JFrame;
//
//import de.erichseifert.gral.data.DataTable;
//import de.erichseifert.gral.plots.XYPlot;
//import de.erichseifert.gral.ui.InteractivePanel;
//
//public class LinePlotTest extends JFrame {
//
//    public static void main(String[] args) {
//    	
//        JFrame frame = new JFrame("a");
//        frame.setVisible(true);
//
//        frame.setSize(800, 600);
//
//        DataTable data = new DataTable(Double.class, Double.class);
//        for (double x = -5.0; x <= 5.0; x+=0.25) {
//            double y = 5.0*Math.sin(x);
//            data.add(x, y);
//        }
//        XYPlot plot = new XYPlot(data);
//        frame.getContentPane().add(new InteractivePanel(plot));
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//
//
//	
//
//	}
//}



import java.awt.Color;
import java.util.Iterator;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
	public LinePlotTest() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		
		Polynom or = new Polynom("x^5+x^2-80");
		DataTable data = new DataTable(Double.class, Double.class);
		for (double x = -8.0; x <= 8.0; x+=0.25) {
			double y = or.f(x);
			data.add(x, y);
		}
		XYPlot plot = new XYPlot(data);
		getContentPane().add(new InteractivePanel(plot));

		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

		Color color = new Color(0.0f, 0.4f, 1.0f);

		plot.getLineRenderers(data).get(0).setColor(color);
		plot.getPointRenderers(data).get(0).setColor(color);

	}


	public static void main(String[] args) {
		LinePlotTest frame = new LinePlotTest();
		frame.setVisible(true);
	}
}
